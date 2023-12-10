import Bedroom from "./Bedroom";
import "./RoomsStyle.scss";

const SimulationLogic = () => {
  const jSonObj =  
  {
    "components": [
      {
        "roomType": "APARTMENT_ROOM",
        "roomNumber": 1,
        "roomWidht": 1,
        "roomHeight": 4,
        "windows": 2,
        "doors": 2,
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
        "logs": [
          {
            "events": [
              {
                "eventType": "FIRE",
                "dangerLevel": "CONSIDERABLE"
              },
              {
                "eventType": "FIRE",
                "dangerLevel": "CONSIDERABLE"
              },
              {
                "eventType": "FIRE",
                "dangerLevel": "CONSIDERABLE"
              }
            ]
          }
        ],
        "components": []
      },
      {
        "roomType": "OFFICE_ROOM",
        "roomNumber": 2,
        "roomWidht": 1,
        "roomHeight": 2,
        "windows": 4,
        "doors": 5,
        "sensors": [
          {
            "coverageArea": 10,
            "type": "OPENED_DOOR"
          },
          {
            "coverageArea": 15,
            "type": "OPENED_WINDOW"
          }
        ],
        "logs": [
          {
            "events": [
              {
                "eventType": "OPENED_WINDOW",
                "dangerLevel": "CONSIDERABLE"
              },
              {
                "eventType": "MOTION",
                "dangerLevel": "CONSIDERABLE"
              }
            ]
          }
        ],
        "components": []
      },
      {
        "roomType": "OFFICE_ROOM",
        "roomNumber": 2,
        "roomWidht": 1,
        "roomHeight": 2,
        "windows": 4,
        "doors": 5,
        "sensors": [
          {
            "coverageArea": 10,
            "type": "OPENED_DOOR"
          },
          {
            "coverageArea": 15,
            "type": "OPENED_WINDOW"
          }
        ],
        "logs": 
          {
            "events": [
              {
                "eventType": "OPENED_WINDOW",
                "dangerLevel": "CONSIDERABLE"
              },
              {
                "eventType": "MOTION",
                "dangerLevel": "CONSIDERABLE"
              }
            ]
          }  
        }
          ,
          {
            "roomType": "APARTMENT_ROOM",
            "roomNumber": 1,
            "roomWidht": 1,
            "roomHeight": 4,
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
            "logs": [
              {
                "events": [
                  {
                    "eventType": "FIRE",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "FIRE",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "FIRE",
                    "dangerLevel": "CONSIDERABLE"
                  }
                ]
              }
            ],
            "components": []
          },
          {
            "roomType": "OFFICE_ROOM",
            "roomNumber": 2,
            "roomWidht": 1,
            "roomHeight": 2,
            "windows": 4,
            "doors": 5,
            "sensors": [
              {
                "coverageArea": 10,
                "type": "OPENED_DOOR"
              },
              {
                "coverageArea": 15,
                "type": "OPENED_WINDOW"
              }
            ],
            "logs": 
              {
                "events": [
                  {
                    "eventType": "OPENED_WINDOW",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "MOTION",
                    "dangerLevel": "CONSIDERABLE"
                  }
                ]
              }  
          },
          {
            "roomType": "APARTMENT_ROOM",
            "roomNumber": 1,
            "roomWidht": 1,
            "roomHeight": 4,
            "windows": 2,
            "doors": 2,
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
            "logs": [
              {
                "events": [
                  {
                    "eventType": "FIRE",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "FIRE",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "FIRE",
                    "dangerLevel": "CONSIDERABLE"
                  }
                ]
              }
            ],
            "components": []
          },
          {
            "roomType": "OFFICE_ROOM",
            "roomNumber": 2,
            "roomWidht": 1,
            "roomHeight": 2,
            "windows": 4,
            "doors": 5,
            "sensors": [
              {
                "coverageArea": 10,
                "type": "OPENED_DOOR"
              },
              {
                "coverageArea": 15,
                "type": "OPENED_WINDOW"
              }
            ],
            "logs": [
              {
                "events": [
                  {
                    "eventType": "OPENED_WINDOW",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "MOTION",
                    "dangerLevel": "CONSIDERABLE"
                  }
                ]
              }
            ],
            "components": []
          },
          {
            "roomType": "OFFICE_ROOM",
            "roomNumber": 2,
            "roomWidht": 1,
            "roomHeight": 2,
            "windows": 4,
            "doors": 5,
            "sensors": [
              {
                "coverageArea": 10,
                "type": "OPENED_DOOR"
              },
              {
                "coverageArea": 15,
                "type": "OPENED_WINDOW"
              }
            ],
            "logs": 
              {
                "events": [
                  {
                    "eventType": "OPENED_WINDOW",
                    "dangerLevel": "CONSIDERABLE"
                  },
                  {
                    "eventType": "MOTION",
                    "dangerLevel": "CONSIDERABLE"
                  }
                ]
              }  
            }
            ,
            {
              "roomType": "APARTMENT_ROOM",
              "roomNumber": 1,
              "roomWidht": 1,
              "roomHeight": 4,
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
              "logs": [
                {
                  "events": [
                    {
                      "eventType": "FIRE",
                      "dangerLevel": "CONSIDERABLE"
                    },
                    {
                      "eventType": "FIRE",
                      "dangerLevel": "CONSIDERABLE"
                    },
                    {
                      "eventType": "FIRE",
                      "dangerLevel": "CONSIDERABLE"
                    }
                  ]
                }
              ],
              "components": []
            },
            {
              "roomType": "OFFICE_ROOM",
              "roomNumber": 2,
              "roomWidht": 1,
              "roomHeight": 2,
              "windows": 4,
              "doors": 5,
              "sensors": [
                {
                  "coverageArea": 10,
                  "type": "OPENED_DOOR"
                },
                {
                  "coverageArea": 15,
                  "type": "OPENED_WINDOW"
                }
              ],
              "logs": 
                {
                  "events": [
                    {
                      "eventType": "OPENED_WINDOW",
                      "dangerLevel": "CONSIDERABLE"
                    },
                    {
                      "eventType": "MOTION",
                      "dangerLevel": "CONSIDERABLE"
                    }
                  ]
                }  
            }
    ],
    "name": "Floor 1"
};


    const numOfRooms = jSonObj.components.length;
  
    const generateBedrooms = () => {
      const bedrooms = [];
  
      for (let i = 0; i < numOfRooms; i++) {
        bedrooms.push(<Bedroom key={i} roomType={jSonObj.components[i].roomType} 
                                        width={jSonObj.components[i].roomWidht} 
                                        height={jSonObj.components[i].roomHeight}
                                        label={jSonObj.components[i].roomType + jSonObj.components[i].roomNumber} />);
                                        // windows={jSonObj.components[i].windows} />);
                                        // doors={jSonObj.components[i].doors} />
                                       
      }
  
      return bedrooms;
    };
  
    return (
        <div className="Components FloorPlan">
          {generateBedrooms()}
        </div>
    );

export default SimulationLogic;
