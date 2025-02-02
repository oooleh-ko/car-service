import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CarRent } from '../models/car-rent.model';

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  private apiUrl = 'http://localhost:8079/api/rentals';

  constructor(private http: HttpClient) { }

  rentCar(carId: number, customerId: number, startDate: Date, endDate: Date): Observable<CarRent> {
    return this.http.post<CarRent>(this.apiUrl, {
      carId,
      customerId,
      startDate,
      endDate
    });
  }
} 