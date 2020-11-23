<template>
  <div>
    <base-header
      class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
      style="min-height: 250px; background-image: url(img/theme/profile-cover.jpg); background-size: cover; background-position: center top;"
    >
      <!-- Mask -->
      <span class="mask bg-gradient-success opacity-8"></span>
      <!-- Header container -->
    </base-header>

    <div class="container-fluid mt--7">
      <div class="row ">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0"></div>

        <div class="col-xl-8 order-xl-1" style="margin-auto">
          <card shadow type="secondary">
            <div slot="header" class="bg-white border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0"></h3>
                </div>
              </div>
            </div>
            <template>
              <div class="submitform">
                <div v-if="!submitted">
                  <form @submit.prevent>
                    <div class="pl-lg-4">
                      <div class="row">
                        <div class="col-lg-6">
                          <base-input
                            alternative=""
                            label="Event Name"
                            placeholder="Event Name"
                            input-classes="form-control-alternative"
                            v-model="schedule.eventName"
                            name="eventName"
                          />
                        </div>
                        <div class="col-lg-6">
                          <base-input
                            id="addressInput"
                            alternative=""
                            label="Event Address"
                            placeholder="Example Ave, City, State 12345"
                            input-classes="form-control-alternative"
                            v-model="schedule.address"
                            name="address"
                          >
                          </base-input>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-lg-6">
                          <label>Event Start Time</label>
                          <br />
                          <vue-timepicker
                            v-model="schedule.eventStartTime"
                            format="hh:mm a"
                          ></vue-timepicker>
                        </div>
                        <div class="col-lg-6">
                          <label>Desired Arrival Time</label>
                          <br />
                          <vue-timepicker
                            v-model="schedule.arrivalTime"
                            format="hh:mm a"
                          ></vue-timepicker>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-lg-6">
                          <br />
                          <label>Event Date</label>
                          <br />
                          <date-pick
                            v-model="schedule.eventDate"
                            :displayFormat="'DD.MM.YYYY'"
                          ></date-pick>
                        </div>
                      </div>
                      <div class="row"></div>
                      <button v-on:click="calculate" class="btn btn-success">
                        Submit
                      </button>
                    </div>
                  </form>
                </div>
                <div v-else>
                  <h4>Your event has been added!</h4>
                  <button class="btn btn-success" v-on:click="newSchedule">
                    Add another event
                  </button>
                </div>
                <hr class="my-4" />
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from "../http-common";
import VueTimepicker from "vue2-timepicker";
import DatePick from "vue-date-pick";
import "vue2-timepicker/dist/VueTimepicker.css";
import "vue-date-pick/dist/vueDatePick.css";

export default {
  // props: {
  //   email: String
  // },
  props: ['email'],
  name: "create-event",
  components: { VueTimepicker, DatePick },
  data() {
    return {
      schedule: {
        id: "",
        eventName: "",
        eventStartTime: "",
        eventDate: "",
        arrivalTime: "",
        address: "",
        userEmail: this.$store.state.email,
        leaveByTime: "",
      },
      submitted: false,
    };
  },

  mounted() {
    // document.getElementById("addressInput").value = this.schedule.address;
    // document.getElementById("addressInput").value = this.schedule.address
    let google = window.google;
    // Create the autocomplete object, restricting the search predictions to
    // geographical location types.
    let autocomplete = new google.maps.places.Autocomplete(document.getElementById("addressInput"))
    // Avoid paying for data that you don't need by restricting the set of
    // place fields that are returned to just the address components.
    autocomplete.setFields(["address_component"]);
  },

  methods: {
    calculate() {
      this.schedule.address = document.getElementById("addressInput").value;
      // console.log("document.getElementById(\"addressInput\")" + document.getElementById("addressInput").value)
      //upon submit calculate the time to leave by

      const origin = { lat: 34.0589, lng: -117.8194 } // cpp by default
      // const destination = { lat: 34.068921, lng: -118.4473698 } //ucla by default
      let google = window.google
      // const geocoder = new google.maps.Geocoder();
      const service = new google.maps.DistanceMatrixService();
      
      var tempArrivalTime = this.schedule.arrivalTime;
      var arrivalTimeHours = tempArrivalTime.substring(0,2);
      var arrivalTimeMinutes = tempArrivalTime.substring(3,5);
      var N = arrivalTimeHours*(60000*60) + arrivalTimeMinutes*60000

      var tempTime;

      service.getDistanceMatrix(
        {
          origins: [origin],
          destinations: [this.schedule.address],
          travelMode: google.maps.TravelMode.DRIVING,
          unitSystem: google.maps.UnitSystem.IMPERIAL,
          avoidHighways: false,
          avoidTolls: false,
          drivingOptions: {
            departureTime: new Date(Date.now() + N),  // for the time N milliseconds from now.
            trafficModel: 'optimistic'
  }
        },
        (response, status) => { //this part of the code waits for the response for google then runs the below code
          if (status !== "OK") {
            alert("Error was: " + status);
          } else {
            const results = response.rows[0].elements;
              tempTime = results[0].duration.value;

              //begin calculations
              var timeInSeconds = tempTime;
              // var timeInSeconds = 5990; //for testing purposes only
              var drivingTime = new Date(timeInSeconds * 1000).toISOString().substr(11, 8);
              console.log("driving time: " + drivingTime);
              var drivingTimeHours = drivingTime.substring(0,2);
              var drivingTimeMinutes = drivingTime.substring(3,5);
              // var drivingTimeSeconds = drivingTime.substring(6);
              // console.log(drivingTimeHours + " " + drivingTimeMinutes);

              // console.log(tempArrivalTime);
              // console.log(arrivalTimeHours + " " + arrivalTimeMinutes);

              var leaveTimeMinutes = null;
              var leaveTimeHours = null;
              var leaveTimePeriod = tempArrivalTime.substring(6);
              leaveTimeMinutes = arrivalTimeMinutes - drivingTimeMinutes;
              if (leaveTimeMinutes < 0)
              {
                leaveTimeMinutes = leaveTimeMinutes + 60;
                arrivalTimeHours = arrivalTimeHours - 1;
              }
              leaveTimeHours = arrivalTimeHours - drivingTimeHours;
              if (leaveTimeHours < 1)
              {
                leaveTimeHours = leaveTimeHours + 12;
                // console.log(tempArrivalTime.substring(6))
                if (tempArrivalTime.substring(6) == "am")
                {
                  leaveTimePeriod = "pm";
                }
                else {
                  leaveTimePeriod = "am"
                }
              }
              var leaveTime = leaveTimeHours + ":" + leaveTimeMinutes + " " + leaveTimePeriod;
              console.log(leaveTime);
              //end of 4AM calculation code

              this.schedule.leaveByTime = leaveTime;
              this.saveSchedule();
            }
          }
        )
    },
    saveSchedule() {

      var data = {
        eventName: this.schedule.eventName,
        eventStartTime: this.schedule.eventStartTime,
        eventDate: this.schedule.eventDate,
        arrivalTime: this.schedule.arrivalTime,
        address: this.schedule.address,
        userEmail: this.schedule.userEmail,
        leaveByTime: this.schedule.leaveByTime
      };
      http
        .post("/schedule", data)
        .then((response) => {
          this.schedule.id = response.data.id;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
      this.submitted = true;
    },
    newSchedule() {
      this.submitted = false;
      this.schedule = {};
    },
  },
};
</script>
<style></style>
