import React from "react";
import EmployeeCard from "./EmployeeCard";

// renders an EmployeeCard for every employee, theme is no longer passed as a prop
function EmployeesList(props) {
  return (
    <div>
      {props.employees.map((employee) => (
        <EmployeeCard key={employee.id} employee={employee} />
      ))}
    </div>
  );
}

export default EmployeesList;
