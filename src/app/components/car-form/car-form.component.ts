import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { ImageService } from '../../services/image.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html'
})
export class CarFormComponent implements OnInit {
  carForm: FormGroup;
  error: string = '';
  isEditMode = false;
  carId?: number;
  currentYear = new Date().getFullYear();
  loading = false;

  constructor(
    private fb: FormBuilder,
    private carService: CarService,
    private imageService: ImageService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.carForm = this.fb.group({
      id: [null],
      brand: ['', Validators.required],
      model: ['', Validators.required],
      vinCode: ['', Validators.required],
      productionYear: ['', [Validators.required, Validators.min(1900), Validators.max(this.currentYear)]],
      imageUrl: ['']
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.carId = +id;
      this.loadCar(this.carId);
    }
  }

  private loadCar(id: number): void {
    this.carService.getCar(id).subscribe({
      next: (car) => {
        this.carForm.patchValue(car);
      },
      error: (err) => {
        console.error('Error loading car:', err);
        this.error = 'Failed to load car details.';
      }
    });
  }

  onSubmit(): void {
    if (this.carForm.valid) {
      this.carService.saveCar(this.carForm.value).subscribe({
        next: () => {
          this.router.navigate(['/cars']);
        },
        error: (err) => {
          console.error('Error saving car:', err);
          this.error = 'Failed to save car. Please try again.';
        }
      });
    }
  }

  getRandomImage(): void {
    this.loading = true;
    this.imageService.getRandomCarImage().subscribe({
      next: (imageUrl) => {
        this.carForm.patchValue({ imageUrl });
        this.loading = false;
      },
      error: (err) => {
        console.error('Error getting random image:', err);
        this.error = 'Failed to get random image.';
        this.loading = false;
      }
    });
  }
}