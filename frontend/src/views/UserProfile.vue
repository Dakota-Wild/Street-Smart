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
                <div class="col-8" style="margin: auto">
                  <h3 class="mb-0">My Account</h3>
                </div>
              </div>
            </div>
            <template>   
              <div class="pl-lg-4">
                <table style="margin: auto; width: 100%">
                  <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                  </tr>
                  <tr>
                    <td>{{ user.firstName }}</td>
                    <td>{{ user.lastName }}</td>
                  </tr>
                  <tr>
                    <th>Email</th>
                    <th>Password</th>
                  </tr>
                  <tr>
                    <td>{{ user.email }}</td>
                    <td style="color: gray">Hidden</td>
                  </tr>
                </table>
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
  },
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
