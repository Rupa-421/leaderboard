import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {IonicModule} from '@ionic/angular';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';

import {ComponentsModule} from '../../components/components.module';
import {CustomPipesModule} from '../../pipe/custom-pipes.module';
import {DetailsPage} from './details/details.page';
import {MainPage} from './main/main.page';
import {UserPageRoutingModule} from './user-routing.module';
import {UserPage} from './user.page';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        IonicModule,
        ComponentsModule,
        CustomPipesModule,
        UserPageRoutingModule,
        BsDatepickerModule.forRoot(),
    ],
    declarations: [UserPage, MainPage, DetailsPage],
})
export class UserPageModule {
}
