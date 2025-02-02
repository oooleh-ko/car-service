import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { CarListComponent } from './components/car-list/car-list.component';
import { RentCarComponent } from './components/rent-car/rent-car.component';
import { CarFormComponent } from './components/car-form/car-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CarListComponent,
    RentCarComponent,
    CarFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '', redirectTo: '/cars', pathMatch: 'full' },
      { path: 'cars', component: CarListComponent },
      { path: 'cars/new', component: CarFormComponent },
      { path: 'cars/edit/:id', component: CarFormComponent },
      { path: 'rent/:id', component: RentCarComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { } 