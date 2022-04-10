import { Component, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartData, ChartEvent, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import {DatePipe} from "@angular/common";
import {DietService} from "../../services/DietService";
import {Observable} from "rxjs";
import {Diet} from "../../models/diet";
import {document} from "ngx-bootstrap/utils";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent {
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  todayDate = Date.now();
  dates = [this.datePipe.transform(this.todayDate-345600000, "YYYY-MM-dd"), this.datePipe.transform(this.todayDate-259200000, "YYYY-MM-dd"),
    this.datePipe.transform(this.todayDate-172800000, "YYYY-MM-dd"), this.datePipe.transform(this.todayDate-86400000, "YYYY-MM-dd"),
    this.datePipe.transform(this.todayDate, "YYYY-MM-dd")];

  kcals = [0, 0, 0, 0, 0];

  dietElements: Observable<Diet[]>;

  constructor(private datePipe: DatePipe, private dietService: DietService) {
    this.onSearchDiet();
  }

  public barChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: 0.1
      }
    },
    // We use these empty structures as placeholders for dynamic theming.
    scales: {
      x: {},
      y: {
        min: 0,
      }
    },
    plugins: {
      legend: { display: true },
    }
  };
  public barChartLabels = this.dates;
  public barChartType: ChartType = 'bar';

  public barChartData: ChartData<'bar'> = {
    labels: this.barChartLabels,
    datasets: [{
        data: this.kcals, label: 'Ilość kalorii',
        backgroundColor: '#f0ad4e',
        borderColor: '#f0ad4e',
      }]
  };

  public chartClicked({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    // console.log(event, active);
  }

  public chartHovered({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    // console.log(event, active);
  }

  public randomize(): void {
    this.barChartType = this.barChartType === 'bar' ? 'line' : 'bar';
    const btn = document.getElementById("displayChart").innerHTML = "Zmień typ";
  }

  searchDiet(date: string | null) {
    let id = JSON.parse(<string>localStorage.getItem('user')).id;
    // @ts-ignore
    return this.dietService.getForUserForDate(id, date);
  }

  onSearchDiet() {
    this.dates.forEach((value, index) =>
      {
        this.dietElements = this.searchDiet(value);
        // @ts-ignore
        this.dietElements.forEach( i => i.forEach( i => {
        this.kcals[index] += Math.round(i.product.kcal * i.weight / 100)
        }))
      }
    )
  }

}
