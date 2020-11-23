<template>
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
            <div class="card bg-secondary shadow border-0">

                <div class="card-body px-lg-5 py-lg-5">
                    <div class="text-center text-muted mb-4">
                        <small>Sign up below!</small>
                    </div>
                    <form role="form">

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="First Name"
                                    addon-left-icon="ni ni-hat-3"
                                    v-model="user.firstName">
                        </base-input>

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="Last Name"
                                    addon-left-icon="ni ni-hat-3"
                                    v-model="user.lastName">
                        </base-input>

                        <base-input class="input-group-alternative"
                                    placeholder="email"
                                    addon-left-icon="ni ni-email-83"
                                    v-model="user.email">
                        </base-input>

                        <base-input id = "homeAddressInput"
                                    class="input-group alternative"
                                    placeholder="Home Address"
                                    addon-left-icon="ni ni-square-pin"
                                    v-model="user.homeAddress">
                        </base-input>

                        <base-input class="input-group alternative"
                                    placeholder="Password"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="user.password">
                        </base-input>
                        <!---
                        <base-input class="input-group alternative"
                                    placeholder="Re-Enter Password"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open">
                        </base-input>--->

                        <div class="text-center">
                            <router-link to="/login" class="text-light">
                                <base-button v-on:click="saveUser" type="primary" class="my-4">Create account</base-button>
                            </router-link>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-6">
                </div>
                <div class="col-12 text-right">
                    <router-link to="/login" class="text-light">
                        <small>Already have an account? Log in here</small>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import BaseInput from '../components/BaseInput.vue';
import http from "../http-common";
  export default {
  components: { BaseInput },
    name: 'register',
    data() {
            return {
                user: {
                    id: '',
                    firstName: '',
                    lastName: '',
                    email: '',
                    homeAddress: '',
                    password: ''
                }
            }   
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
                    document.getElementById("homeAddressInput"),
                    { types: ["geocode"] }
                );
                // Avoid paying for data that you don't need by restricting the set of
                // place fields that are returned to just the address components.
                autocomplete.setFields(["address_component"]);
                // When the user selects an address from the drop-down, populate the
                // address fields in the form.
                autocomplete.addListener("place_changed", fillInAddress(componentForm, autocomplete));
            },

    methods: {
        saveUser() {
            var data = {
                firstName: this.user.firstName,
                lastName: this.user.lastName,
                email: this.user.email,
                homeAddress: this.user.homeAddress,
                password: this.user.password
            };
            http
                .post("/user", data)
                .then(response => {
                    this.user.id = response.data.id;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e)
                });
        },
        newUser() {
            this.submitted = false;
            this.user = {};
        }   
    }
  }
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
<style>
</style>
