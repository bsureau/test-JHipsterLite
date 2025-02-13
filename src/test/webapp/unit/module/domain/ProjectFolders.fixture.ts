import { ProjectFoldersRepository } from '@/module/domain/ProjectFoldersRepository';
import sinon, { SinonStub } from 'sinon';

export interface ProjectFoldersRepositoryStub extends ProjectFoldersRepository {
  get: SinonStub;
}

export const stubProjectFoldersRepository = (): ProjectFoldersRepositoryStub =>
  ({
    get: sinon.stub(),
  }) as ProjectFoldersRepositoryStub;
