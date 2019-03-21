import {Component, OnInit} from "@angular/core";
import {
  MatSnackBar,
  MatSnackBarConfig
} from '@angular/material';
import {ProjectService} from "../project.service";
import {Project} from "../domain/Project";

@Component({
  selector: 'app-project-filter',
  templateUrl: './project-filter.component.html',
  styleUrls: ['./project-filter.component.scss']
})
export class ProjectFilterComponent implements OnInit {

  projects: Project[];
  companies: Set<string>;
  industries: Set<string>;
  skills: Set<string>;
  distributionValues: Set<boolean>;
  employeeNumbers: NumberOfEmployees[] = [NumberOfEmployees.LessOrEqualThan5, NumberOfEmployees.LessOrEqualThan10, NumberOfEmployees.LessOrEqualThan50, NumberOfEmployees.LessOrEqualThan100, NumberOfEmployees.MoreThan100];

  constructor(
    private snackBar: MatSnackBar,
    private projectService: ProjectService) {
  }

  ngOnInit(): void {
    this.getProjects();
  }

  public openSnackBar(): void {
    let config = new MatSnackBarConfig();
    config.verticalPosition = 'bottom';
    config.horizontalPosition = 'center';
    config.duration = 5000;
    this.snackBar.open('You will be notified about new projects', 'Thanks', config);
  }

  private getProjects() {
    this.projectService
      .getProjects()
      .subscribe(projects => {
        this.projects = projects;
        this.companies = new Set(this.projects.map(project => project.zuehlkeCompany));
        this.industries = new Set(this.projects.map(project => project.industry));
        this.distributionValues = new Set(this.projects.map(project => project.distributed));
        let mergedSkills: string[][] = [];
        this.projects.forEach(project => mergedSkills.push(project.skills));
        this.skills = new Set(mergedSkills.flat(1));
      });
  }

}

enum NumberOfEmployees {
  LessOrEqualThan5 = "<= 5",
  LessOrEqualThan10 = "<= 10",
  LessOrEqualThan50 = "<= 50",
  LessOrEqualThan100 = "<= 100",
  MoreThan100 = "> 100"
}
