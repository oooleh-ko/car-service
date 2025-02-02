import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RentalService } from '../../services/rental.service';
import { CarService } from '../../services/car.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Car } from '../../models/car.model';

@Component({
  selector: 'app-rent-car',
  templateUrl: './rent-car.component.html'
})
export class RentCarComponent implements OnInit {
  rentForm: FormGroup;
  error: string = '';
  car?: Car;
  defaultImageUrl = 'https://placehold.co/600x400/gray/white?text=No+Image';

  constructor(
    private fb: FormBuilder,
    private rentalService: RentalService,
    private carService: CarService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.rentForm = this.fb.group({
      carId: ['', Validators.required],
      customerId: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  ngOnInit() {
    const carId = this.route.snapshot.paramMap.get('id');
    if (carId) {
      this.loadCar(+carId);
      this.rentForm.patchValue({ carId: carId });
    }
  }

  private loadCar(id: number): void {
    this.carService.getCar(id).subscribe({
      next: (car) => {
        this.car = car;
      },
      error: (err) => {
        console.error('Error loading car:', err);
        this.error = 'Failed to load car details.';
      }
    });
  }

  onSubmit(): void {
    if (this.rentForm.valid) {
      const formValue = this.rentForm.value;
      this.rentalService.rentCar(
        formValue.carId,
        formValue.customerId,
        formValue.startDate,
        formValue.endDate
      ).subscribe({
        next: () => {
          this.router.navigate(['/cars']);
        },
        error: (err) => {
          console.error('Error renting car:', err);
          this.error = 'Failed to rent car. Please try again.';
        }
      });
    }
  }

  handleImageError(event: any): void {
    event.target.src = this.defaultImageUrl;
  }
} 