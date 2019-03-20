import {Project} from './domain/Project';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class ProjectService {

  constructor(private http: HttpClient) {

  }

  getProjects(): Observable<Project[]> {

    return this.http
      .get<Project[]>('/api/projects')
      .pipe(catchError(this.handleError('getProjects', [])));

  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };

  }
}
