import React from "react";

// tracks how many people entered and exited the mall
class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
    this.updateEntry = this.updateEntry.bind(this);
    this.updateExit = this.updateExit.bind(this);
  }

  // increments the entry count by 1
  updateEntry() {
    this.setState({ entrycount: this.state.entrycount + 1 });
  }

  // increments the exit count by 1
  updateExit() {
    this.setState({ exitcount: this.state.exitcount + 1 });
  }

  render() {
    return (
      <div>
        <h2>Mall Visitor Counter</h2>
        <p>People Entered: {this.state.entrycount}</p>
        <p>People Exited: {this.state.exitcount}</p>
        <button onClick={this.updateEntry}>Login</button>
        <button onClick={this.updateExit}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;
