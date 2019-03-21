import {Location} from './Location';

export interface ProjectDto {
  location: Location;
  title: string;
  zuehlkeCompany: string;
  projectCode: string;
  industry: string;
  skills: string[];
  pictureURL: string;
  logoURL: string;
  projectURL: string;
  amountOfEmployees: number;
  isDistributed: boolean;
  projectDescription: string;
}

export interface Project extends ProjectDto {
  isSelected: boolean;
}
