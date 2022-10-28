import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TheHeaderComponent } from './the-header/the-header.component';

@NgModule({
  declarations: [TheHeaderComponent],
  imports: [CommonModule],
  exports: [TheHeaderComponent],
})
export class LayoutModule { }
