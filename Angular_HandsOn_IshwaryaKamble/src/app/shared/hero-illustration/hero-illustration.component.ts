import { Component } from '@angular/core';

// decorative hero illustration, a stylised stack of study books
@Component({
  selector: 'app-hero-illustration',
  standalone: true,
  template: `
    <svg viewBox="0 0 360 300" class="hero-svg" xmlns="http://www.w3.org/2000/svg">
      <ellipse cx="180" cy="262" rx="130" ry="18" fill="var(--color-rose-light)" opacity="0.6"></ellipse>

      <rect x="70" y="190" width="220" height="34" rx="6" fill="var(--color-lavender)"></rect>
      <rect x="86" y="156" width="188" height="34" rx="6" fill="var(--color-rose)"></rect>
      <rect x="102" y="122" width="156" height="34" rx="6" fill="var(--color-sand-deep)" stroke="var(--color-border)" stroke-width="2"></rect>

      <g transform="translate(180 86)">
        <polygon points="0,-26 70,0 0,26 -70,0" fill="var(--color-lavender-dark)"></polygon>
        <polygon points="-70,0 0,26 0,46 -70,20" fill="var(--color-lavender)"></polygon>
        <polygon points="70,0 0,26 0,46 70,20" fill="var(--color-lavender-dark)"></polygon>
        <circle cx="58" cy="6" r="5" fill="var(--color-rose-light)"></circle>
        <line x1="58" y1="6" x2="58" y2="34" stroke="var(--color-rose-light)" stroke-width="2"></line>
      </g>

      <circle cx="62" cy="64" r="5" fill="var(--color-rose)"></circle>
      <circle cx="300" cy="100" r="7" fill="var(--color-lavender)"></circle>
      <circle cx="284" cy="50" r="4" fill="var(--color-rose)"></circle>
      <path d="M48 110 q10 -14 24 0" stroke="var(--color-lavender)" stroke-width="3" fill="none" stroke-linecap="round"></path>
      <path d="M306 150 q-10 -14 -24 0" stroke="var(--color-rose)" stroke-width="3" fill="none" stroke-linecap="round"></path>
    </svg>
  `,
  styles: [`
    :host { display: block; }
    .hero-svg { width: 100%; height: auto; max-width: 320px; }
  `]
})
export class HeroIllustrationComponent { }
