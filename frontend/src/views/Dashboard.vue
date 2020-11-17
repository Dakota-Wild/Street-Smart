<template>
    <div>
          
          <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">            
            <div>
              <schedule/>
            </div>
          </base-header>
            <div class="container-fluid mt--7">
              <maps/>
            </div>

        
       
        

    </div>
</template>
<script>
  import Schedule from './Schedule.vue';
  import Maps from './Maps';

  export default {
    components: {
      Schedule,
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
<style></style>
