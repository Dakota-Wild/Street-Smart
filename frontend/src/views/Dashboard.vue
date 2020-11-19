<template>
    <div>
      <base-header type="gradient-success" class="pt-md-8">            
        <card shadow type="secondary">
          <div slot="header">
            Upcoming events
          </div>
          <div class="event">
            You have no scheduled events
          </div>
        </card>
      </base-header>

      <div >
        <maps/>
      </div>
    </div>
</template>
<script>
  import Maps from './Maps';

  export default {
    components: {
      Maps,
    },
    data() {
      return {
      };
    },
    methods: {
      
    },
  };
</script>
<style scoped>
.event {
  color: gray;
}
</style>
