import {Component, OnInit} from "@angular/core";
import {
  MatSnackBar,
  MatSnackBarConfig
} from '@angular/material';

@Component({
  selector: 'app-project-filter',
  templateUrl: './project-filter.component.html',
  styleUrls: ['./project-filter.component.scss']
})
export class ProjectFilterComponent implements OnInit {

  companies: any = [
    {
      'a': 1
    },
    {
      'a': 2
    },
    {
      'a': 3
    }
  ];

  constructor(public snackBar: MatSnackBar) {
  }

  ngOnInit(): void {

  }

  public openSnackBar(): void {
    let config = new MatSnackBarConfig();
    config.verticalPosition = 'bottom';
    config.horizontalPosition = 'center';
    config.duration = 5000;
    this.snackBar.open('Subscribed for project notifications', 'Thanks', config);
  }

}
