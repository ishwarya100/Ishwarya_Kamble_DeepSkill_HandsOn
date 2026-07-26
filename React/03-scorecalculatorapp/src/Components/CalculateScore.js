import React from "react";
import "../Stylesheets/mystyle.css";

// function component that computes the average score of a student
function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const average = total / goal;

  return (
    <div className="score-card">
      <h2 className="score-title">Student Score Card</h2>
      <p>Name: {name}</p>
      <p>School: {school}</p>
      <p>Total Marks: {total}</p>
      <p>Number of Subjects: {goal}</p>
      <p>Average Score: {average.toFixed(2)}</p>
    </div>
  );
}

export default CalculateScore;
