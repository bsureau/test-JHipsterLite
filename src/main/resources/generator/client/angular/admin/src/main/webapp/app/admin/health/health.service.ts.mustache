import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ApplicationConfigService } from '../../config/application-config.service';
import { Health } from './health.model';

@Injectable({ providedIn: 'root' })
export class HealthService {
  private readonly http = inject(HttpClient);
  private readonly applicationConfigService = inject(ApplicationConfigService);

  checkHealth(): Observable<Health> {
    return this.http.get<Health>(this.applicationConfigService.getEndpointFor('management/health'));
  }
}
