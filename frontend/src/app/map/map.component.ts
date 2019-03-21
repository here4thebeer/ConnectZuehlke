import { ProjectService } from '../project.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { GeocodeService } from '../geocode.service';
import { Project } from '../domain/Project';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  lat: number;
  lng: number;
  zoom = 9;
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

  public selectProject(project: Project) {
    project.isSelected = true;
  }

  public resetProjectSelection() {
    this.projects.forEach(p => p.isSelected = false);
  }

  public getProjectsList(): Project[] {
    return this.projects && this.projects.some(p => p.isSelected) ? [this.projects.find(p => p.isSelected)] : this.projects;
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
