<template>
  <div>
    

    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">
    <body id="results-container">
      <div class="results">
        <div id="inputs"></div>
        <div class="results">
          <strong>Results</strong>
        </div>
        <div id="output"></div>
      </div>
    </body>
    </base-header>

    <body>
      <div class="container-fluid mt--7">
        <div class="row">
          <div class="col">
            <div class="card shadow border-0">
              <div
                id="map-canvas"
                class="map-canvas"
                data-lat="34.0589"
                data-lng="-117.8194"
                style="height: 600px"
              ></div>
            </div>
          </div>
        </div>
      </div>

      <input
        id="pac-input"
        class="controls"
        type="text"
        placeholder="Search Box"
      />
      <div id="map"></div>
    </body>
  </div>
</template>
<script>
export default {
  mounted() {
    let google = window.google;

    const origin1 = { lat: 34.068921, lng: -118.4473698 }; //cpp
    //const destinationA = { lat: 34.0589, lng: -117.8194 }; // ucla
    const destinationIcon =
      "https://chart.googleapis.com/chart?" +
      "chst=d_map_pin_letter&chld=D|FF0000|000000";
    const originIcon =
      "https://chart.googleapis.com/chart?" +
      "chst=d_map_pin_letter&chld=O|FFFF00|000000";

    const map = new google.maps.Map(document.getElementById("map-canvas"), {
      center: { lat: 34.0589, lng: -117.8194 },
      zoom: 11,
      mapTypeId: "roadmap",
    });
    // Create the search box and link it to the UI element.
    const input = document.getElementById("pac-input");
    const searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
    // Bias the SearchBox results towards current map's viewport.
    map.addListener("bounds_changed", () => {
      searchBox.setBounds(map.getBounds());
    });
    let markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener("places_changed", () => {
      const places = searchBox.getPlaces();
      const geocoder = new google.maps.Geocoder();
      const service = new google.maps.DistanceMatrixService();

      service.getDistanceMatrix(
        {
          origins: [origin1],
          destinations: [places[0].geometry.location],
          travelMode: google.maps.TravelMode.DRIVING,
          unitSystem: google.maps.UnitSystem.METRIC,
          avoidHighways: false,
          avoidTolls: false,
        },
        (response, status) => {
          if (status !== "OK") {
            alert("Error was: " + status);
          } else {
            const originList = response.originAddresses;
            const destinationList = response.destinationAddresses;
            const outputDiv = document.getElementById("output");
            outputDiv.innerHTML = "";
            deleteMarkers(markers);

            const showGeocodedAddressOnMap = function(asDestination) {
              const icon = asDestination ? destinationIcon : originIcon;

              return function(results, status) {
                if (status === "OK") {
                  map.fitBounds(bounds.extend(results[0].geometry.location));
                  markers.push(
                    new google.maps.Marker({
                      map,
                      position: results[0].geometry.location,
                      icon: icon,
                    })
                  );
                } else {
                  alert("Geocode was not successful due to: " + status);
                }
              };
            };

            for (let i = 0; i < originList.length; i++) {
              const results = response.rows[i].elements;
              geocoder.geocode(
                { address: originList[i] },
                showGeocodedAddressOnMap(false)
              );

              for (let j = 0; j < results.length; j++) {
                geocoder.geocode(
                  { address: destinationList[j] },
                  showGeocodedAddressOnMap(true)
                );
                outputDiv.innerHTML +=
                  originList[i] +
                  " to " +
                  destinationList[j] +
                  ": " +
                  results[j].distance.text +
                  " in " +
                  results[j].duration.text +
                  "<br>";
              }
            }
          }
        }
      );

      if (places.length == 0) {
        return;
      }
      // Clear out the old markers.
      markers.forEach((marker) => {
        marker.setMap(null);
      });
      markers = [];
      // For each place, get the icon, name and location.
      const bounds = new google.maps.LatLngBounds();
      places.forEach((place) => {
        if (!place.geometry) {
          console.log("Returned place contains no geometry");
          return;
        }
        const icon = {
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(25, 25),
        };
        // Create a marker for each place.
        markers.push(
          new google.maps.Marker({
            map,
            icon,
            title: place.name,
            position: place.geometry.location,
          })
        );

        if (place.geometry.viewport) {
          // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      map.fitBounds(bounds);
    });
  },
};
function deleteMarkers(markersArray) {
  for (let i = 0; i < markersArray.length; i++) {
    markersArray[i].setMap(null);
  }
  markersArray = [];
}
</script>
<style>
#pac-input {
  background-color: #fff;
  font-family: Roboto;
  font-size: 16px;
  font-weight: 300;
  margin-left: 12px;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 600px;
}
#output {
  background-color: transparent;
}

.results {
  color: white;
  background: transparent;
  width: 100%;
}

#inputs {
  background-color: transparent;
}

#results-container {
  background-color: transparent;
}

strong {
  background-color: transparent;
}
</style>
