import { CanDeactivateFn } from '@angular/router';

// any component using this guard must expose a hasUnsavedChanges check
export interface UnsavedChangesComponent {
  hasUnsavedChanges: () => boolean;
}

// warns the student before leaving a form with unsaved changes
export const unsavedChangesGuard: CanDeactivateFn<UnsavedChangesComponent> = (component) => {
  if (component.hasUnsavedChanges()) {
    return window.confirm('You have unsaved changes. Leave?');
  }
  return true;
};
