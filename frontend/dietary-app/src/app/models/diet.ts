import {Product} from "./product";

export interface Diet {
  diet_id: number;
  user_id: number;
  product: Product;
  date: Date;
  weight: number;
}
