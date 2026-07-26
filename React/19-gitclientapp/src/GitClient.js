import axios from "axios";

// fetches the repository names of a given GitHub user
class GitClient {
  async getRepositories(username) {
    const response = await axios.get(`https://api.github.com/users/${username}/repos`);
    return response.data.map((repo) => repo.name);
  }
}

export default GitClient;
