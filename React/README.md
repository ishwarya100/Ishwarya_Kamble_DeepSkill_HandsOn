# React JS Hands-On Labs - Answer Sheet

This folder contains the completed solution for all 19 React hands-on labs.
Each lab is a separate, independent React application built with
create-react-app, matching the app name and requirements given in each lab
document.

## Folder structure

```
REACT/
  01-myfirstreact/
  02-studentapp/
  03-scorecalculatorapp/
  04-blogapp/
  05-cohortdetails-styling/
  06-trainersapp/
  07-shoppingapp/
  08-counterapp/
  09-cricketapp/
  10-officespacerentalapp/
  11-eventexamplesapp/
  12-ticketbookingapp/
  13-bloggerapp/
  14-employeetheme-context/
  15-ticketraisingapp/
  16-mailregisterapp/
  17-fetchuserapp/
  18-cohortdetails-testing/
  19-gitclientapp/
```

Every folder is a complete, standalone create-react-app project (its own
`package.json`, `public/`, and `src/`). None of them share dependencies, so
each one is installed and run separately.

## Prerequisites

- Node.js (LTS version) and npm installed
- Visual Studio Code (or any code editor)

## How to run any lab

Open a terminal in the lab folder you want to run, then:

```
npm install
npm start
```

This opens the app at `http://localhost:3000`. Only one app can run on port
3000 at a time, so stop one (Ctrl+C) before starting another, or pass a
different port: `PORT=3001 npm start`.

## Lab-by-lab notes

**01-myfirstreact** - Basic setup, renders the welcome heading.

**02-studentapp** - Home, About and Contact class components rendered from
`src/Components/`.

**03-scorecalculatorapp** - `CalculateScore` function component computes and
displays a student's average score. Includes `Stylesheets/mystyle.css`.

**04-blogapp** - `Posts` class component fetches posts from
jsonplaceholder.typicode.com in `componentDidMount()`, and reports errors via
`componentDidCatch()`.

**05-cohortdetails-styling** - The lab document says to download a starter
"CohortDetails" app, but the file was not actually included in the provided
zip (only an embedded attachment icon). I built the CohortDetails dashboard
component and mock cohort data from scratch, then applied the CSS Module
styling exactly as instructed (`.box` class, `<dt>` tag selector, green/blue
`<h3>` status color).

**06-trainersapp** - Uses `react-router-dom` v6 (`BrowserRouter`, `Routes`,
`Route`, `Link`, `useParams`), which is the current standard router syntax
and matches what the lab asks for. Routes: `/` (Home) and `/trainers`
(TrainersList), with `/trainers/:id` for TrainerDetail.

**07-shoppingapp** - `Cart` and `OnlineShopping` class components, 5 cart
items looped and displayed.

**08-counterapp** - `CountPeople` class component with `entrycount` and
`exitcount` in state, Login/Exit buttons.

**09-cricketapp** - `ListofPlayers` (map + filter with arrow functions) and
`IndianPlayers` (destructuring + array merge with spread) toggled by a
`flag` variable.

**10-officespacerentalapp** - JSX element/attribute examples, an office
object, a looped office list, and conditional red/green rent coloring. Uses
a placeholder image URL since no image asset was provided in the lab files.

**11-eventexamplesapp** - `EventExamples` component (increment/decrement,
argument passing, synthetic click event) plus a separate
`CurrencyConvertor` component (INR to Euro on submit).

**12-ticketbookingapp** - `GuestPage` and `UserPage` toggled by a
`isLoggedIn` state flag via Login/Logout buttons.

**13-bloggerapp** - `BookDetails` (if/else with an element variable),
`BlogDetails` (ternary), `CourseDetails` (`&&` operator) - three different
conditional rendering techniques as requested.

**14-employeetheme-context** - Same starter-app situation as lab 5 (the
lab references a downloadable Employee Management app that isn't in the
zip), so I built the Employee dashboard from scratch already using
`ThemeContext` end to end: `ThemeContext.js` (`createContext('light')`),
`App.js` provides the value, `EmployeesList.js` no longer passes theme as a
prop, and `EmployeeCard.js` reads it with `useContext()`.

**15-ticketraisingapp** - `ComplaintRegister` component, textbox + textarea,
generates a reference number in an alert on submit.

**16-mailregisterapp** - `register.js` validates name (>= 5 chars), email
(must contain `@` and `.`), and password (>= 8 chars) on submit.

**17-fetchuserapp** - `Getuser` component fetches from
`https://api.randomuser.me/` in `componentDidMount()` and displays title,
first name and photo.

**18-cohortdetails-testing** - Reuses the CohortDetails component and mock
data built in lab 5, with an Enzyme + Jest test suite in
`CohortDetails.test.js` covering all 4 required tests (component creation,
props initialization, `h3` content check, and a snapshot test).

Important: Enzyme has no officially maintained adapter for React 18+, so
this project specifically uses **React 17** with the unofficial
`@wojtekmaj/enzyme-adapter-react-17` adapter, as agreed. During testing I
also found that the `cheerio` version enzyme pulls in by default needs
Node's `undici`/`ReadableStream`, which Jest's jsdom test environment
doesn't provide - so `package.json` pins `cheerio` to `1.0.0-rc.12` via an
`overrides` field to keep it stable. Install this one with:

```
npm install --legacy-peer-deps
npm test
```

**19-gitclientapp** - `GitClient` class uses axios to call
`api.github.com`, `App.js` renders the fetched repository names.
`GitClient.test.js` mocks axios with `jest.mock('axios')` and verifies
`getRepositories()` returns the mocked repository names without making a
real network call.

```
npm install
npm test
```

## Verification performed

Every app (1-17, 19) was installed and built with `npm install` +
`npm run build` to confirm there are no syntax or compile errors. Lab 19's
Jest test and lab 18's 4 Enzyme tests were both run and confirmed passing.
`node_modules`, `build`, and `package-lock.json` were removed afterwards to
keep this submission lightweight - run `npm install` in a lab folder before
using it.
