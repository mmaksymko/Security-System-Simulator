import fetch from 'node-fetch';

const buildingId = 123; // Замінити це на реальний id будівлі

const apiUrl = `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}`;

const requestOptions = {
  method: 'DELETE',
  headers: {
    'Content-Type': 'application/json'
  },
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