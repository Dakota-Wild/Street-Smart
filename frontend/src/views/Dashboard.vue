<template>
  <div>
    <base-header type="gradient-success" class="pt-md-8">
      <card shadow type="secondary">
        <div slot="header">
          Upcoming events
        </div>
        <div class="event">
          <ul>
            <li
              style="text-align: left;"
              v-for="event in schedule"
              v-bind:key="event.id"
            >
              Event: {{ event.eventName }} <br/>Date: {{ event.eventDate }} <br/>Leave
              By: {{ event.leaveByTime }}
            </li>
          </ul>
        </div>
      </card>
    </base-header>

    <div>
      <maps />
    </div>
  </div>
</template>
<script>
import http from "../http-common";
import Maps from "./Maps";
export default {
  props: {
    email: String,
  },
  components: {
    Maps,
  },
  mounted() {
    this.user.email = this.$store.state.email;
    this.getEvents();
  },
  data() {
    return {
      schedule: [],
      user: {
        id: "",
        firstName: "",
        lastName: "",
        email: "",
        homeAddress: "",
        password: "",
      },
    };
  },
  methods: {
    getEvents() {
      http
        .get("/schedule/" + this.user.email)
        .then((response) => {
          this.schedule = response.data;
          this.schedule.forEach(event => {
            var date = new Date(Date.parse(event.eventDate));
            event.eventDate = date.getMonth()+1 + '/' + date.getDate() + '/' + date.getFullYear();
          });
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
<style scoped>
.event {
  color: gray;
}
</style>
