import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

// entry point that bootstraps the root standalone component
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
