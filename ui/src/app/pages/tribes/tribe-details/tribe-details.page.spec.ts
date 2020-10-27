import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TribeDetailsPage } from './tribe-details.page';
import {LabeledNumberCircleComponent} from '../components/labeled-number-circle/labeled-number-circle.component';
import {ActivatedRoute} from '@angular/router';
import {of} from 'rxjs';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {ComponentsModule} from '../../../components/components.module';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../../../../environments/environment';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireAuthModule} from '@angular/fire/auth';
import {EmployeeActivityService} from '../../../services/employee-activity.service';
import {Inject} from '@angular/core';
import {TribeDetailsModel} from '../../../models/tribe-details.model';

describe('TribeDetailsPage', () => {
  let component: TribeDetailsPage;
  let fixture: ComponentFixture<TribeDetailsPage>;
  let mockEmployeeActivitySerivce: EmployeeActivityService;
  const mockTribeDetailsData: TribeDetailsModel = {
    name: 'DevOps',
    tribeSummery: [
      {name: 'all time score', value: 2000},
      {name: 'monthly score', value: 200},
      {name: 'Per member score', value: 20},
    ],
    allTimeScoreBreakdown: [
      {contributionType: 'Blog', contributionScore: 45},
      {contributionType: 'Knolx', contributionScore: 4},
      {contributionType: 'webinar', contributionScore: 70}
    ],
    trends: [
      {
        month: 'JUNE',
        year: 2020,
        blogScore: 30,
        knolxScore: 20,
        webinarScore: 34,
        techHubScore: 20,
        osContributionScore: 30,
        conferenceScore: 100,
        bookScore: 100,
        researchPaperScore: 0,
      },
      {
        month: 'JULY',
        year: 2020,
        blogScore: 30,
        knolxScore: 20,
        webinarScore: 34,
        techHubScore: 20,
        osContributionScore: 20,
        conferenceScore: 0,
        bookScore: 0,
        researchPaperScore: 100,
      }
    ],
    memberReputations: [
      {
        knolderId: 1,
        knolderName: 'mark',
        allTimeScore: 10,
        allTimeRank: 7,
        quarterlyStreak: '5-6-7',
        monthlyScore: 10,
        monthlyRank: 1,
      }, {
        knolderId: 2,
        knolderName: 'sam',
        allTimeScore: 20,
        allTimeRank: 6,
        quarterlyStreak: '5-6-8',
        monthlyScore: 10,
        monthlyRank: 1,
      }
    ]
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TribeDetailsPage, LabeledNumberCircleComponent ],
      imports: [
        HttpClientTestingModule,
        IonicModule.forRoot(),
        RouterTestingModule,
        ComponentsModule,
        AngularFireModule.initializeApp(environment.firebaseConfig, 'angular-auth-firebase'),
        AngularFirestoreModule,
        AngularFireAuthModule
      ],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            queryParams: of({
              id: 'dev-ops'
            })
          }
        }]
    }).compileComponents();

    fixture = TestBed.createComponent(TribeDetailsPage);
    component = fixture.componentInstance;
    mockEmployeeActivitySerivce = TestBed.get(EmployeeActivityService);
  }));

  it('should get tribe id from params and call getTribeDetails', () => {
    spyOn(component, 'getTribeDetails');
    component.ngOnInit();
    expect(component.tribeId).toEqual('dev-ops');
    expect(component.getTribeDetails).toHaveBeenCalled();
  });

  it('should get tribe detail data from API', () => {
    spyOn(mockEmployeeActivitySerivce, 'getTribeDetails').and.returnValue(of({...mockTribeDetailsData}));
    component.getTribeDetails('dev-ops');
    expect(component.tribeDetails).toEqual(mockTribeDetailsData);
  });
});