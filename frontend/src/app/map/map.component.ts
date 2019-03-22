import { BehaviorSubject } from 'rxjs';
import { ProjectService } from '../project.service';
import { Component, OnInit } from '@angular/core';
import { GeocodeService } from '../geocode.service';
import { Project } from '../domain/Project';
import { LatLngBounds } from '@agm/core';

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
  mapBounds$: BehaviorSubject<LatLngBounds> = new BehaviorSubject(null);

  constructor(private geocodeService: GeocodeService, private projectService: ProjectService) {
    this.projectService.registerMapBoundsObservable(this.mapBounds$);
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

  public selectProject(project: Project) {
    project.isSelected = true;
  }

  public resetProjectSelection() {
    this.projects.forEach(p => p.isSelected = false);
  }

  public onZoomChange(newZoomLevel) {
    this.zoom = newZoomLevel;
  }

  public onBoundsChange(latLng: LatLngBounds) {
    this.mapBounds$.next(latLng);
  }

  public calculateColor(project: Project): string {
    return project.isFavorite ? 'red' : 'blue';
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

  private getProjects() {
    this.projectService
      .getProjects()
      .subscribe(projects => {
        this.projects = projects;
        this.maxAmountOfEmployeesInProject = Math.max(...this.projects.map(p => p.amountOfEmployees));
      });
  }
}
