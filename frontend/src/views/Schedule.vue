<script>
import http from "../http-common";

export default {
  props: {
    email: String
  },
  data() {
    return {
      masks: {
        weekdays: "WWW",
      },
      schedule: [],
      user: {
                id: '',
                firstName: '',
                lastName: '',
                email: '',
                homeAddress: '',
                password: ''
      },
      attributes: [],
    };
  },
  /*created() {
    this.user.email = this.$store.state.email;
    this.getEvents();
  },*/
  mounted() {
    this.user.email = this.$store.state.email;
    this.getEvents();
  },
  methods: {
    getEvents() {
        http
            .get("/schedule/" + this.user.email)
            .then(response => {
              this.schedule = response.data;
              this.schedule.forEach(event => {
              var tempEvent = {
                key: '',
                customData: {
                  eventName: '',
                  eventStartTime: ''
                },
                dates: ''
              };
                tempEvent.key = event.id;
                tempEvent.customData.eventName = event.eventName;
                tempEvent.customData.eventStartTime = event.eventStartTime;
                tempEvent.dates = event.eventDate;
                this.attributes.push(tempEvent);
              });
            })
            .catch(e => {
              console.log(e);
            });
      }
  }
};
</script>

<template>
  <div class="text-center section full-width">
    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8 full-width">
    </base-header>
    <div class="container-fluid mt--7 full-width">
      <vc-calendar
        class="custom-calendar full-width"
        :masks="masks"
        :attributes="attributes"
        disable-page-swipe
        is-expanded
      >
        <template v-slot:day-content="{ day, attributes }" class="full-width">
          <div class="flex flex-col h-full z-10 overflow-hidden full-width">
            <span class="day-label text-sm text-gray-900">{{ day.day }}</span>
            <div class="flex-grow overflow-y-auto overflow-x-auto full-width">
              <p
                v-for="attr in attributes"
                v-bind:key="attr.id"
                class="text-xs leading-tight rounded-sm p-1 mt-0 mb-1 p full-width"
                id="day-background"
              >
                {{ attr.customData.eventName }} <br/> {{ attr.customData.eventStartTime }} 
              </p>
            </div>
          </div>
        </template>
      </vc-calendar>
    </div>
  </div>
</template>

<style lang="scss" scoped>
/*<meta
  name="viewport"
  content="width=device-width, initial-scale=1"
  > ::-webkit-scrollbar {
  width: 0px;
}
::-webkit-scrollbar-track {
  display: none;
}*/

button {
  margin-top: 20px;
  line-height: 60px;
  font-weight: bold;
  padding: 0 40px;
  background: rgb(68, 204, 141);
  border: none;
  color: white;
}
/deep/ .custom-calendar.vc-container {
  --day-border: 1px solid #b8c2cc;
  --day-border-highlight: 1px solid #b8c2cc;
  --day-width: 90px;
  --day-height: 90px;
  --weekday-bg: #f8fafc;
  --weekday-border: 1px solid #eaeaea;
  border-radius: 0;
  width: 100%;
  & .vc-header {
    background-color: #f1f5f8;
    padding: 10px 0;
  }
  & .vc-weeks {
    padding: 0;
  }
  & .vc-weekday {
    background-color: var(--weekday-bg);
    border-bottom: var(--weekday-border);
    border-top: var(--weekday-border);
    padding: 5px 0;
  }
  & .vc-day {
    padding: 0 5px 3px 5px;
    text-align: left;
    height: var(--day-height);
    min-width: var(--day-width);
    background-color: white;
    &.weekday-1,
    &.weekday-7 {
      background-color: #eff8ff;
    }
    &:not(.on-bottom) {
      border-bottom: var(--day-border);
      &.weekday-1 {
        border-bottom: var(--day-border-highlight);
      }
    }
    &:not(.on-right) {
      border-right: var(--day-border);
    }
  }
  & .vc-day-dots {
    margin-bottom: 5px;
  }
  * {
    box-sizing: border-box;
  }

  .p {
    background-color: #eb2d3a;
    color: #ffffff;
  }

  .full-width {
    width: 100%;
  }
}
</style>
