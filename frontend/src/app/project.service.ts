import { ProjectFilterService } from './project-filter.service';
import { ProjectFilterSelection } from './project-filter/project-filter';
import { Project } from './domain/Project';
import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject, Subscription, timer } from 'rxjs';
import { catchError, tap, map, debounce } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { LatLngBounds } from '@agm/core';

@Injectable({ providedIn: 'root' })
export class ProjectService {
  projects: Project[];
  getProjects$: Subscription;
  currentRelevantProjects$: BehaviorSubject<Project[]> = new BehaviorSubject([]);
  lastFilterSelection: ProjectFilterSelection;
  lastMapBounds: LatLngBounds;

  constructor(
    private http: HttpClient,
    private projectFilterService: ProjectFilterService,
  ) {
    this.getProjects$ = this.http
      .get<Project[]>('/api/projects')
      .pipe(
        catchError(this.handleError<Project[]>([])),
        map(p => p.sort((a, b) => b.amountOfEmployees - a.amountOfEmployees)),
        tap(p => this.projects = p),
        tap(p => this.currentRelevantProjects$.next(p)),
      ).subscribe();
  }

  registerMapBoundsObservable(obs$: BehaviorSubject<LatLngBounds>) {
    obs$.pipe(
      debounce(() => timer(300))
    ).subscribe((latLng: LatLngBounds) => {
      this.applyMapBounds(latLng);
    });
  }

  getProjects(): Observable<Project[]> {
    return this.currentRelevantProjects$;
  }

  applyFilter(filterSelection: ProjectFilterSelection) {
    if (!this.projects) {
      return;
    }
    this.lastFilterSelection = filterSelection;
    const filteredProjects = this.projectFilterService.filter(this.projects, this.lastMapBounds, filterSelection);
    this.currentRelevantProjects$.next(filteredProjects);
  }

  applyMapBounds(latLngBounds: LatLngBounds) {
    if (!this.projects) {
      return;
    }
    this.lastMapBounds = latLngBounds;
    const filteredProjects = this.projectFilterService.filter(this.projects, latLngBounds, this.lastFilterSelection);
    this.currentRelevantProjects$.next(filteredProjects);
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
