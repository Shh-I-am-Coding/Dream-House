<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4 mb-4 text-center">
      <b-col class="sm-3">
        <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select v-model="dongCode" :options="dongs" @change="searchApt"></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select v-model="sortBy" :options="sortByslt" @change="searchApt"></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select v-model="sortOrder" :options="sortOrderslt" @change="searchApt"></b-form-select>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const dealStore = "dealStore";

export default {
  name: "DealSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      sortBy: "aptName",
      sortByslt: [
        { value: "avgAmount", text: "평균 금액순 정렬" },
        { value: "aptName", text: "이름순 정렬" },
      ],
      sortOrder: "asc",
      sortOrderslt: [
        { value: "asc", text: "오름차순 정렬" },
        { value: "desc", text: "내림차순 정렬" },
      ],
    };
  },
  computed: {
    ...mapState(dealStore, ["sidos", "guguns", "dongs", "deals", "deal"]),
  },
  created() {
    this.CLEAR_DETAIL_DEAL();
    this.CLEAR_DEAL_LIST();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(dealStore, ["getSido", "getGugun", "getDong", "getDealList"]),
    ...mapMutations(dealStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_DETAIL_DEAL", "CLEAR_DEAL_LIST", "CHANGE_SORTBY", "CHANGE_SORTORDER"]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.CLEAR_DETAIL_DEAL();
      this.CLEAR_DEAL_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.CLEAR_DETAIL_DEAL();
      this.CLEAR_DEAL_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      const params = {
        dongCode: this.dongCode,
        sortBy: this.sortBy,
        sortOrder: this.sortOrder,
      };
      this.CHANGE_SORTBY(this.sortBy);
      this.CHANGE_SORTORDER(this.sortOrder);
      this.getDealList(params);
    },
  },
};
</script>

<style></style>
