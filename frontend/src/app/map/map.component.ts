import { ProjectService } from './../project.service';
import {Component, OnInit} from '@angular/core';
import {GeocodeService} from '../geocode.service';
import { Project } from '../domain/Project';

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
    const adressLookup = 'Schweiz';
    this.geocodeService.geocodeFirst(adressLookup)
      .then(location => {
        this.lat = location.geometry.location.lat();
        this.lng = location.geometry.location.lng();
      });

    this.projects = this.projectService.getProjects();
  }

  handleClickProject(project: Project) {
    console.log(project.name);
  }
}
