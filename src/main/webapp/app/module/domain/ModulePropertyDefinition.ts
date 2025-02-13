import { ModulePropertyDefaultValue } from './ModulePropertyDefaultValue';
import { ModulePropertyDefinitionType } from './ModulePropertyDefinitionType';
import { ModulePropertyDescription } from './ModulePropertyDescription';
import { ModulePropertyKey } from './ModulePropertyKey';

export interface ModulePropertyDefinition {
  type: ModulePropertyDefinitionType;
  mandatory: boolean;
  key: ModulePropertyKey;
  description?: ModulePropertyDescription;
  defaultValue?: ModulePropertyDefaultValue;
  order: number;
}
