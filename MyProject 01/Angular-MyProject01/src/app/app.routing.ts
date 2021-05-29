import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/no_auth/user/login/login.component';
import { RegistrationComponent } from './components/no_auth/user/registration/registration.component';
import { HomeComponent } from './components/auth/home/home.component';
import { AuthGuard } from './shared/guards/auth-guard';
import { FirstStepsComponent } from './components/auth/first-steps/first-steps.component';


const routes: Routes = [
        { path: "", component: HomeComponent , canActivate: [AuthGuard] },
        { path: "firstSteps", component: FirstStepsComponent , canActivate: [AuthGuard] },
        { path: "login", component: LoginComponent },
        { path: "registration", component: RegistrationComponent },
      ]

export const NxRouting = RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' });