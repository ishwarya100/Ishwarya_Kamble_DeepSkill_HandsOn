import React, { useContext } from "react";
import ThemeContext from "./ThemeContext";

// displays a single employee and reads the theme directly from context
function EmployeeCard(props) {
  const theme = useContext(ThemeContext);

  return (
    <div className={`card-${theme}`}>
      <p>{props.employee.name}</p>
      <p>{props.employee.role}</p>
      <button className={`btn-${theme}`}>View Profile</button>
    </div>
  );
}

export default EmployeeCard;
