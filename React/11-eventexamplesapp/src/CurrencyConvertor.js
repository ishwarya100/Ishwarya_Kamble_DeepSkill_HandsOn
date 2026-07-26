import React from "react";

// converts Indian Rupees to Euro when the Convert button is clicked
class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rupees: 0,
      euro: 0
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // keeps the rupees value in state as the user types
  handleChange(event) {
    this.setState({ rupees: event.target.value });
  }

  // converts the rupees value into euro on submit
  handleSubmit(event) {
    event.preventDefault();
    const conversionRate = 0.011;
    this.setState({ euro: (this.state.rupees * conversionRate).toFixed(2) });
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <h2>Currency Convertor</h2>
        <input type="number" value={this.state.rupees} onChange={this.handleChange} />
        <button type="submit">Convert</button>
        <p>Euro: {this.state.euro}</p>
      </form>
    );
  }
}

export default CurrencyConvertor;
