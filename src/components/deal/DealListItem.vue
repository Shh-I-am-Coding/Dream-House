<template>
  <b-row class="m-2" @click="selectDeal" @mouseover="colorChange(true)" @mouseout="colorChange(false)" :class="{ 'mouse-over-bgcolor': isColor }">
    <b-col cols="2" class="text-center align-self-center">
      <b-img thumbnail src="https://picsum.photos/250/250/?image=58" alt="Image 1"></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">{{ deal.aptName }} </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const dealStore = "dealStore";

export default {
  name: "DealListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    deal: Object,
  },
  methods: {
    ...mapActions(dealStore, ["detailDeal"]),
    selectDeal() {
      this.detailDeal(this.deal);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
