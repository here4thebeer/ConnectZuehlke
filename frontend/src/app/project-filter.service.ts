import { LatLngBounds, LatLng } from '@agm/core';
import { FilterOptions, NumberOfEmployees, ProjectFilterSelection } from './project-filter/project-filter';
import { Injectable } from '@angular/core';
import { Project } from './domain/Project';

@Injectable({ providedIn: 'root' })
export class ProjectFilterService {

  public getFilterOptions(projects: Project[]): FilterOptions {
    const mergedSkills: string[] = [];
    projects.forEach(project => mergedSkills.push(...project.skills));
    return {
      companies: new Set(projects.map(project => project.zuehlkeCompany)),
      industries: new Set(projects.map(project => project.industry)),
      distributionValues: new Set(projects.map(project => project.distributed)),
      skills: new Set(mergedSkills),
      employeeNumbers: [
        NumberOfEmployees.LessOrEqualThan5,
        NumberOfEmployees.LessOrEqualThan10,
        NumberOfEmployees.LessOrEqualThan50,
        NumberOfEmployees.LessOrEqualThan100,
        NumberOfEmployees.MoreThan100
      ],
    } as FilterOptions;
  }

  filter(projects: Project[], latLngBounds: LatLngBounds, filterSelection: ProjectFilterSelection): Project[] {
    let filteredProjects = projects;
    if (filterSelection) {
      filteredProjects = this.filterProjects(filteredProjects, filterSelection);
    }
    if (latLngBounds) {
      filteredProjects = this.filterLatLngNotInBounds(filteredProjects, latLngBounds);
    }
    return filteredProjects;
  }

  private filterLatLngNotInBounds(projects: Project[], latLngBounds: LatLngBounds): Project[] {
    return projects.filter(p => latLngBounds.contains(
      {
        lat: p.location.latitude,
        lng: p.location.longitude
      } as unknown as LatLng
    ));
  }

  private filterProjects(projects: Project[], filterSelection: ProjectFilterSelection): Project[] {
    let filteredProjects = this.applyFilter(projects, filterSelection.zuehlkeCompany, 'zuehlkeCompany');
    filteredProjects = this.applyFilter(filteredProjects, filterSelection.industry, 'industry');
    filteredProjects = this.applySkillsFilter(filteredProjects, filterSelection.skills);
    filteredProjects = this.applyDistributedFilter(filteredProjects, filterSelection.distributed);
    filteredProjects = this.applyEmployeesFilter(filteredProjects, filterSelection.employees);
    return filteredProjects;
  }

  private applyFilter(projects: Project[], filterSelection: string[], key: keyof ProjectFilterSelection): Project[] {
    return projects.filter(p => filterSelection.length === 0 || filterSelection.includes(p[key]));
  }

  private applySkillsFilter(projects: Project[], filterSelection: string[]): Project[] {

    return projects.filter(p => filterSelection.length === 0
      || filterSelection.reduce((previous, skill) => previous && p.skills.includes(skill), true));
  }

  private applyDistributedFilter(projects: Project[], filterSelection: boolean): Project[] {
    return projects.filter(p => filterSelection === undefined || filterSelection === p.distributed);
  }

  private applyEmployeesFilter(projects: Project[], filterSelection: NumberOfEmployees): Project[] {
    let filterFunction: (p: Project) => boolean;
    switch (filterSelection) {
      case NumberOfEmployees.LessOrEqualThan5: {
        filterFunction = (p: Project) => p.amountOfEmployees <= 5;
        break;
      }
      case NumberOfEmployees.LessOrEqualThan10: {
        filterFunction = (p: Project) => p.amountOfEmployees <= 10;
        break;
      }
      case NumberOfEmployees.LessOrEqualThan50: {
        filterFunction = (p: Project) => p.amountOfEmployees <= 50;
        break;
      }
      case NumberOfEmployees.LessOrEqualThan100: {
        filterFunction = (p: Project) => p.amountOfEmployees <= 100;
        break;
      }
      case NumberOfEmployees.MoreThan100: {
        filterFunction = (p: Project) => p.amountOfEmployees > 100;
        break;
      }
      default:
        return projects;
    }

    return projects.filter(filterFunction);
  }
}
