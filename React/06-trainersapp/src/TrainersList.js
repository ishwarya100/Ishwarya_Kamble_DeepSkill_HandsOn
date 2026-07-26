import React from "react";
import { Link } from "react-router-dom";

// renders the list of trainer names as clickable links to their detail page
function TrainersList(props) {
  const { trainers } = props;

  return (
    <ul>
      {trainers.map((trainer) => (
        <li key={trainer.trainerId}>
          <Link to={`/trainers/${trainer.trainerId}`}>{trainer.name}</Link>
        </li>
      ))}
    </ul>
  );
}

export default TrainersList;
