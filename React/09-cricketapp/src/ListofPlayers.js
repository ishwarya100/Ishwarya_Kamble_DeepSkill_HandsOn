import React from "react";

// list of 11 players with their scores
const players = [
  { name: "Player 1", score: 45 },
  { name: "Player 2", score: 78 },
  { name: "Player 3", score: 90 },
  { name: "Player 4", score: 33 },
  { name: "Player 5", score: 65 },
  { name: "Player 6", score: 22 },
  { name: "Player 7", score: 88 },
  { name: "Player 8", score: 55 },
  { name: "Player 9", score: 71 },
  { name: "Player 10", score: 40 },
  { name: "Player 11", score: 60 }
];

// arrow function that filters players whose score is below 70
const lowScorers = players.filter((player) => player.score < 70);

// component that displays all players and the low scoring players
function ListofPlayers() {
  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>
      <h2>Players With Score Below 70</h2>
      <ul>
        {lowScorers.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
