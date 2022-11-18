import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { CreateComponent } from './create/create.component';
import { OverviewComponent } from './overview/overview.component';

@NgModule({
  declarations: [CreateComponent, OverviewComponent],
  imports: [CommonModule, AdminRoutingModule],
})
export class AdminModule { }
