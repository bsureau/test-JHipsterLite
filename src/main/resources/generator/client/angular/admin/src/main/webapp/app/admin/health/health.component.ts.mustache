import { Component, inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';

import { Health, HealthStatus } from './health.model';
import { HealthService } from './health.service';
import { HealthModalComponent } from './modal/health-modal.component';

@Component({
  selector: 'jhi-health',
  templateUrl: './health.component.html',
  imports: [MatDialogModule, MatIconModule, MatButtonModule, MatTableModule],
  styleUrl: './health.component.css',
})
export default class HealthComponent implements OnInit {
  displayedColumns: string[] = ['key', 'value', 'detail'];
  datasource: any = [];

  private readonly dialog = inject(MatDialog);
  private readonly healthService = inject(HealthService);

  ngOnInit(): void {
    this.refresh();
  }

  getBadgeClass(statusState: HealthStatus): string {
    if (statusState === 'UP') {
      return 'bg-success';
    }
    return 'bg-danger';
  }

  refresh(): void {
    this.healthService.checkHealth().subscribe({
      next: health => {
        this.datasource = Object.keys(health.components).map(key => ({
          key,
          status: health.components[key].status,
          details: health.components[key].details,
        }));
      },
    });
  }

  showHealth(health: Health): void {
    this.dialog.open(HealthModalComponent, {
      data: health,
    });
  }
}
