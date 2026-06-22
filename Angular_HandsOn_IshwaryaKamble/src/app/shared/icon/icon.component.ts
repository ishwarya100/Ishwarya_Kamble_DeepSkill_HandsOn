import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

// central registry of every icon path used across the portal
const ICONS: Record<string, string> = {
  graduation: 'M12 3 1 8l11 5 9-4.09V17h2V8L12 3zM5 13.18v3.64L12 21l7-4.18v-3.64L12 17l-7-3.82z',
  book: 'M4 4.5A2.5 2.5 0 0 1 6.5 2H20v17H6.5A2.5 2.5 0 0 0 4 21.5v-17z M6.5 2A2.5 2.5 0 0 0 4 4.5 M6.5 22A2.5 2.5 0 0 1 4 19.5 M4 19.5v-15',
  search: 'M11 19a8 8 0 1 1 0-16 8 8 0 0 1 0 16zm10 2-5.4-5.4',
  layers: 'm12 2 9 5-9 5-9-5 9-5zM3 12l9 5 9-5 M3 17l9 5 9-5',
  user: 'M12 12a5 5 0 1 0 0-10 5 5 0 0 0 0 10zM4 22c0-4.4 3.6-8 8-8s8 3.6 8 8',
  clipboard: 'M9 4h6a1 1 0 0 1 1 1v1H8V5a1 1 0 0 1 1-1zM6 6h12v15H6zM9 11h6 M9 15h6',
  star: 'm12 2 3.1 6.3 6.9 1-5 4.9 1.2 6.8L12 17.8 5.8 21l1.2-6.8-5-4.9 6.9-1L12 2z',
  arrowRight: 'M5 12h14 M13 5l7 7-7 7',
  arrowLeft: 'M19 12H5 M11 19l-7-7 7-7',
  check: 'M20 6 9 17l-5-5',
  x: 'M18 6 6 18 M6 6l12 12',
  plus: 'M12 5v14 M5 12h14',
  warning: 'M12 9v4 M12 17h.01 M10.3 3.9 1.8 18a2 2 0 0 0 1.7 3h17a2 2 0 0 0 1.7-3L13.7 3.9a2 2 0 0 0-3.4 0z',
  bell: 'M18 8a6 6 0 1 0-12 0c0 7-3 9-3 9h18s-3-2-3-9 M13.7 21a2 2 0 0 1-3.4 0',
  mail: 'M4 4h16v16H4z M4 6l8 7 8-7',
  code: 'm8 6-6 6 6 6 M16 6l6 6-6 6',
  flask: 'M9 3h6 M10 3v6.5L4.5 19a1.5 1.5 0 0 0 1.3 2.3h12.4a1.5 1.5 0 0 0 1.3-2.3L14 9.5V3 M7 16h10',
  cpu: 'M9 2v2 M15 2v2 M9 20v2 M15 20v2 M2 9h2 M2 15h2 M20 9h2 M20 15h2 M6 6h12v12H6z M10 10h4v4h-4z',
  database: 'M12 5c4.4 0 8-1.1 8-2.5S16.4 0 12 0 4 1.1 4 2.5 7.6 5 12 5z M4 2.5V18c0 1.4 3.6 2.5 8 2.5s8-1.1 8-2.5V2.5 M4 9.5c0 1.4 3.6 2.5 8 2.5s8-1.1 8-2.5',
  chat: 'M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v10z',
  brain: 'M8 4a3 3 0 0 0-3 3 3 3 0 0 0-1 5.8 3.5 3.5 0 0 0 3.3 4.7H8a3 3 0 0 0 3-3V7a3 3 0 0 0-3-3z M16 4a3 3 0 0 1 3 3 3 3 0 0 1 1 5.8 3.5 3.5 0 0 1-3.3 4.7H16a3 3 0 0 1-3-3V7a3 3 0 0 1 3-3z',
  sparkle: 'M12 3v4 M12 17v4 M3 12h4 M17 12h4 M5.6 5.6l2.8 2.8 M15.6 15.6l2.8 2.8 M5.6 18.4l2.8-2.8 M15.6 8.4l2.8-2.8',
  trash: 'M4 7h16 M9 7V4h6v3 M6 7l1 13h10l1-13',
  eye: 'M2 12s4-7 10-7 10 7 10 7-4 7-10 7-10-7-10-7z M12 15a3 3 0 1 0 0-6 3 3 0 0 0 0 6z',
  home: 'm3 11 9-8 9 8 M5 10v10h14V10',
  award: 'M12 2a6 6 0 1 0 0 12 6 6 0 0 0 0-12z M8.2 13.5 7 22l5-3 5 3-1.2-8.5'
};

@Component({
  selector: 'app-icon',
  standalone: true,
  imports: [CommonModule],
  template: `
    <svg class="icon" [class]="className" viewBox="0 0 24 24" fill="none" [attr.stroke]="color" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
      <path *ngFor="let segment of paths" [attr.d]="segment"></path>
    </svg>
  `
})
export class IconComponent {
  @Input() name = 'star';
  @Input() className = '';
  @Input() color = 'currentColor';

  // splits a multi-subpath icon definition into separate path elements
  get paths(): string[] {
    const def = ICONS[this.name] ?? ICONS['star'];
    return def.split(/\s(?=M)/);
  }
}
