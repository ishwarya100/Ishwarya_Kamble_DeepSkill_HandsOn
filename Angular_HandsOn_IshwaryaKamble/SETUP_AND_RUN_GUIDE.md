# Setup and Run Guide

## Prerequisites

- Node.js 18 or newer (the project was built and tested with Node 22)
- npm (comes bundled with Node.js)

You do **not** need to install a database, a backend server, or any API keys. The "backend" is fully simulated in the browser (see `PROJECT_NOTES.md` section 3).

> **Note on accounts:** registration and login are also simulated in the browser, with no real server and no password hashing. Refreshing the page clears every account. This is fine for the exercise, just don't expect accounts to survive a refresh.

## 1. Install dependencies

Unzip the project, then open a terminal inside the project folder:

```bash
cd student-course-portal
npm install
```

This installs Angular, NgRx, and `angular-in-memory-web-api` (the mock backend library).

## 2. (Optional) Install the Angular CLI globally

The project already has the CLI as a local dev dependency, so this step is optional. If you want the global `ng` command available everywhere:

```bash
npm install -g @angular/cli@20
```

If you skip this, use `npx ng <command>` instead of `ng <command>` everywhere below.

## 3. Run the app in development mode

```bash
ng serve
```

Then open your browser at:

```
http://localhost:4200
```

The app will hot-reload automatically whenever you edit a file.

## 4. Build a production bundle

```bash
ng build
```

The compiled output is written to `dist/student-course-portal/`. You can serve that folder with any static file server, for example:

```bash
npx http-server dist/student-course-portal/browser -p 8080
```

## 5. Run the unit tests

```bash
ng test
```

This opens Karma and runs every `*.spec.ts` file in a real Chrome browser (it will try to launch Chrome on your machine). Use `ng test --watch=false` for a single run instead of watch mode.

## 6. Project layout reminder

```
student-course-portal/
  src/app/            all application source code
  PROJECT_NOTES.md     full explanation of the project
  SETUP_AND_RUN_GUIDE.md   this file
  README.md            quick start summary
```

## 7. Trying out the app

Once it's running, here's a quick tour:

1. **Register** — create an account with your name, email, gender, and a password. You're logged in right away.
2. **Home** — see your personalised welcome message, the dashboard, type in the search box, click "Enroll Now".
3. **Courses** — browse the course cards, search to filter them live, hover to see the highlight directive, click "Show Details", click a card to open its detail page.
4. **Profile** — protected route, shows your real name and email, plus your enrolled courses (enroll in a course first to see it appear here).
5. **Enroll** — fill out the simple form with a real course id (1 to 12), submit to actually enroll in that course.
6. **Enroll (Reactive)** — try entering a course id starting with "XX" to see the custom validator fire, or an email containing "test@" to see the simulated async check. A valid course id enrolls you the same way.
7. **Logout** — click Logout in the header, then log back in with the same email and password to confirm your account is still there (until you refresh the page).

## 8. Troubleshooting

- **Port 4200 already in use** — run `ng serve --port 4300` and open that port instead.
- **`ng` command not found** — use `npx ng serve` instead, since the CLI is installed locally in `node_modules`.
- **Tests fail to launch Chrome** — make sure Google Chrome or Chromium is installed on your machine; Karma needs a real browser to run tests in.
