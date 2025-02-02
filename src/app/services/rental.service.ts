import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CarRent } from '../models/car-rent.model';
import { API_ENDPOINTS } from '../shared/constants';

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  constructor(private http: HttpClient) { }

  rentCar(carId: number, customerId: number, startDate: Date, endDate: Date): Observable<CarRent> {
    return this.http.post<CarRent>(API_ENDPOINTS.RENTALS, {
      carId: Number(carId),
      customerId,
      startDate,
      endDate
    });
  }
} 