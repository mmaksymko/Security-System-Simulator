import ConfigureBuilding from "./pages/ConfigureBuilding";
import Simulation from "./pages/Simulation";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ConfigureBuilding />}></Route>
        <Route path="simulation" element={<Simulation />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
