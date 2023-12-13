import fetch from 'node-fetch';

const buildingId = 123; // Замінити це на реальний id будівлі

type Sensor = {
  coverageArea: string;
  type: string;
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

const buildingData: DataType = {
  buildingComponent: {
    components: [
      {
        roomType: "living_room",
        roomNumber: 101,
        windows: 2,
        doors: 1,
        name: "Main Room",
        sensors: [
          {
            coverageArea: "area1",
            type: "temperature"
          },
          {
            coverageArea: "area1",
            type: "motion"
          }
        ],
        logs: [
          {
            events: []
          }
        ]
      },
    ]
  }
};

const apiUrl = `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}`;

const requestOptions = {
  method: 'PUT',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(buildingData)
};

fetch(apiUrl, requestOptions)
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    console.log('Successfully sent and received data:', data);
  })
  .catch(error => {
    console.error('Error sending PUT request:', error);
  });