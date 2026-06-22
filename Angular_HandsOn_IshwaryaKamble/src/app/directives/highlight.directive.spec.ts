import { ElementRef } from '@angular/core';
import { HighlightDirective } from './highlight.directive';

describe('HighlightDirective', () => {
  it('should create an instance', () => {
    const directive = new HighlightDirective(new ElementRef(document.createElement('div')));
    expect(directive).toBeTruthy();
  });

  it('should apply the highlight colour on mouseenter and clear it on mouseleave', () => {
    const el = document.createElement('div');
    const directive = new HighlightDirective(new ElementRef(el));
    directive.appHighlight = 'lightblue';

    directive.onMouseEnter();
    expect(el.style.backgroundColor).toBe('lightblue');

    directive.onMouseLeave();
    expect(el.style.backgroundColor).toBe('');
  });
});
