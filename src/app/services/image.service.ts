import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private readonly carColors = [
    'red', 'blue', 'gray', 'black', 'white', 'silver'
  ];

  getRandomCarImage(): Observable<string> {
    const width = 600;
    const height = 400;
    const randomColor = this.carColors[Math.floor(Math.random() * this.carColors.length)];
    const imageUrl = `https://placehold.co/${width}x${height}/${randomColor}/white?text=Car+${randomColor}`;
    return of(imageUrl);
  }
} 