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

  readonly MIN_ZOOM: number = 1;
  readonly MAX_ZOOM: number = 22;

  lat: number;
  lng: number;
  zoom = 8;
  projects: Project[];
  maxAmountOfEmployeesInProject: number;

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

  public onZoomChange(newZoomLevel) {
    this.zoom = newZoomLevel;
  }

  public calculateRadius(amountOfEmployees: number) {
    const minRadius = 0.5;
    const amountOfEmployeesFactor = (amountOfEmployees / this.maxAmountOfEmployeesInProject) > minRadius
      ? (amountOfEmployees / this.maxAmountOfEmployeesInProject)
      : minRadius;

    return Math.floor(2 ** (this.MAX_ZOOM - this.zoom) / 2)
      * amountOfEmployeesFactor;
  }

  public getProjectsList(): Project[] {
    return this.projects && this.projects.some(p => p.isSelected)
      ? [this.projects.find(p => p.isSelected)]
      : this.projects;
  }

  public redirectToProjectURL(project: Project) {
    window.open(project.projectURL);
  }

  private getProjects() {
    this.projectService
      .getProjects()
      .subscribe(projects => {
        this.projects = projects;
        this.maxAmountOfEmployeesInProject = Math.max(...this.projects.map(p => p.amountOfEmployees));
      });
  }
}
