<template>
  <div>
    <deal-search-bar></deal-search-bar>
    <deal-list />
    <deal-detail />
  </div>
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
