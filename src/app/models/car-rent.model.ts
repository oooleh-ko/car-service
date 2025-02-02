export interface CarRent {
    id?: number;
    carId: number;
    customerId: number;
    startDate: Date;
    endDate: Date;
    createdAt?: Date;
    updatedAt?: Date;
} 