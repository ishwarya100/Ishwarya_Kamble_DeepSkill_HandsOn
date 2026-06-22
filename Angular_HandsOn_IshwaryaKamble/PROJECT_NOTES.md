# Student Course Portal — Project Notes 

## 1. What This Project Is

The Student Course Portal is a single page application (SPA) built for a college system. A student registers or logs in with their own details, browses a course catalog, views course details, submits enrollment requests through two different form styles, checks their profile and enrolled courses, and sees live notifications. It also demonstrates an admin-style course management flow through the same service layer.

The project was built incrementally across 10 hands-on modules, each one adding a new Angular concept on top of the same codebase:

1. Project setup, file structure, first component
2. Data binding, lifecycle hooks, parent-child communication
3. Directives and pipes (built-in and custom)
4. Template-driven forms and validation
5. Reactive forms, FormBuilder, FormArray, custom validators
6. Services and dependency injection
7. Routing, guards, lazy loading
8. HTTP client, RxJS operators, interceptors
9. NgRx state management (store, actions, reducers, effects, selectors)
10. Unit testing with Jasmine and Karma

Everything lives in one Angular workspace — there is no separate backend server. A mock in-memory API plays the role of the backend so the app works fully offline with no real external API keys.

## 2. How It Works — Frontend

The app is built with **Angular 20** using **standalone components** (no NgModules). Every component, pipe, and directive declares its own imports directly in its `@Component` / `@Pipe` / `@Directive` decorator.

### Folder structure

```
src/app/
  components/        shared, reusable UI building blocks (header, course-card, notification, summary widget)
  pages/              route-level pages (home, login, register, course-list, course-detail, student-profile, not-found, courses-layout)
  features/enrollment/  lazy-loaded enrollment feature (both enrollment forms)
  services/           shared business logic and data access (course, enrollment, auth, loading, notification)
  store/              NgRx state — one folder per feature slice (course, enrollment)
  guards/             route guards (auth guard, unsaved changes guard)
  interceptors/       HTTP interceptors (auth token, error handling, loading spinner)
  directives/         custom attribute directive (highlight)
  pipes/              custom pipe (credit label)
  models/             shared TypeScript interfaces (Course, Student, StudentProfile)
```

### Page flow

- **Home (`/`)** — dashboard with live stats pulled from the course service, a personalised "Welcome back" greeting with a gender-based emoji once logged in, a two-way bound search box, and an Enroll Now button to demonstrate all four Angular binding types.
- **Register (`/register`)** — reactive form with name, email, gender, password, and confirm-password validation. Creates an in-memory student and logs them in immediately.
- **Login (`/login`)** — reactive form that checks email and password against the in-memory student list.
- **Courses (`/courses`)** — lists every course as a card, filterable through the search box. Cards use `*ngFor` with `trackBy`, an `*ngSwitch` badge for grade status, `[ngClass]`/`[ngStyle]` for dynamic styling, and a custom `appHighlight` directive on hover. The list itself is powered by the NgRx store.
- **Course Detail (`/courses/:id`)** — nested route under the courses layout, reads the `:id` route parameter and loads that single course.
- **Profile (`/profile`)** — protected by the auth guard. Shows the logged-in student's own name, email, and GPA, plus their live enrolled courses (resolved through a cross-slice NgRx selector).
- **Enroll (`/enroll`)** — lazy loaded feature module containing the template-driven enrollment form. Submitting enrolls the student in the typed course id, it does not create a new course.
- **Enroll Reactive (`/enroll/reactive`)** — the same lazy chunk, containing the reactive form with custom sync/async validators and a dynamic `FormArray`. Submitting also enrolls the student in the typed course id. Protected from accidental navigation by a `CanDeactivate` guard when the form is dirty.
- **404 page** — wildcard fallback for any unmatched route.

### State management

Course data and enrollment status both live in the **NgRx store**:
- `store/course` — holds the list of courses, a loading flag, and an error message. Populated through an **effect** that calls `CourseService` and dispatches success/failure actions.
- `store/enrollment` — holds the list of enrolled course ids. A cross-slice selector joins this with the course list to produce full enrolled `Course` objects for the profile page.

Components never talk to the store's two slices directly with raw state — they always go through memoised **selectors**, and they never mutate state directly — they always **dispatch actions** that the reducers handle.

### Forms

Two parallel enrollment forms exist on purpose, to demonstrate both approaches Angular supports:
- **Template-driven** (`/enroll`) — the form structure lives in the HTML template using `ngModel`, `#ctrl="ngModel"` references, and built-in validators (`required`, `minlength`, `email`).
- **Reactive** (`/enroll/reactive`) — the form structure lives in the TypeScript class using `FormBuilder`, with a custom synchronous validator (rejects course codes starting with "XX"), a custom asynchronous validator (simulates an email-availability check with an 800ms delay), and a `FormArray` for adding multiple extra courses dynamically.

### Styling

The whole UI uses one fixed color palette defined as CSS variables in `src/styles.css`:

| Color | Hex | Used for |
|---|---|---|
| Royal dark blue | `#1b2a4a` | header, headings, primary buttons |
| Olive green | `#6b8e23` | "passed" badges, valid form fields |
| Rose pink | `#d6336c` | "failed" badges, invalid form fields, errors |
| Orange | `#e8772e` | accent buttons, loading bar |
| White | `#ffffff` | cards, background surfaces |

No external UI library or component kit is used — every button, card, and badge is plain CSS so the styling stays easy to read and change.

## 3. How It Works — "Backend"

The brief asked for no real external APIs. Instead of a real server, the project uses **`angular-in-memory-web-api`** — a library that intercepts every `HttpClient` call (`GET`, `POST`, `PUT`, `DELETE`) made to `api/...` and serves it from an in-memory JavaScript object, entirely inside the browser. There is no server process to start.

This means:
- `CourseService` makes completely normal `HttpClient` calls (`this.http.get<Course[]>('api/courses')`, etc.) exactly the way it would against a real REST API.
- `src/app/services/in-memory-data.service.ts` defines the starting data (12 courses, an empty enrollments list) — this plays the role of a `db.json` file you'd otherwise feed to `json-server`.
- Every request still goes through the real Angular HTTP pipeline, including all three interceptors, so the request/response behaviour matches a real backend integration.

If you ever want to swap this for a real backend, you only need to remove the `HttpClientInMemoryWebApiModule.forRoot(...)` provider in `app.config.ts` and point the `API_URL` in `course.service.ts` at your real API base URL — no other code changes are required.

### Authentication

`AuthService` is a small in-memory store, separate from `angular-in-memory-web-api`. Registered students live in a plain array inside the service for the lifetime of the browser tab. This means:
- There is no real backend, no password hashing, and no persistence.
- Refreshing the page clears every registered account and logs everyone out.
- This is intentional for an exercise project — do not reuse this auth pattern for anything handling real user data.

### Interceptors (run on every HTTP request)

1. **Auth interceptor** — attaches a mock `Authorization: Bearer mock-token-12345` header to every request.
2. **Loading interceptor** — flips a global `LoadingService` flag on before the request and off after, which drives the thin progress bar at the top of the page.
3. **Error handler interceptor** — watches for `401` (redirects home) and `500` (logs a message) responses globally, so individual components don't need to repeat this logic.

## 4. Use Cases Covered

- Student registers with their own name, email, gender, and password, or logs back in.
- Student views their personalised dashboard with a name and gender-based emoji greeting.
- Student searches/browses the course catalog, with the list filtering live as they type.
- Student opens a single course's details by clicking a card.
- Student submits an enrollment request and is actually enrolled in that course id.
- Student submits a more advanced enrollment request with extra courses added dynamically.
- Student views their own profile and the courses they're currently enrolled in.
- Application shows a toast-style notification on load and on key actions.
- Application protects the profile and enroll routes from unauthenticated access.
- Application persists enrollment changes through the mock backend (POST/PUT/DELETE all wired up in `CourseService`).

## 5. Testing

Every service, component, pipe, directive, guard, and interceptor has a matching `*.spec.ts` file using Jasmine syntax (`describe`, `it`, `expect`, `spyOn`) and Angular's `TestBed`. Highlights:
- `CourseCardComponent` tests cover rendering from `@Input`, emitting `@Output` events, and the `ngOnChanges` lifecycle hook.
- `CourseService` tests use `HttpClientTestingModule` to intercept and assert on the exact HTTP calls made, including a simulated 500 error.
- `CourseListComponent` tests use `provideMockStore` to drive the component through different NgRx states (loaded vs loading) and verify the search filter narrows the rendered cards.
- `AuthService` tests cover registering, duplicate email rejection, logging in with correct and incorrect credentials, and logging out.

There are 27 spec files with 61 `it(...)` test cases in total. Run `ng test` locally to execute them against a real headless Chrome — this sandbox could not run Karma since it has no Chrome binary available, but `ng build` and a strict `tsc --noEmit` both passed cleanly on the final code.

## 6. Key Files To Read First

If you want to explore the codebase yourself, read these files in this order:
1. `src/app/app.config.ts` — see everything that gets wired up at startup.
2. `src/app/app.routes.ts` — see every route, guard, and the lazy-loaded feature.
3. `src/app/services/course.service.ts` — see how HTTP + RxJS operators are used.
4. `src/app/store/course/*.ts` — see the NgRx actions/reducer/selectors/effects working together.
5. `src/app/components/course-card/course-card.component.ts` — the most concept-dense component in the app.
