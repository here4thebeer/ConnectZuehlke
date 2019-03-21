import { ProjectFilterService } from './project-filter.service';
import { ProjectFilterSelection } from './project-filter/project-filter';
import { Project } from './domain/Project';
import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { GeocodeService } from './geocode.service';

@Injectable({ providedIn: 'root' })
export class ProjectService {
  projects: Project[];
  currentRelevantProjects$: BehaviorSubject<Project[]> = new BehaviorSubject([]);

  constructor(private http: HttpClient, private projectFilterService: ProjectFilterService, private geocodeService: GeocodeService) {
    this.http
      .get<Project[]>('/api/projects')
      .pipe(
        catchError(this.handleError<Project[]>('getProjects', [])),
        tap(p => this.projects = p.sort((a, b) => b.amountOfEmployees - a.amountOfEmployees).slice(0, 100)),
        tap(p => this.currentRelevantProjects$.next(p.sort((a, b) => b.amountOfEmployees - a.amountOfEmployees).slice(0, 100))),
      ).subscribe();
  }

  getProjects(): Observable<Project[]> {
    return this.currentRelevantProjects$;
  }

  applyFilter(filterSelection: ProjectFilterSelection) {
    this.currentRelevantProjects$.next(this.projectFilterService.filterProjects(this.projects, filterSelection));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
