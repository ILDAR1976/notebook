import { NgModule, Provider } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './auth/login-page/login-page.component';
import { AuthInterceptor } from './auth/auth.interceptor';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { ErrorPageComponent } from './error-page/error-page.component';

const INTERCEPTOR_AUTH_PROVIDER: Provider = {
  provide: HTTP_INTERCEPTORS,
  multi: true,
  useClass: AuthInterceptor
};

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    ErrorPageComponent,
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    INTERCEPTOR_AUTH_PROVIDER,
    AuthService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
