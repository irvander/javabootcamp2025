import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'demo-ng';

  // constructor(out: LoggerService) {
  //   out.error('Error')
  //   out.warn('Warn')
  //   out.info('Info')
  //   out.log('Log')
  // }
}
