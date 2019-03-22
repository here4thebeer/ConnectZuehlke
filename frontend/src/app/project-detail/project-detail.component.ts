import { Component, OnInit, Input } from '@angular/core';
import { Project } from '../domain/Project';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.scss']
})
export class ProjectDetailComponent implements OnInit {

  @Input()
  project: Project;

  constructor() { }

  ngOnInit() {
  }

  public onFavorite(event, project: Project) {
    event.stopPropagation();
    project.isFavorite = !project.isFavorite;
  }

}
