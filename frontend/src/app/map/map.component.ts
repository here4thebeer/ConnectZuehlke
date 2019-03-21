import { ProjectService } from '../project.service';
import { Component, OnInit } from '@angular/core';
import { GeocodeService } from '../geocode.service';
import { Project } from '../domain/Project';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  lat: number;
  lng: number;
  zoom = 8;
  projects: Project[];

  constructor(private geocodeService: GeocodeService, private projectService: ProjectService) {
  }

  ngOnInit(): void {
    const countryToFocus = 'Switzerland';
    this.geocodeService.geocodeFirst(countryToFocus)
      .then(location => {
        this.lat = location.geometry.location.lat();
        this.lng = location.geometry.location.lng();
      });
    this.getProjects();
  }

  public onFavorite(event, project: Project) {
    event.stopPropagation();
    project.isFavorite = !project.isFavorite;
  }

  public selectProject(project: Project) {
    project.isSelected = true;
  }

  public resetProjectSelection() {
    this.projects.forEach(p => p.isSelected = false);
  }

  public getProjectsList(): Project[] {
    return this.projects && this.projects.some(p => p.isSelected) ? [this.projects.find(p => p.isSelected)] : this.projects;
  }

  public redirectToProjectURL(project: Project) {
    window.open(project.projectURL);
  }

  private getProjects() {
    this.projectService
      .getProjects()
      .subscribe(projects => {
        console.log(projects);
        this.projects = projects;
      });
  }
}
