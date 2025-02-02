import { Component, OnInit } from '@angular/core';
import { Car } from '../../models/car.model';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];
  error: string = '';
  defaultImageUrl = 'https://placehold.co/600x400/gray/white?text=No+Image';

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

  handleImageError(event: any): void {
    event.target.src = this.defaultImageUrl;
  }
} 