import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { OverviewComponent } from './overview/overview.component';

const routes: Routes = [
  {
    component: OverviewComponent,
    path: 'overview',
  },
  {
    component: CreateComponent,
    path: 'create',
  },
  {
    component: CreateComponent,
    path: 'update',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule { }
