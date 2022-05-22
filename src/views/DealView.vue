<template>
  <b-container class="bv-example-row mt-3 text-center" fluid>
    <h3 class="underline-orange"><b-icon icon="house-fill"></b-icon> Deal Service</h3>
    <b-row>
      <b-col>
        <deal-search-bar></deal-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="4" align="left">
        <deal-list />
      </b-col>
      <b-col cols="8">
        <deal-detail />
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import DealSearchBar from "@/components/deal/DealSearchBar.vue";
import DealList from "@/components/deal/DealList.vue";
import DealDetail from "@/components/deal/DealDetail.vue";

export default {
  name: "DealView",
  components: {
    DealSearchBar,
    DealList,
    DealDetail,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
