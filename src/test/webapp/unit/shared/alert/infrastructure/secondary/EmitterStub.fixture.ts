import { Emitter } from 'mitt';
import { vi } from 'vitest';

interface EmitterStub extends Emitter<any> {
  emit: vi.fn;
}

export const stubEmitter = (): EmitterStub =>
  ({
    emit: vi.fn(),
  }) as EmitterStub;
