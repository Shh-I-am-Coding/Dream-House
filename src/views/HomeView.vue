<template>
  <div class="">
    <h1>HAPPY HOUSE <b-img :src="require('@/assets/img/house.png')" style="width: 70px"></b-img></h1>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
