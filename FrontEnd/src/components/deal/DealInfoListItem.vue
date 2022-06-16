<template>
  <div>
    <b-button v-b-toggle="'infoCard-' + index" @mouseover="colorChange(true)" @mouseout="colorChange(false)" :class="['infoBtn', { 'mouse-over-bgcolor': isColor }, { 'mouse-out-bgcolor': !isColor }]">
      {{ dealInfo.dealYear }}/{{ dealInfo.dealMonth }}/{{ dealInfo.dealDay }} : {{ dealInfo.dealAmount | price }}
    </b-button>
    <b-collapse :id="'infoCard-' + index" class="mt-2">
      <b-card class="infoCard" bg-variant="Secondary">
        <b-card-text>면적 : {{ dealInfo.area }} m<sup>2</sup></b-card-text>
        <b-card-text>{{ dealInfo.floor }} 층</b-card-text>
      </b-card>
    </b-collapse>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const dealStore = "dealStore";
const interestStore = "interestStore";
const userStore = "userStore";

export default {
  name: "DealListInfoItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    dealInfo: Object,
    index: Number,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(dealStore, ["detailDeal", "getAptList"]),
    ...mapActions(interestStore, ["createInterest", "getInterestList"]),
    async insertInterest() {
      const params = {
        id: this.userInfo.id,
        aptCode: this.deal.aptCode,
      };
      console.log("create", params);
      await this.createInterest(params);
      await this.getInterestList({ id: this.userInfo.id });
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    price(value) {
      if (!value) {
        return value;
      }
      let rst = "";
      let tmp = value / 10000;
      if (tmp != 0) {
        rst += tmp + "억";
        value -= tmp * 10000;
      } else {
        rst += value;
        rst += "만원";
      }
      return rst.toString();
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  color: black;
  background-color: #ffd279;
}
.mouse-out-bgcolor {
  background-color: #fae0ad;
}
</style>
