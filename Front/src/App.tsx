import ConfigureBuilding from "./pages/ConfigureBuilding";
import Simulation from "./pages/SimulationPage";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import React from 'react'

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
