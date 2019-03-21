import {Injectable} from '@angular/core';
import {MapsAPILoader} from '@agm/core';
import {Location} from './domain/Location';
import {LatLonCoordinates} from "./map/map.component";

@Injectable({
  providedIn: 'root'
})
export class GeocodeService {
  private maps$: Promise<any>;

  constructor(private mapsApiLoader: MapsAPILoader) {
    // The Google Api is lazy loaded, thats why we need to do this hack.
    this.maps$ = new Promise<any>(((resolve, reject) => {
      this.mapsApiLoader.load()
        .then(() => resolve(google.maps))
        .catch(reason => reject(reason));
    }));
  }

  geocodeFirst(address: string): Promise<google.maps.GeocoderResult> {
    return this.geocode(address)
      .then(locations => locations[0]);
  }

  geocode(address: string): Promise<google.maps.GeocoderResult[]> {
    return this.maps$.then(maps => {
      let geocoder = new maps.Geocoder();
      return new Promise<google.maps.GeocoderResult[]>((resolve, reject) => {
        geocoder.geocode({address}, (results: google.maps.GeocoderResult[], status: google.maps.GeocoderStatus) => {
          if (status === google.maps.GeocoderStatus.OK) {
            resolve(results);
          } else {
            reject(status);
          }
        });
      });
    });
  }

  route(destination: Location, origin?: LatLonCoordinates): Promise<google.maps.DirectionsResult> {
    return this.maps$.then(maps => {
      let request: google.maps.DirectionsRequest = {
        destination: destination.latitude + ',' + destination.longitude,
        origin: origin ? origin.latitude + "," + origin.longitude : 'Zurich',
        travelMode: maps.TravelMode.TRANSIT
      };
      let directionsService = new maps.DirectionsService();
      return new Promise<google.maps.DirectionsResult>((resolve, reject) => {
        directionsService.route(request, function (response, status) {
          if (status == maps.DirectionsStatus.OK) {
            resolve(response);
          } else {
            reject(response);
          }
        });
      });
    });
  }

}
