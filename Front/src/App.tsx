// App.js
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { BuildingProvider } from "./BuildingContext";
import ConfigureBuilding from "./pages/ConfigureBuilding";
import SimulationPage from "./pages/SimulationPage";

function App() {
  return (
    <Router>
      <BuildingProvider>
        <Routes>
          <Route path="/" element={<ConfigureBuilding />} />
          <Route path="/simulation" element={<SimulationPage />} />
        </Routes>
      </BuildingProvider>
    </Router>
  );
}

export default App;
