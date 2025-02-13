import { Preset } from '@/module/domain/Preset';
import { RestPresetModuleToApply, mapPresetModulesToApply } from './RestPresetModuleToApply';

export interface RestPreset {
  name: string;
  modules: RestPresetModuleToApply[];
}

export const mapToPreset = (restPreset: RestPreset): Preset => {
  return {
    name: restPreset.name,
    modules: mapPresetModulesToApply(restPreset.modules),
  };
};
