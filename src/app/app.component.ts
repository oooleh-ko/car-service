import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Car Rental Service';
  cities = ['New York', 'Los Angeles', 'Chicago', 'Houston', 'Phoenix'];
  selectedCity = this.cities[0];

  onCityChange(city: string): void {
    this.selectedCity = city;
  }
} 