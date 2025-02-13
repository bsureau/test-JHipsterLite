import { TimeoutListener, Timeoutable } from '@/shared/toast/domain/Timeout';
import sinon, { SinonStub } from 'sinon';

interface TimeoutListenerStub extends TimeoutListener {
  register: SinonStub<[Timeoutable, number]>;
  unregister: SinonStub;
}

export const stubTimeout = (): TimeoutListenerStub => ({
  register: sinon.stub<[Timeoutable, number]>(),
  unregister: sinon.stub(),
});
