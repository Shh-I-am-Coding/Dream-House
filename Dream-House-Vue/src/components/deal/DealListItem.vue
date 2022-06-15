<template>
  <b-card
    class="m-2"
    @click="selectDeal"
    style="height: 10vh"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="[{ 'mouse-over-bgcolor': isColor }, { 'mouse-out-bgcolor': !isColor }]"
  >
    <b-card-title style="margin-bottom: 0px">{{ deal.aptName }} </b-card-title>
    <b-card-text>평균 거래 금액 : 약 {{ deal.avgAmount | price }}</b-card-text>
  </b-card>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";

const dealStore = "dealStore";
const interestStore = "interestStore";
const userStore = "userStore";

export default {
  name: "DealListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    deal: Object,
    interested: Number,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(dealStore, ["detailDeal", "getAptList"]),
    ...mapActions(interestStore, ["createInterest", "getInterestList", "deleteInterest"]),
    selectDeal() {
      this.detailDeal(this.deal);
      this.searchByAptName(this.deal.aptName, this.deal.dongCode);
    },
    searchByAptName(aptName, dongCode) {
      const params = {
        aptName,
        dongCode,
      };
      console.log(params);
      this.getAptList(params);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    async insertInterest() {
      if (this.userInfo.id == null) {
        Swal.fire({
          title: "로그인이 필요합니다! 💦",
          text: "회원가입을 하고 관심지역을 등록해 보세요.",
          icon: "warning",
          confirmButtonText: "확인",
        });
      } else {
        const params = {
          id: this.userInfo.id,
          aptCode: this.deal.aptCode,
        };
        console.log("create", params);
        await this.createInterest(params);
        this.getInterestList({ id: this.userInfo.id });
      }
    },
    async delInterest() {
      const params = {
        id: this.userInfo.id,
        aptCode: this.deal.aptCode,
      };
      await this.deleteInterest(params);
      await this.getInterestList({ id: this.userInfo.id });
    },
  },
  filters: {
    price(value) {
      if (!value) {
        return value;
      }
      let rst = "";
      let tmp = (Math.round(value / 100) * 100) / 10000;
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
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css);
.mouse-over-bgcolor {
  background-color: #ffd279;
}
.mouse-out-bgcolor {
  background-color: #fae0ad;
}
</style>
