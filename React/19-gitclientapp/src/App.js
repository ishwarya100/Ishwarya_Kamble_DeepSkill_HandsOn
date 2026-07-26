import React, { useState, useEffect } from "react";
import GitClient from "./GitClient";

// App component fetches and displays the repositories of a GitHub user
function App() {
  const [repositories, setRepositories] = useState([]);

  useEffect(() => {
    const gitClient = new GitClient();
    gitClient.getRepositories("techiesyed").then((repos) => {
      setRepositories(repos);
    });
  }, []);

  return (
    <div>
      <h1>Git Client App</h1>
      <ul>
        {repositories.map((repo, index) => (
          <li key={index}>{repo}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
