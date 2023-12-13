export type Sensor = {
  coverageArea: string;
  type: string;
};

export type Event = {
  eventType: string;
  happenedAt: string;
  dangerLevel: string;
};

export type Log = {
  events: Event[];
};

export type Room = {
  roomType: string;
  roomNumber: number;
  windows: number;
  doors: number;
  width: number;
  lenght: number;
  name: string;
  sensors: Sensor[];
  logs: Log[];
};

export type Floor = {
  components: Room[];
  name: string;
};

export type GetBuildingsResponse = {
  components: Floor[];
};

export async function getBuildings() {
  try {
    // 👇️ const response: Response
    const response = await fetch("http://localhost:8080/buildings", {
      method: "GET",
      headers: {
        Accept: "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`Error! status: ${response.status}`);
    }

    // 👇️ const result: GetUsersResponse
    const result = (await response.json()) as GetBuildingsResponse;

    // console.log("result is: ", JSON.stringify(result));

    return result;
  } catch (error) {
    if (error instanceof Error) {
      console.error("error message: ", error.message);
      return error.message;
    } else {
      console.error("unexpected error: ", error);
      return "An unexpected error occurred";
    }
  }
}

export async function getBuilding(id: string | null) {
  if (id === null) return;

  try {
    // console.log("Start getting building with id: ", id);
    // 👇️ const response: Response
    const response = await fetch(`http://localhost:8080/buildings/${id}`, {
      method: "GET",
      headers: {
        Accept: "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`Error! status: ${response.status}`);
    }

    const result = (await response.json()) as GetBuildingsResponse;

    // console.log(result);
    return result;
  } catch (error) {
    if (error instanceof Error) {
      console.error("error message: ", error.message);
      return error.message;
    } else {
      console.error("unexpected error: ", error);
      return "An unexpected error occurred";
    }
  }
}

// getBuildings();
