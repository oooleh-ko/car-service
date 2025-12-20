import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../models/car.model';
import { API_ENDPOINTS } from '../shared/constants';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  public city!: string;

  constructor(private http: HttpClient) { }

  selectCity(city: string): void {
    this.city = city;
  }

  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>(`${API_ENDPOINTS.CARS}`, {
      params: this.city ? {
        city: this.city
      } : {}
    });
  }

  getCar(id: number): Observable<Car> {
    return this.http.get<Car>(`${API_ENDPOINTS.CARS}/${id}`);
  }

  saveCar(car: Car): Observable<Car> {
    if (car.id) {
      return this.http.put<Car>(`${API_ENDPOINTS.CARS}/${car.id}`, car);
    }
    return this.http.post<Car>(API_ENDPOINTS.CARS, car);
  }
} 