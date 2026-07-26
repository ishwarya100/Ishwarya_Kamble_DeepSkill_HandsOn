import React from "react";

// team players split into odd and even positions
const teamPlayers = ["Rohit", "Virat", "Shubman", "Rishabh", "Hardik", "Ravindra"];
const [odd1, even1, odd2, even2, odd3, even3] = teamPlayers;
const oddTeamPlayers = [odd1, odd2, odd3];
const evenTeamPlayers = [even1, even2, even3];

// two arrays merged using the ES6 spread feature
const T20players = ["Rohit", "Virat", "Suryakumar"];
const RanjiTrophyPlayers = ["Prithvi", "Mayank", "Shreyas"];
const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

// component that displays odd/even team players and the merged player list
function IndianPlayers() {
  return (
    <div>
      <h2>Odd Team Players</h2>
      <p>{oddTeamPlayers.join(", ")}</p>
      <h2>Even Team Players</h2>
      <p>{evenTeamPlayers.join(", ")}</p>
      <h2>Merged T20 and Ranji Trophy Players</h2>
      <p>{mergedPlayers.join(", ")}</p>
    </div>
  );
}

export default IndianPlayers;
