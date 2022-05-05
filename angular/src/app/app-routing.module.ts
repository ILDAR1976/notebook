import {
  NgModule
} from '@angular/core';
import {
  RouterModule,
  Routes
} from '@angular/router';
import {
  MainComponent
} from './secret/main/main.component';
import {
  LoginPageComponent
} from './auth/login-page/login-page.component';
import {
  AuthGuard
} from './auth/auth.guard';
import {
  ErrorPageComponent
} from './error-page/error-page.component';
import {
  UserComponent
} from './components/user/user.component';


const routes: Routes = [
  {
    path: 'userpage',
    component: UserComponent
  },
  {
    path: 'main',
    canLoad: [AuthGuard],
    loadChildren: () => import('./secret/secret.module').then(m => m.SecretModule)
  },
  {
    path: 'admin',
    canLoad: [AuthGuard],
    loadChildren: () => import('./admin/admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'login',
    component: LoginPageComponent,
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: ErrorPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
