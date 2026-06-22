import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { IconComponent } from '../../shared/icon/icon.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, IconComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  // static portal name shown in the navigation bar
  portalName = 'Student Course Portal';

  constructor(public authService: AuthService, private router: Router) { }

  // logs the student out and returns to the home page
  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
