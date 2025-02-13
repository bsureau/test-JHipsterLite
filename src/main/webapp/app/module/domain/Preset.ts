import { ModuleSlug } from './ModuleSlug';

type PresetName = string;

export interface Preset {
  name: PresetName;
  modules: ModuleSlug[];
}
