<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange"><b-icon icon="house-fill"></b-icon> House Service</h3>
    <b-row>
      <b-col>
        <img alt="Happy House" src="@/assets/happyhouse.png" />
      </b-col>
    </b-row>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "HouseView",
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
