import { ProjectFilterService } from './project-filter.service';
import { ProjectFilterSelection } from './project-filter/project-filter';
import { Project } from './domain/Project';
import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ProjectService {
  projects: Project[];
  currentRelevantProjects$: BehaviorSubject<Project[]> = new BehaviorSubject([]);

  constructor(private http: HttpClient, private projectFilterService: ProjectFilterService) {
    this.http
      .get<Project[]>('/api/projects')
      .pipe(
        catchError(this.handleError<Project[]>('getProjects', [])),
        tap(p => this.projects = p),
        tap(p => this.currentRelevantProjects$.next(p)),
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
