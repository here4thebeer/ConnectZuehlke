export interface ProjectFilterSelection {
  zuehlkeCompany: string[];
  industry: string[];
  distributed: boolean;
  skills: string[];
  employees: NumberOfEmployees;
}

export enum NumberOfEmployees {
  LessOrEqualThan5 = '<= 5',
  LessOrEqualThan10 = '<= 10',
  LessOrEqualThan50 = '<= 50',
  LessOrEqualThan100 = '<= 100',
  MoreThan100 = '> 100'
}

export interface FilterOptions {
  companies: Set<string>;
  industries: Set<string>;
  skills: Set<string>;
  distributionValues: Set<boolean>;
  employeeNumbers: NumberOfEmployees[];
}
