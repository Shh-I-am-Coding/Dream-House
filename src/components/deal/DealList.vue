<template>
  <div>
    <div style="position: absolute; top: 180px; right: 10px; z-index: 3">
      <b-button v-b-toggle.sidebar-aptList>아파트 리스트</b-button>
    </div>
    <b-sidebar id="sidebar-aptList" title="아파트 리스트" shadow>
      <b-container v-if="deals && deals.length != 0" class="bv-example-row mt-3 overflow-auto" style="height: 600px">
        <deal-list-item v-for="(deal, index) in deals" :key="index" :deal="deal" />
      </b-container>
      <b-container v-else class="bv-example-row mt-3">
        <b-row>
          <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
        </b-row>
      </b-container>
    </b-sidebar>
  </div>
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

<style>
.b-sidebar-body {
  height: 300px;
}
</style>
