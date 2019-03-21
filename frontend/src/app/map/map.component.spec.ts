import {MapComponent} from "./map.component";
import {GeocodeService} from "../geocode.service";
import {ProjectService} from "../project.service";
import { Location } from '@angular/common';


fdescribe('MapComponent', () => {

  let geoCodeServiceMock: GeocodeService;
  let projectServiceMock: ProjectService;

  let component: MapComponent = new MapComponent(geoCodeServiceMock, projectServiceMock);

  it('should calculate the project radius depending on zoom level and amount of employees', () => {
    // zoom level 1 ~ world
    // zoom level 9 ~ schweiz
    // zoom level 14 ~ zürich

    component.maxAmountOfEmployeesInProject = 50;

    component.zoom = 1; // word
    expect(component.calculateRadius(1)).toBeGreaterThan(1000000);
    expect(component.calculateRadius(1)).toBeLessThan(1500000);

    component.zoom = 9; // Switzerland
    expect(component.calculateRadius(1)).toBeGreaterThan(4000);
    expect(component.calculateRadius(1)).toBeLessThan(10000);

    component.zoom = 14; // Zürich
    expect(component.calculateRadius(1)).toBeGreaterThan(100);
    expect(component.calculateRadius(1)).toBeLessThan(500);
  });
});
