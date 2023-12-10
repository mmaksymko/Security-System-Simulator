// App.js
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { BuildingProvider } from "./BuildingContext";
import ConfigureBuilding from "./pages/ConfigureBuilding";
import SimulationPage from "./pages/SimulationPage";
import CustomBuilding from "./pages/CustomBuilding";
import AddSensors from "./pages/AddSensors";


function App() {
  return (
    <Router>
      <BuildingProvider>
        <Routes>
          <Route path="/" element={<ConfigureBuilding />} />
          <Route path="/simulation" element={<SimulationPage />} />
          <Route path="/customBuilding" element={<CustomBuilding />} />
          <Route path="/AddSensors" element={<AddSensors />} />
        </Routes>
      </BuildingProvider>
    </Router>
  );
}

export default App;
