import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {faHeart} from '@fortawesome/free-solid-svg-icons';
import {faAngular, faJava} from '@fortawesome/free-brands-svg-icons';
import {intersectionObserverPreset, LazyLoadImageModule} from 'ng-lazyload-image';


import {library} from '@fortawesome/fontawesome-svg-core';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {SearchPipe} from './search.pipe';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatBadgeModule,
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatSelectModule,
  MatSidenavModule,
  MatSnackBarModule,
  MatToolbarModule
} from '@angular/material';
import {LayoutModule} from '@angular/cdk/layout';
import {TopbarComponent} from './navigation/topbar/topbar.component';
import {MapComponent} from './map/map.component';
import {AgmCoreModule} from '@agm/core';
import {ErrorRequestInterceptor} from './common/error-request-interceptor';
import {ProjectFilterComponent} from './project-filter/project-filter.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ScrollDispatchModule } from '@angular/cdk/scrolling';
import {InjectableRxStompConfig, RxStompService, rxStompServiceFactory} from '@stomp/ng2-stompjs';
import * as SockJS from 'sockjs-client';
import { ProjectDetailComponent } from './project-detail/project-detail.component';

// For more icons, please checkout https://fontawesome.com/icons?d=gallery
library.add(faHeart);
library.add(faJava);
library.add(faAngular);


function socketProvider() {
  return new SockJS('http://localhost:8080/socket');
}

const stompConfig: InjectableRxStompConfig = {
  webSocketFactory: socketProvider,
  connectHeaders: {},
  heartbeatIncoming: 0,
  heartbeatOutgoing: 20000,
  reconnectDelay: 5000,
};

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    SearchPipe,
    TopbarComponent,
    MapComponent,
    ProjectFilterComponent,
    ProjectDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    FontAwesomeModule,
    LazyLoadImageModule.forRoot({
      preset: intersectionObserverPreset
    }),
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTooltipModule,
    MatBadgeModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatSnackBarModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    ScrollDispatchModule,
    AgmCoreModule.forRoot({apiKey: 'AIzaSyBrgp24CvFV3M0PZGByVDVEG0qn56k8Y-g'})
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: ErrorRequestInterceptor, multi: true
    },
    {
      provide: InjectableRxStompConfig,
      useValue: stompConfig
    },
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory,
      deps: [InjectableRxStompConfig]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
