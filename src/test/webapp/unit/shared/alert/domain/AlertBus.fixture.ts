import { AlertBus } from '@/shared/alert/domain/AlertBus';
import sinon, { SinonStub } from 'sinon';

export interface AlertBusFixture extends AlertBus {
  success: SinonStub;
  error: SinonStub;
}

export const stubAlertBus = (): AlertBusFixture => ({
  success: sinon.stub(),
  error: sinon.stub(),
});
