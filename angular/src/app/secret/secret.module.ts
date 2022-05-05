import {
  NgModule
} from '@angular/core';
import {
  CommonModule
} from '@angular/common';
import {
  MainComponent
} from './main/main.component';
import {
  SecretRoutingModule
} from './secret-routing.module';


@NgModule({
  declarations: [MainComponent],
  imports: [
    CommonModule,
    SecretRoutingModule
  ]
})
export class SecretModule {}
