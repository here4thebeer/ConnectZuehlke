import { ProjectFilterService } from './project-filter.service';
import { ProjectFilterSelection } from './project-filter/project-filter';
import { Project } from './domain/Project';
import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject, Subscription, timer } from 'rxjs';
import { catchError, tap, map, debounce } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { LatLngBounds } from '@agm/core';
import {GeocodeService} from "./geocode.service";
import {forEach} from "@angular/router/src/utils/collection";

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
    private geocodeService: GeocodeService
  ) {
    this.getProjects$ = this.http
      .get<Project[]>('/api/projects')
      .pipe(
        catchError(this.handleError<Project[]>([])),
        map(p => p.sort((a, b) => b.amountOfEmployees - a.amountOfEmployees)),
        tap(p => this.projects = p),
        tap(p => this.currentRelevantProjects$.next(p)),
        tap(p => this.loadDistance(p)),
      ).subscribe();
  }

  loadDistance(projects: Project[]) {
    let coordinates: LatLonCoordinates;
    this.getCurrentPosition().then(position => {
      coordinates = position;
      projects.forEach(p => this.loadDistanceForProject(coordinates, p));
    }).catch(err => {
      coordinates = new LatLonCoordinates(47.399699, 8.443863);
      projects.forEach(p => this.loadDistanceForProject(coordinates, p));
    });
  }

  loadDistanceForProject(origin: LatLonCoordinates, project: Project) {
    if (project.zuehlkeCompany !== 'Zühlke Switzerland') {
      this.geocodeService.route(project.location).then(direction => {
        project.commuteDistance = Math.round((direction.routes[0].legs[0].distance.value) / 1000);
        project.commuteDuration = Math.round(direction.routes[0].legs[0].duration.value / 60);
      }).catch(err => {
          project.commuteDistance = Number.NaN;
          project.commuteDuration = Number.NaN;
        }
      )
    } else {
      this.geocodeService.route(project.location, origin).then(direction => {
        project.commuteDistance = Math.round((direction.routes[0].legs[0].distance.value) / 1000);
        project.commuteDuration = Math.round(direction.routes[0].legs[0].duration.value / 60);
      }).catch(err => {
          project.commuteDistance = Number.NaN;
          project.commuteDuration = Number.NaN;
        }
      )
    }
  }

  private getCurrentPosition(): Promise<LatLonCoordinates> {
    if (window.navigator && window.navigator.geolocation) {
      return new Promise<LatLonCoordinates>((resolve, reject) => {
        window.navigator.geolocation.getCurrentPosition(position => {
          resolve(new LatLonCoordinates(position.coords.latitude, position.coords.longitude));
        }, error => {
          reject(new LatLonCoordinates(47.399699, 8.443863));
        });

      });
    }
  }

  registerMapBoundsObservable(obs$: BehaviorSubject<LatLngBounds>) {
    obs$.pipe(
      debounce(() => timer(500))
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

export class LatLonCoordinates {
  constructor(
    public latitude: number,
    public longitude: number) {}
}
