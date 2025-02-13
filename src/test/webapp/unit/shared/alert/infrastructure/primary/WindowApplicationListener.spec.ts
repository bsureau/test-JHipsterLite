import { WindowApplicationListener } from '@/shared/alert/infrastructure/primary/WindowApplicationListener';
import { describe, expect, it, vi } from 'vitest';

describe('WindowApplicationListener', () => {
  it('should add event listener on window', () => {
    vi.spyOn(window, 'addEventListener').mockImplementation(() => {});

    const windowApplicationListener = new WindowApplicationListener(window);

    windowApplicationListener.addEventListener('success', () => {});

    expect(window.addEventListener).toHaveBeenCalledOnce();
  });

  it('should remove event listener on window', () => {
    vi.spyOn(window, 'removeEventListener').mockImplementation(() => {});

    const windowApplicationListener = new WindowApplicationListener(window);

    windowApplicationListener.removeEventListener('success', () => {});

    expect(window.removeEventListener).toHaveBeenCalledOnce();
  });
});
