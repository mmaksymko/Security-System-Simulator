import fetch from 'node-fetch';

type Sensor = {
    coverageArea: string;
    type: string; 
  };
  
  type Event = {
    eventType: string;
    happenedAt: string;
    dangerLevel: string;
    result: boolean;
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

  type GetBuildingsResponse = {
    data: DataType[];  
  }

async function getBuildings() {
  try {
    // 👇️ const response: Response
    const response = await fetch('http://localhost:8080/buildings', {
      method: 'GET',
      headers: {
        Accept: 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`Error! status: ${response.status}`);
    }

    // 👇️ const result: GetUsersResponse
    const result = (await response.json()) as GetBuildingsResponse;

    console.log('result is: ', JSON.stringify(result));

    return result;
  } catch (error) {
    if (error instanceof Error) {
      console.log('error message: ', error.message);
      return error.message;
    } else {
      console.log('unexpected error: ', error);
      return 'An unexpected error occurred';
    }
  }
}

getBuildings();
