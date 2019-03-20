import { Project } from './domain/Project';
import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class ProjectService {

  getProjects(): Project[] {
    return [
      new Project(47.3916982, 8.483888, 'BJB Bank Julis Baer', 35),
      new Project(47.399979, 8.4470852, 'SAM TO, Swisscom', 20),
      new Project(47.393509, 8.5071727, 'SIX Payment Services', 10),
    ];
  }
}
