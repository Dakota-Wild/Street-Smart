<template>
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div class="card bg-secondary shadow border-0">
                    <div class="card-body px-lg-5 py-lg-5">
                        <form role="form">
                            <base-input class="input-group-alternative mb-3"
                                        placeholder="Email"
                                        addon-left-icon="ni ni-email-83"
                                        v-model="user.email">
                            </base-input>

                            <base-input class="input-group-alternative"
                                        placeholder="Password"
                                        type="password"
                                        addon-left-icon="ni ni-lock-circle-open"
                                        v-model="user.password">
                            </base-input>
                            <div class="text-center">
                                <base-button @click="checkValidLogin" type="primary" class="my-4">Sign in</base-button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12 text-right">
                        <router-link to="/register" class="text-light"><small>Create new account</small></router-link>
                    </div>
                </div>
            </div>
        </div>
</template>
<script>
import http from "../http-common";

  export default {
    name: 'login',
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
    methods: {
        checkValidLogin() {
            var data = {
                id: this.user.id,
                firstName: this.user.firstName,
                lastName: this.user.lastName,
                email: this.user.email,
                homeAddress: this.user.homeAddress,
                password: this.user.password
            };
            http
                .get("/login-check", data)
                .then(response => {
                    this.user.id = response.data.id;
                    if(!this.user.id) {
                        alert('That email and password combination do not exist!');
                    }
                    else {
                        console.log(response.data.email);
                    }
                })
                .catch(e => {
                    console.log(e)
                });
        },

    }
}
  
</script>
<style>
</style>
