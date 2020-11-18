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

                        <base-input class="input-group alternative"
                                    placeholder="Password"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="user.password">
                        </base-input>

                        <base-input class="input-group alternative"
                                    placeholder="Re-Enter Password"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open">
                        </base-input>

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
                    password: ''
                }
            }   
        },
    methods: {
        saveUser() {
            var data = {
                firstName: this.user.firstName,
                lastName: this.user.lastName,
                email: this.user.email,
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
</script>
<style>
</style>
