import React, { useState } from "react";
import ThemeContext from "./ThemeContext";
import EmployeesList from "./EmployeesList";
import EmployeeMock from "./EmployeeMock";

// App component provides the theme value to the entire component tree
function App() {
  const [theme, setTheme] = useState("light");

  return (
    <ThemeContext.Provider value={theme}>
      <div>
        <h1>Employee Management</h1>
        <button onClick={() => setTheme(theme === "light" ? "dark" : "light")}>
          Toggle Theme
        </button>
        <EmployeesList employees={EmployeeMock} />
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
