const { TextEncoder, TextDecoder } = require("util");

// jsdom test environment does not provide these globals, but Enzyme's cheerio dependency needs them
global.TextEncoder = TextEncoder;
global.TextDecoder = TextDecoder;

const Enzyme = require("enzyme");
const Adapter = require("@wojtekmaj/enzyme-adapter-react-17");

// configures Enzyme to work with React 17 for this project
Enzyme.configure({ adapter: new Adapter() });
