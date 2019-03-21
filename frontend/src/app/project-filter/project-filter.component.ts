import {Component, OnInit} from "@angular/core";

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


  ngOnInit(): void {

  }

}
