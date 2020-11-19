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
              <div class="submitform">
                <div v-if="!submitted">
                  <form @submit.prevent>
                    <div class="pl-lg-4">
                      <div class="row">
                        <div class="col-lg-6">
                          <div class="firstName">
                            <strong>First Name</strong>
                          </div>
                          <div id="fname"></div>
                        </div>
                        <div class="col-lg-6">
                          <div class="firstName">
                            <strong>Last Name</strong>
                          </div>
                          <div id="fname"></div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-lg-6">
                          <div class="firstName">
                            <strong>Email</strong>
                          </div>
                          <div id="fname"></div>
                        </div>
                        <div class="col-lg-6">
                          <div class="firstName">
                            <strong>Password</strong>
                          </div>
                          <div id="fname"></div>
                        </div>
                      </div>
                      <button v-on:click="saveUser" class="btn btn-success">
                        Submit
                      </button>
                    </div>
                  </form>
                </div>
                <div v-else>
                  <h4>You submitted successfully!</h4>
                  <button class="btn btn-success" v-on:click="newUser">
                    Add
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
export default {
  name: "user-profile",
  mounted() {
    this.user.email = this.$store.state.email;
    this.getUser();
    const fnameDiv = document.getElementById("fname");
    fnameDiv.innerHTML = this.user.firstName;
  },

  methods: {
    getUser() {
      http
        .get("/user/" + this.$store.state.email)
        .then((response) => {
          this.user = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
<style></style>
