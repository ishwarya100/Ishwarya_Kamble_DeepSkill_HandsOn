import React from "react";
import CohortDetails from "./CohortDetails";
import CohortData from "./Cohort";

// App component renders a CohortDetails box for every cohort in the mock data
function App() {
  return (
    <div>
      <h1>Cohort Dashboard</h1>
      {CohortData.map((cohort) => (
        <CohortDetails key={cohort.id} cohort={cohort} />
      ))}
    </div>
  );
}

export default App;
