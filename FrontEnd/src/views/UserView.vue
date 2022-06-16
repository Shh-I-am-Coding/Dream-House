<template>
  <div class="bv-example-row m-5 p-3 text-center">
    <!-- <h3 class="underline-steelblue"><b-icon icon="person-lines-fill"></b-icon> Member Service</h3> -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "UserView",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
