import { ProjectService } from '../project.service';
import { Component, OnInit } from '@angular/core';
import { GeocodeService } from '../geocode.service';
import { Project } from '../domain/Project';
import {reject, resolve} from "q";

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
  showOnlyFavorites = false;

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

  public calculateColor(project: Project): string {
    return project.isFavorite ? "red" : "blue";
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
    if (this.showOnlyFavorites) {
      return this.projects.filter(p => p.isFavorite);
    } else {
      return this.projects && this.projects.some(p => p.isSelected)
        ? [this.projects.find(p => p.isSelected)]
        : this.projects;
    }
  }

  public onFavorites(): void {
    this.showOnlyFavorites = !this.showOnlyFavorites;
  }

  public getFavoriteProjectsList(): Project[] {
    return this.getProjectsList().filter(p => p.isFavorite);
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
        this.projects.forEach(p => this.loadDistance(p));
      });
  }

  private loadDistance(project: Project) {
    if (project.zuehlkeCompany !== "Zühlke Switzerland") {
      this.geocodeService.route(project.location).then(direction => {
        project.commuteDistance = Math.round((direction.routes[0].legs[0].distance.value) / 1000);
        project.commuteDuration = Math.round(direction.routes[0].legs[0].duration.value / 60);
      }).catch(err => {
          project.commuteDistance = Number.NaN;
          project.commuteDuration = Number.NaN;
        }
      )
    } else {
      this.getCurrentPosition().then(position => {
        this.geocodeService.route(project.location, position).then(direction => {
          project.commuteDistance = Math.round((direction.routes[0].legs[0].distance.value) / 1000);
          project.commuteDuration = Math.round(direction.routes[0].legs[0].duration.value / 60);
        }).catch(err => {
            project.commuteDistance = Number.NaN;
            project.commuteDuration = Number.NaN;
          }
        )
      });
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

}

export class LatLonCoordinates {
  constructor(
    public latitude: number,
    public longitude: number) {}
}
