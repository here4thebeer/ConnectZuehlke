import {MapComponent} from "./map.component";
import {GeocodeService} from "../geocode.service";
import {ProjectService} from "../project.service";


describe('MapComponent', () => {

  let geoCodeServiceMock: GeocodeService;
  let projectServiceMock: ProjectService;

  let component: MapComponent = new MapComponent(geoCodeServiceMock, projectServiceMock);

  describe('the project radius depending on the zoom level and amount of employees', () => {

    it('should be calculated correctly for a world level scale', () => {
      component.maxAmountOfEmployeesInProject = 50;

      component.zoom = 1;
      expect(component.calculateRadius(1)).toBeGreaterThan(500000);
      expect(component.calculateRadius(1)).toBeLessThan(1000000);

      expect(component.calculateRadius(50)).toBeGreaterThan(500000);
      expect(component.calculateRadius(50)).toBeLessThan(1500000);

      expect(component.calculateRadius(100)).toBeGreaterThan(500000);
      expect(component.calculateRadius(100)).toBeLessThan(2500000);
    });

    it('should be calculated correctly for a Swiss level scale', () => {
      component.maxAmountOfEmployeesInProject = 50;

      component.zoom = 9;
      expect(component.calculateRadius(1)).toBeGreaterThan(2000);
      expect(component.calculateRadius(1)).toBeLessThan(5000);

      expect(component.calculateRadius(50)).toBeGreaterThan(4000);
      expect(component.calculateRadius(50)).toBeLessThan(8000);

      expect(component.calculateRadius(100)).toBeGreaterThan(6000);
      expect(component.calculateRadius(100)).toBeLessThan(10000);
    });

    it('should be calculated correctly for a Zürich level scale', () => {
      component.maxAmountOfEmployeesInProject = 50;

      component.zoom = 14;
      expect(component.calculateRadius(1)).toBeGreaterThan(50);
      expect(component.calculateRadius(1)).toBeLessThan(100);

      expect(component.calculateRadius(50)).toBeGreaterThan(100);
      expect(component.calculateRadius(50)).toBeLessThan(300);

      expect(component.calculateRadius(100)).toBeGreaterThan(200);
      expect(component.calculateRadius(100)).toBeLessThan(500);
    });
  });
});
