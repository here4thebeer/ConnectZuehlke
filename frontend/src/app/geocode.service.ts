import {Injectable} from '@angular/core';
import {LatLng, MapsAPILoader} from '@agm/core';
import {Location} from './domain/Location';

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

  route(destination: Location, origin?: LatLng): Promise<google.maps.DirectionsResult> {
    return this.maps$.then(maps => {
      let request: google.maps.DirectionsRequest = {
        destination: destination.latitude === 0 && destination.longitude === 0 ? destination.zip + ' ' + destination.city + ',' + destination.country : destination.latitude + ',' + destination.longitude,
        origin: origin ? origin.lat + "," + origin.lng : 'Zurich',
        travelMode: (destination.zip === 86444 || destination.zip === 87459) ? maps.TravelMode.DRIVING : maps.TravelMode.TRANSIT
      };
      let directionsService = new maps.DirectionsService();
      return new Promise<google.maps.DirectionsResult>((resolve, reject) => {
        directionsService.route(request, function (response, status) {
          if (status == maps.DirectionsStatus.OK) {
            resolve(response);
          } else {
            console.log(status);
            console.log(response);
            reject(response);
          }
        });
      });
    });
  }

}
