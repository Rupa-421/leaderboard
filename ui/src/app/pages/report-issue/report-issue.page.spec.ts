import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ReportIssuePage } from './report-issue.page';
import {RouterTestingModule} from '@angular/router/testing';
import {ComponentsModule} from '../../components/components.module';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../../../environments/environment';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireAuthModule} from '@angular/fire/auth';

describe('ReportIssuePage', () => {
  let component: ReportIssuePage;
  let fixture: ComponentFixture<ReportIssuePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportIssuePage ],
      imports: [
        IonicModule.forRoot(),
        RouterTestingModule,
        ComponentsModule,
        RouterTestingModule,
        AngularFireModule.initializeApp(environment.firebaseConfig, 'angular-auth-firebase'),
        AngularFirestoreModule,
        AngularFireAuthModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ReportIssuePage);
    component = fixture.componentInstance;
  }));

  it('should call window.open with form url', () => {
    spyOn(window, 'open');
    component.openKeka();
    expect(window.open).toHaveBeenCalledWith(component.kekaUrl, '_blank');
  });
});
