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
                  <h3 class="mb-0">My account</h3>
                </div>
              </div>
            </div>
            <template>
              <form @submit.prevent>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <base-input
                        alternative=""
                        label="Event Name"
                        placeholder="Event Name"
                        input-classes="form-control-alternative"
                        v-model="user.eventName"
                        name="eventName"
                      />
                    </div>
                    <div class="col-lg-6">
                      <input
                        id = "addressInput"
                        alternative=""
                        label="Event Address"
                        placeholder="FE: 3801 W Temple Ave, Pomona, CA 91768"
                        input-classes="form-control-alternative"
                        v-model="user.address"
                        name="address"
                      />
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <base-input
                        alternative=""
                        label="Email"
                        placeholder="example@example.com"
                        input-classes="form-control-alternative"
                        v-model="user.email"
                        name="email"
                      />
                    </div>
                    <div class="col-lg-6">
                      <label>Event Date</label>
                      <br />
                      <date-pick
                        v-model="user.eventDate"
                        :displayFormat="'DD.MM.YYYY'"
                      ></date-pick>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <label>Event Start Time</label>
                      <br />
                      <vue-timepicker
                        v-model="user.startTime"
                        format="hh:mm a"
                      ></vue-timepicker>
                    </div>
                    <div class="col-lg-6">
                      <label>Desired Arrival Time</label>
                      <br />
                      <vue-timepicker
                        v-model="user.arrivalTime"
                        format="hh:mm a"
                      ></vue-timepicker>
                    </div>
                  </div>
                  <div class="row"></div>
                  <button v-on:click="saveUser" class="btn btn-success">
                    Submit
                  </button>
                </div>
                <hr class="my-4" />
              </form>
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
  name: "user-profile",
  components: { VueTimepicker, DatePick },
  data() {
    return {
      user: {
        id: "",
        firstName: "",
        lastName: "",
        email: "",
        homeAddress: "",
        password: "",
        date: "",
      },
    };
  },

  mounted() {
    // let placeSearch;

const componentForm = {
  street_number: "short_name",
  route: "long_name",
  locality: "long_name",
  administrative_area_level_1: "short_name",
  country: "long_name",
  postal_code: "short_name",
};

  let google = window.google
  // Create the autocomplete object, restricting the search predictions to
  // geographical location types.
  let autocomplete = new google.maps.places.Autocomplete(
    document.getElementById("addressInput"),
    { types: ["geocode"] }
  );
  // Avoid paying for data that you don't need by restricting the set of
  // place fields that are returned to just the address components.
  autocomplete.setFields(["address_component"]);
  // When the user selects an address from the drop-down, populate the
  // address fields in the form.
  autocomplete.addListener("place_changed", fillInAddress());

fillInAddress(); {
  // Get the place details from the autocomplete object.
  const place = autocomplete.getPlace();

  for (const component in componentForm) {
    document.getElementById(component).value = "";
    document.getElementById(component).disabled = false;
  }

  // Get each component of the address from the place details,
  // and then fill-in the corresponding field on the form.
  for (const component of place.address_components) {
    const addressType = component.types[0];

    if (componentForm[addressType]) {
      const val = component[componentForm[addressType]];
      document.getElementById(addressType).value = val;
    }
  }
}

  },

  methods: {
    saveUser() {
      var data = {
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        email: this.user.email,
        homeAddress: this.user.homeAddress,
        password: this.user.password,
      };
      http
        .post("/user", data)
        .then((response) => {
          this.user.id = response.data.id;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    newUser() {
      this.submitted = false;
      this.user = {};
    },
  },
};
function fillInAddress(componentForm, autocomplete) {
  // Get the place details from the autocomplete object.
  const place = autocomplete.getPlace();

  for (const component in componentForm) {
    document.getElementById(component).value = "";
    document.getElementById(component).disabled = false;
  }

  // Get each component of the address from the place details,
  // and then fill-in the corresponding field on the form.
  for (const component of place.address_components) {
    const addressType = component.types[0];

    if (componentForm[addressType]) {
      const val = component[componentForm[addressType]];
      document.getElementById(addressType).value = val;
    }
  }
}
</script>
<style></style>
