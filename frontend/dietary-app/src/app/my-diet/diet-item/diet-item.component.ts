import {Component, Input, OnInit} from '@angular/core';
import {Diet} from "../../models/diet";
import {DietService} from "../../services/DietService";

@Component({
  selector: 'app-diet-item',
  templateUrl: './diet-item.component.html',
  styleUrls: ['./diet-item.component.css']
})
export class DietItemComponent implements OnInit {

  @Input('dietEl') diet: Diet;
  @Input('index') i: number;

  constructor(private dietService: DietService) {  }

  ngOnInit(): void {  }

  roundNumber(number: number, digits: number) {
    return number.toFixed(digits);
  }

  deleteDiet() {
    this.dietService.deleteDiet(this.diet.diet_id);
    window.location.reload();
  }

}
