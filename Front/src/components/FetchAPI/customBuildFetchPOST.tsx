import React, {useEffect, useState} from "react";

type Sensor = {
    coverageArea: string;
    type: string; 
  };
  
  type Event = {
    eventType: string;
    happenedAt: string;
    dangerLevel: string;
  };
  
  type Log = {
    events: Event[];
  };
  
  type Component = {
    roomType: string;
    roomNumber: number;
    windows: number;
    doors: number;
    name: string;
    sensors: Sensor[];
    logs: Log[];
  };
  
  type BuildingComponent = {
    components: Component[];
  };
  
  type DataType = {
    buildingComponent: BuildingComponent;
  };

  const postData = {
        "components": [
          {
            "components": [
              {
                "roomType": "APARTMENT_ROOM",
                "roomNumber": 1,
                "windows": 4,
                "doors": 5,
                "sensors": [
                  {
                    "coverageArea": 10,
                    "type": "FIRE"
                  },
                  {
                    "coverageArea": 15,
                    "type": "GAS_LEAK"
                  }
                ],
                "logs": [],
                "components": []
              },

            ],
            "name": "Floor 1"
          }
        ],
        "name": "Building 1"
      } 
  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(postData)
  };

  fetch('http://localhost:8080/buildings', requestOptions)
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Error: ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    console.log('Successfully sent and received data:', data);
  })
  .catch(error => {
    console.error('Пerror sending POST-request:', error);
  });