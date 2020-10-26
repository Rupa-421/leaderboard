import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { TribesPage } from './tribes.page';
import { RouterModule, Routes } from '@angular/router';
import { ComponentsModule } from '../../components/components.module';
import { TribeCardComponent } from './components/tribe-card/tribe-card.component';

const routes: Routes = [
  {
    path: '',
    component: TribesPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule
  ],
  declarations: [TribesPage, TribeCardComponent]
})
export class TribesPageModule {}