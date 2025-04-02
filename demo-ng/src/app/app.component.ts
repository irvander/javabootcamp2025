import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';
import { NotificationComponent } from './MainModule/main';
import { DemosComponent } from './demos/demos.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NotificationComponent, DemosComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'demo-ng';
}
