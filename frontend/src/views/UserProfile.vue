<template>
    <div>
        <base-header class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
                     style="min-height: 250px; background-image: url(img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
            <!-- Mask -->
            <span class="mask bg-gradient-success opacity-8"></span>
            <!-- Header container -->
        </base-header>

        <div class="container-fluid mt--7">
            <div class="row ">
                <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
                </div>

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
                                            <base-input alternative=""
                                                        label="First Name"
                                                        placeholder="First Name"
                                                        input-classes="form-control-alternative"
                                                        v-model="user.firstName"
                                                        name="firstName"
                                            />
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="Last Name"
                                                        placeholder="Last Name"
                                                        input-classes="form-control-alternative"
                                                        v-model="user.lastName"
                                                        name="lastName"
                                            />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="Email"
                                                        placeholder="example@example.com"
                                                        input-classes="form-control-alternative"
                                                        v-model="user.email"
                                                        name="email"
                                            />
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="Password"
                                                        placeholder="Password"
                                                        input-classes="form-control-alternative"
                                                        v-model="user.password"
                                                        name="password"

                                            />
                                        </div>
                                    </div>
                                <button v-on:click="saveUser" class="btn btn-success">Submit</button>
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
  export default {
    name: 'user-profile',
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
  };
</script>
<style></style>
