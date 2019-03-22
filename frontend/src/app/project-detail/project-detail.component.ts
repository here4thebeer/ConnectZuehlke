import {Component, OnInit, Input, OnChanges, SimpleChanges} from '@angular/core';
import { Project } from '../domain/Project';
import {DistanceService} from '../distance.service';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.scss']
})
export class ProjectDetailComponent implements OnChanges {

  @Input()
  project: Project;

  constructor(private distanceService: DistanceService) {
  }

  public onFavorite(event, project: Project) {
    event.stopPropagation();
    project.isFavorite = !project.isFavorite;
  }

  ngOnChanges(changes: SimpleChanges): void {
    const origin = this.distanceService.getPosition().getValue();
    this.distanceService.loadDistanceForProject(origin, this.project);
  }

  public redirectToProjectURL(project: Project) {
    window.open(project.projectURL);
  }

}
