import {Component} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import {RxStompService} from '@stomp/ng2-stompjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private rxStompService: RxStompService,
              private snackBar: MatSnackBar) {

    rxStompService.watch('/topic/notification').subscribe((message) => {
      const messageText = JSON.parse(message.body)['message'];
      this.snackBar.open(messageText, 'close', {
        duration: 4000
      });
    });
  }
}
