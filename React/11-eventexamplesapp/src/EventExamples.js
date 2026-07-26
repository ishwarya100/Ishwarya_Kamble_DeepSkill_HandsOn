import React from "react";

// demonstrates event handling with counter, argument passing and synthetic events
class EventExamples extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      counter: 0,
      message: ""
    };
    this.handleIncrement = this.handleIncrement.bind(this);
    this.handleDecrement = this.handleDecrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
    this.sayWelcome = this.sayWelcome.bind(this);
    this.handlePress = this.handlePress.bind(this);
  }

  // increments the counter value
  handleIncrement() {
    this.setState({ counter: this.state.counter + 1 });
    this.sayHello();
  }

  // decrements the counter value
  handleDecrement() {
    this.setState({ counter: this.state.counter - 1 });
  }

  // displays a static hello message
  sayHello() {
    this.setState({ message: "Hello, counter was incremented" });
  }

  // displays a message using the argument passed to the function
  sayWelcome(text) {
    this.setState({ message: `Say ${text}` });
  }

  // handles a synthetic click event
  handlePress(event) {
    this.setState({ message: "I was clicked" });
  }

  render() {
    return (
      <div>
        <h2>Counter: {this.state.counter}</h2>
        <button onClick={this.handleIncrement}>Increment</button>
        <button onClick={this.handleDecrement}>Decrement</button>
        <button onClick={() => this.sayWelcome("welcome")}>Say Welcome</button>
        <button onClick={this.handlePress}>OnPress</button>
        <p>{this.state.message}</p>
      </div>
    );
  }
}

export default EventExamples;
