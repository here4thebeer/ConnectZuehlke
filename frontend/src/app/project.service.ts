import { Project } from './domain/Project';
import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ProjectService {
  projects: Project[];
  currentRelevantProjects$: BehaviorSubject<Project[]> = new BehaviorSubject([]);

  constructor(private http: HttpClient) {
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

  // applyIndustryFilter(industry: string) {
  //   this.relevantProjects$.next(this.projects.filter(p => p.industry === industry));
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
