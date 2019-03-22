import {BehaviorSubject} from 'rxjs';
import {ProjectService} from '../project.service';
import {Component, OnInit} from '@angular/core';
import {GeocodeService} from '../geocode.service';
import {Project} from '../domain/Project';
import {LatLngBounds} from '@agm/core';
import {MAP_STYLE} from './map.styles';
import {RxStompService} from '@stomp/ng2-stompjs';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

  notificationCount : number = 0;
  readonly MIN_ZOOM: number = 1;
  readonly MAX_ZOOM: number = 22;
  style = MAP_STYLE;
  lat: number;
  lng: number;
  zoom = 8;
  projects: Project[];
  maxAmountOfEmployeesInProject: number;
  showOnlyFavorites = false;
  showOnlyNotifications = false;
  mapBounds$: BehaviorSubject<LatLngBounds> = new BehaviorSubject(null);

  constructor(private geocodeService: GeocodeService,
              private projectService: ProjectService,
              private rxStompService: RxStompService,
              private snackBar: MatSnackBar) {

    this.projectService.registerMapBoundsObservable(this.mapBounds$);

    rxStompService.watch('/topic/notification').subscribe((message) => {
      const project: Project = JSON.parse(message.body);
      let newNotification = this.findProject(project.projectCode);
      if (newNotification !== undefined) {
        this.notificationCount = this.notificationCount + 1;
        newNotification.isNewNotification = true;
      }
    });
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
    return project.isSelected ? '#ff8208' : project.isFavorite ? 'red' : '#925FA7';
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
    } else if(this.showOnlyNotifications) {
      return this.projects.filter(p => p.isNewNotification);
    } else{
      return this.projects && this.projects.some(p => p.isSelected)
        ? [this.projects.find(p => p.isSelected)]
        : this.projects;
    }
  }

  public zoomToProject(project: Project) {
    this.lat = project.location.latitude;
    this.lng = project.location.longitude;
    project.isSelected = true;
    this.onZoomChange(this.zoom + 2);
  }

  public onFavorites(): void {
    this.showOnlyFavorites = !this.showOnlyFavorites;
  }

  public onNotifications() : void {
    if (this.showOnlyNotifications) {
      this.projects.forEach(p => p.isNewNotification = false);
      this.notificationCount = 0;
    }
    this.showOnlyNotifications = !this.showOnlyNotifications;
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

  private findProject(projectCode : string):Project {
    const list = this.projects.filter(p => p.projectCode === projectCode);
    return list[0];
  }

}
