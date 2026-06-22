import { TestBed } from '@angular/core/testing';
import { CanDeactivateFn } from '@angular/router';
import { unsavedChangesGuard, UnsavedChangesComponent } from './unsaved-changes.guard';

describe('unsavedChangesGuard', () => {
  const executeGuard: CanDeactivateFn<UnsavedChangesComponent> = (...guardParameters) =>
    TestBed.runInInjectionContext(() => unsavedChangesGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should allow navigation when there are no unsaved changes', () => {
    const component: UnsavedChangesComponent = { hasUnsavedChanges: () => false };
    const result = executeGuard(component, {} as any, {} as any, {} as any);
    expect(result).toBeTrue();
  });

  it('should prompt the user when there are unsaved changes', () => {
    spyOn(window, 'confirm').and.returnValue(true);
    const component: UnsavedChangesComponent = { hasUnsavedChanges: () => true };
    const result = executeGuard(component, {} as any, {} as any, {} as any);
    expect(window.confirm).toHaveBeenCalled();
    expect(result).toBeTrue();
  });
});
