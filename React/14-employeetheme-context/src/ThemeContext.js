import { createContext } from "react";

// context used to share the current theme name with nested components
const ThemeContext = createContext("light");

export default ThemeContext;
