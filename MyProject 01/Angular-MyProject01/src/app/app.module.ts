import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/no_auth/user/login/login.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from  '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationComponent } from './components/no_auth/user/registration/registration.component';
import { HeaderComponent } from './components/no_auth/header/header/header.component';
import { UserRestDataSourceService, AUTH_URL, RESOURCE_URL } from './rest-api/user-rest-data-source.service';
import { NxRouting } from './app.routing';
import { AuthService } from './shared/services/auth.service';
import { AuthGuard} from './shared/guards/auth-guard';
import { SignButtonToggleService } from './shared/services/sign-button-toggle.service';
import { StorageTokenService } from './shared/services/storage-token.service';
import { NxMaterialModule } from './shared/nx-material/nx-material.module';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { NgxMatFileInputModule } from '@angular-material-components/file-input';
import { LogLevel, LogService, } from './shared/services/log.service';
import { HomeComponent } from './components/auth/home/home.component';
import { FirstStepsComponent } from './components/auth/first-steps/first-steps.component';
import { LocalStorageService } from './shared/services/local-storage.service';
import { UserRepository } from './repository/user-repository';
import { StorageUserService } from './shared/services/storage-user.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    HomeComponent,
    FirstStepsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NxMaterialModule,
    NgxMatFileInputModule,
    NxRouting
  ],
  providers: [
              UserRestDataSourceService,
              { provide: AUTH_URL, useValue: 'http://localhost:4800'},
              { provide: RESOURCE_URL, useValue: 'http://localhost:8080'},
              AuthService,
              AuthGuard,
              SignButtonToggleService,
              StorageTokenService,
              { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
              { provide: LogLevel, useValue: LogLevel.INFO },
              { provide: LogService, 
                deps: [LogLevel],
              useFactory: (level) => {
                  let logger = new LogService();
                  logger.minimumLevel = level;
                  return logger;
              } 
            },
            LocalStorageService,
            UserRepository,
            StorageUserService
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }
