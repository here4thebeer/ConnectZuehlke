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
  distributed: boolean;
  projectDescription: string;
}

export interface Project extends ProjectDto {
  isSelected: boolean;
  isFavorite: boolean;
  commuteDistance: number;
  commuteDuration: number;
}
