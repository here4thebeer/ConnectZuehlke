import { ProjectFilterService } from './../project-filter.service';
import { Component, OnInit } from '@angular/core';
import {
  MatSnackBar,
  MatSnackBarConfig
} from '@angular/material';
import { ProjectService } from '../project.service';
import { ProjectFilterSelection, NumberOfEmployees, FilterOptions } from './project-filter';
import { take, takeUntil, first, filter, skip } from 'rxjs/operators';

@Component({
  selector: 'app-project-filter',
  templateUrl: './project-filter.component.html',
  styleUrls: ['./project-filter.component.scss']
})
export class ProjectFilterComponent implements OnInit {
  filterOptions: FilterOptions = {
    companies: new Set(),
    industries: new Set(),
    distributionValues: new Set(),
    skills: new Set(),
    employeeNumbers: [],
  } as FilterOptions;
  filterSelection: ProjectFilterSelection = {
    zuehlkeCompany: [],
    industry: [],
    employees: undefined,
    skills: [],
    distributed: undefined,
  };

  constructor(
    private snackBar: MatSnackBar,
    private projectService: ProjectService,
    private projectFilterService: ProjectFilterService,
  ) {
  }

  ngOnInit(): void {
    this.getProjects();
  }

  public openSnackBar(): void {
    const config = {
      verticalPosition: 'bottom',
      horizontalPosition: 'center',
      duration: 5000,
    } as MatSnackBarConfig;
    this.snackBar.open('You will be notified about new projects', 'Thanks', config);
    console.log(this.filterSelection);
  }

  private getProjects() {
    this.projectService
      .getProjects()
      .pipe(
        skip(1),
        take(1),
      )
      .subscribe(projects => {
        this.filterOptions = this.projectFilterService.getFilterOptions(projects);
        console.log(this.filterOptions);
      });
  }

  applyFilters() {
    this.projectService.applyFilter(this.filterSelection);
  }
}
