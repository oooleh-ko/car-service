import { Component, OnInit } from '@angular/core';
import { Car } from '../../models/car.model';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html'
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];
  error: string = '';

  constructor(private carService: CarService) { }

  ngOnInit(): void {
    this.loadCars();
  }

  loadCars(): void {
    this.carService.getCars().subscribe({
      next: (cars) => {
        this.cars = cars;
        this.error = '';
      },
      error: (err) => {
        console.error('Error loading cars:', err);
        this.error = 'Failed to load cars. Please try again later.';
      }
    });
  }
} 