import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RentalService } from '../../services/rental.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-rent-car',
  templateUrl: './rent-car.component.html'
})
export class RentCarComponent implements OnInit {
  rentForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private rentalService: RentalService,
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
    // Get carId from route parameter
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.rentForm.patchValue({
          carId: params['id']
        });
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
          // You might want to show an error message to the user here
        }
      });
    }
  }
} 