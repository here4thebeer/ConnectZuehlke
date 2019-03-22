import {Project} from "./domain/Project";
import {GeocodeService} from "./geocode.service";
import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {LatLng} from "@agm/core";

@Injectable({ providedIn: 'root' })
export class DistanceService {

  currentPosition$: BehaviorSubject<LatLng> = new BehaviorSubject(null);

  constructor(private geocodeService: GeocodeService) {
    this.getCurrentPosition().then(position => {
      this.currentPosition$.next(position);
    }).catch(() => {
      this.currentPosition$.next({lat: 47.399699, lng: 8.443863} as unknown as LatLng);
    });
  }

  getPosition(): BehaviorSubject<LatLng> {
    return this.currentPosition$;
  }

  loadDistanceForProject(origin: LatLng, project: Project) {
    if (project.zuehlkeCompany !== 'Zühlke Switzerland') {
      if (project.zuehlkeCompany === 'Zuhlke Singapore') {
        project.commuteDistance = 10550;
        project.commuteDuration = 840;
      } else if (project.zuehlkeCompany === 'Zuhlke Hong Kong') {
        project.commuteDistance = 9290;
        project.commuteDuration = 845;
      } else {
        this.geocodeService.route(project.location).then(direction => {
          project.commuteDistance = Math.round((direction.routes[0].legs[0].distance.value) / 1000);
          project.commuteDuration = Math.round(direction.routes[0].legs[0].duration.value / 60);
        }).catch(() => {
            project.commuteDistance = Number.NaN;
            project.commuteDuration = Number.NaN;
          }
        )
      }
    } else {
      this.geocodeService.route(project.location, origin).then(direction => {
        project.commuteDistance = Math.round((direction.routes[0].legs[0].distance.value) / 1000);
        project.commuteDuration = Math.round(direction.routes[0].legs[0].duration.value / 60);
      }).catch(() => {
          project.commuteDistance = Number.NaN;
          project.commuteDuration = Number.NaN;
        }
      )
    }
  }

  private getCurrentPosition(): Promise<LatLng> {
    if (window.navigator && window.navigator.geolocation) {
      return new Promise<LatLng>((resolve, reject) => {
        window.navigator.geolocation.getCurrentPosition(position => {
          resolve({lat: position.coords.latitude, lng: position.coords.longitude} as unknown as LatLng);
        }, () => {
          reject({lat: 47.399699, lng: 8.443863} as unknown as LatLng);
        });

      });
    }
  }
}
