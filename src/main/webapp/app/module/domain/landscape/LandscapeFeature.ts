import { LandscapeElement } from './LandscapeElement';
import { LandscapeFeatureSlug } from './LandscapeFeatureSlug';
import { LandscapeModule } from './LandscapeModule';

export class LandscapeFeature implements LandscapeElement {
  constructor(
    private readonly featureSlug: LandscapeFeatureSlug,
    readonly modules: LandscapeModule[],
  ) {}

  slugString(): string {
    return this.slug().get();
  }

  slug(): LandscapeFeatureSlug {
    return this.featureSlug;
  }

  allModules(): LandscapeModule[] {
    return this.modules;
  }
}
