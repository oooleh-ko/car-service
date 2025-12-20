import { Component } from '@angular/core';
import { CarService } from './services/car.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public title = 'Car Rental Service';
  public cities = ['New York', 'Los Angeles', 'Chicago', 'Houston', 'Phoenix'];
  public selectedCity!: string;

  constructor(private carService: CarService) {
    this.changeCity(this.cities[0]);
  }

  public changeCity(city: string): void {
    this.selectedCity = city;
    this.carService.selectCity(city);
  }
} 