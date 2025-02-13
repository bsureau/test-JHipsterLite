import { HarnessLoader } from '@angular/cdk/testing';
import { TestbedHarnessEnvironment } from '@angular/cdk/testing/testbed';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { MatDialogHarness } from '@angular/material/dialog/testing';
import { of } from 'rxjs';

import HealthComponent from './health.component';
import { Health } from './health.model';
import { HealthService } from './health.service';

describe('HealthComponent', () => {
  let comp: HealthComponent;
  let fixture: ComponentFixture<HealthComponent>;
  let service: HealthService;
  let loader: HarnessLoader;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    })
      .overrideTemplate(HealthComponent, '')
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HealthComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(HealthService);
    loader = TestbedHarnessEnvironment.documentRootLoader(fixture);
  });

  describe('refresh', () => {
    it('should call refresh on init', () => {
      const health: Health = { status: 'UP', components: { mail: { status: 'UP', details: { mailDetail: 'mail' } } } };
      jest.spyOn(service, 'checkHealth').mockReturnValue(of(health));

      comp.ngOnInit();

      expect(service.checkHealth).toHaveBeenCalled();

      service.checkHealth().subscribe(() => {
        expect(comp.datasource).toEqual([{ details: { mailDetail: 'mail' }, key: 'mail', status: 'UP' }]);
      });
    });

    it('should call checkHealth on refresh', () => {
      const health: Health = { status: 'UP', components: { mail: { status: 'UP', details: { mailDetail: 'mail' } } } };
      jest.spyOn(service, 'checkHealth').mockImplementation(() => of(health));

      comp.refresh();

      expect(service.checkHealth).toHaveBeenCalled();
    });
  });

  describe('showHealth', () => {
    it('should open dialog', async () => {
      const health: Health = {
        status: 'UP',
        components: null,
      };

      comp.showHealth(health);

      const dialogs = await loader.getAllHarnesses(MatDialogHarness);
      expect(dialogs.length).toBe(1);
    });
  });

  describe('getBadgeClass', () => {
    it('should get success badge class', () => {
      const upBadgeClass = comp.getBadgeClass('UP');

      expect(upBadgeClass).toBe('bg-success');
    });

    it('should get danger badge class', () => {
      const downBadgeClass = comp.getBadgeClass('DOWN');

      expect(downBadgeClass).toBe('bg-danger');
    });
  });
});
