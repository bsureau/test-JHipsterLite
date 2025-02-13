import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { HealthModalComponent } from './health-modal.component';

describe('HealthModalComponent', () => {
  let comp: HealthModalComponent;
  let fixture: ComponentFixture<HealthModalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      providers: [{ provide: MAT_DIALOG_DATA, useValue: {} }],
    })
      .overrideTemplate(HealthModalComponent, '')
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HealthModalComponent);
    comp = fixture.componentInstance;
  });

  describe('readableValue', () => {
    it('should return stringify value', () => {
      comp.healthDetails = {
        key: 'hystrix',
        status: 'UP',
      };

      const result = comp.readableValue({ name: 'jhipster' });

      expect(result).toBe('{"name":"jhipster"}');
    });

    it('should return string value', () => {
      comp.healthDetails = {
        key: 'hystrix',
        status: 'UP',
      };

      const result = comp.readableValue('jhipster');

      expect(result).toBe('jhipster');
    });

    it('should return storage space in a human readable unit (GB)', () => {
      comp.healthDetails = {
        key: 'diskSpace',
        status: 'UP',
      };

      const result = comp.readableValue(1073741825);

      expect(result).toBe('1.00 GB');
    });

    it('should return storage space in a human readable unit (MB)', () => {
      comp.healthDetails = {
        key: 'diskSpace',
        status: 'UP',
      };

      const result = comp.readableValue(1073741824);

      expect(result).toBe('1024.00 MB');
    });

    it('should return string value', () => {
      comp.healthDetails = {
        key: 'mail',
        status: 'UP',
      };

      const result = comp.readableValue(1234);

      expect(result).toBe('1234');
    });
  });
});
