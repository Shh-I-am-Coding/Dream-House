<template>
  <b-container v-if="deals && deals.length != 0" class="bv-example-row mt-3 overflow-auto" style="height: 600px">
    <deal-list-item v-for="(deal, index) in deals" :key="index" :deal="deal" />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import DealListItem from "@/components/deal/DealListItem.vue";
import { mapState } from "vuex";

const dealStore = "dealStore";

export default {
  name: "DealList",
  components: {
    DealListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(dealStore, ["deals"]),
  },
};
</script>

<style></style>
