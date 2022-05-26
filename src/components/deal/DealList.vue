<template>
  <div>
    <div style="position: absolute; top: 210px; left: 10px; z-index: 3">
      <b-button v-b-toggle.sidebar-aptList class="mr-2 yBtn"><b>아파트 리스트</b></b-button>
      <b-button v-b-toggle.sidebar-interestList class="interBtn" @click="isLogin"><b>관심 아파트</b></b-button>
    </div>

    <b-sidebar id="sidebar-aptList" title="아파트 리스트" shadow style="width: 20vw">
      <b-container v-if="deals && deals.length != 0" class="bv-example-row mt-3 overflow-auto">
        <deal-list-item v-for="(deal, index) in deals" :key="index" :deal="deal" :interested="0" v-b-toggle="['sidebar-dealInfo']" />
      </b-container>
      <b-container v-else class="bv-example-row mt-3">
        <b-row>
          <b-col><b-alert variant="danger" style="font-size: 1.1rem" show>조건에 맞는 매매 내역이 없습니다.</b-alert></b-col>
        </b-row>
      </b-container>
    </b-sidebar>
    <b-sidebar v-if="userInfo" id="sidebar-interestList" :title="userInfo.name + '님의 관심지역'" shadow>
      <b-container class="bv-example-row mt-3 overflow-auto interestInfoContainer">
        <deal-list-item v-for="(interest, index) in interests" :deal="interest" :key="index" :interested="1" v-b-toggle="['sidebar-dealInfo']" />
      </b-container>
    </b-sidebar>
    <b-sidebar id="sidebar-dealInfo" title="아파트 정보" right shadow width="20vw">
      <b-container v-if="dealInfos && dealInfos.length != 0" class="bv-example-row" style="position: relative; top: -10vh">
        <input class="favoriteBtn bounce-top" type="image" :src="require('@/assets/img/untouch.png')" v-if="!isInInter" @click="insertInterest" name="submit" width="15%" height="5%" alt="좋아요" />
        <input class="favoriteBtn bounce-top" type="image" :src="require('@/assets/img/touch.png')" v-else @click="delInterest" name="submit" width="15%" height="5%" alt="좋아요 해제" />
        <img :src="getImgName()" style="width: 350px; height: 300px" />
        <b-row>
          <b-col id="infoName">{{ dealInfos[0].aptName }} </b-col>
        </b-row>
        <b-row v-if="dealInfos[0].roadName">
          <b-col v-if="dealInfos[0].roadNameBubun != 0" class="address">도로명 주소 : {{ dealInfos[0].roadName }} {{ dealInfos[0].roadNameBonbun }}-{{ dealInfos[0].roadBubun }}</b-col>
          <b-col v-else class="address">도로명 주소 : {{ dealInfos[0].roadName }} {{ dealInfos[0].roadNameBonbun }}</b-col>
        </b-row>
        <b-row v-if="dealInfos[0].jibun">
          <b-col class="address">지번 주소 : {{ dealInfos[0].dongName }} {{ dealInfos[0].jibun }}</b-col>
        </b-row>

        <div style="border-top: 1px solid; margin-top: 5px; padding-top: 5px">거래 정보</div>
        <b-container class="bv-example-row mt-3 overflow-auto dealInfoContainer">
          <div v-for="(dealInfo, index) in dealInfos" :key="index">
            <deal-info-list-item :dealInfo="dealInfo" :index="index" />
          </div>
        </b-container>
        <deal-avg-chart v-if="userInfo" />
        <div v-else style="font-size: 1.1rem">회원 가입하고 더 많은 정보를 얻어보세요~😉</div>
      </b-container>
      <b-row style="position: relative; top: -5vh">
        <b-col><b-button class="interBtn conBtn" v-b-toggle="['sidebar-dealInfo']">돌아가기</b-button></b-col>
      </b-row>
    </b-sidebar>
  </div>
</template>

<script>
import DealListItem from "@/components/deal/DealListItem.vue";
import { mapActions, mapState } from "vuex";
import Swal from "sweetalert2";
import DealInfoListItem from "./DealInfoListItem.vue";
import DealAvgChart from "./DealAvgChart.vue";

const dealStore = "dealStore";
const userStore = "userStore";
const interestStore = "interestStore";

export default {
  name: "DealList",
  components: {
    DealListItem,
    DealInfoListItem,
    DealAvgChart,
  },
  data() {
    return {
      isInter: false,
      isAptList: false,
      imgUrls: [],
    };
  },
  created() {
    this.imgUrls.push(require("@/assets/img/rand/1.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/2.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/3.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/4.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/5.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/6.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/7.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/8.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/9.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/10.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/11.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/12.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/13.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/14.jpg"));
    this.imgUrls.push(require("@/assets/img/rand/15.jpg"));
  },
  computed: {
    ...mapState(dealStore, ["deals", "deal", "dealInfos"]),
    ...mapState(userStore, ["userInfo"]),
    ...mapState(interestStore, ["interests"]),
    isInInter: function () {
      if (!this.userInfo || !this.deal || this.interests.length < 1) return false;
      for (let i = 0; i < this.interests.length; i++) {
        if (this.deal.aptCode == this.interests[i].aptCode) {
          return true;
        }
      }
      return false;
    },
  },
  methods: {
    ...mapActions(interestStore, ["createInterest", "getInterestList", "deleteInterest"]),
    isLogin() {
      if (this.userInfo == null) {
        console.log("NeedLogin");
        Swal.fire({
          title: "로그인 필요! 💦",
          text: "회원가입을 하고 관심 아파트를 등록해 보세요.",
          icon: "warning",
          confirmButtonText: "확인",
        });
      }
    },
    insertInterest() {
      if (this.userInfo.id == null) {
        Swal.fire({
          title: "로그인 필요! 💦",
          text: "회원가입을 하고 관심 아파트를 등록해 보세요.",
          icon: "warning",
          confirmButtonText: "확인",
        });
      } else {
        const params = {
          id: this.userInfo.id,
          aptCode: this.deal.aptCode,
        };
        console.log("create", params);
        this.createInterest(params).then(
          Swal.fire({
            title: "관심 아파트에 등록! 🏠 ",
            text: "관심 아파트 목록에서 확인해보세요!",
            icon: "success",
            confirmButtonText: "확인",
          })
        );
      }
    },
    delInterest() {
      Swal.fire({
        title: "관심 목록에서 삭제하시겠습니까? 🤔",
        icon: "warning",
        showDenyButton: true,
        confirmButtonText: "삭제",
        denyButtonText: `취소`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          const params = {
            id: this.userInfo.id,
            aptCode: this.deal.aptCode,
          };
          this.deleteInterest(params).then(Swal.fire("삭제 완료!", "", "success"));
        }
      });
    },
    getImgName() {
      const num = Math.floor(Math.random() * 15) + 1;
      //return "@/assets/img/houses/rand/" + num + ".jpg";
      return this.imgUrls[num];
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

<style>
.b-sidebar-outer {
  position: relative;
}
#sidebar-dealInfo,
#sidebar-aptList,
#sidebar-interestList {
  height: 96vh;
  max-height: 96vh;
  position: relative;
  top: -9vh;
}
.b-sidebar-body {
  height: 250px;
}
.card_body {
  margin: 10px;
}
.infoBtn {
  color: black;
  width: 200px;
  margin-bottom: 0.25rem;
}
.infoCard {
  margin-bottom: 0.5rem;
}
#infoName {
  margin-bottom: 1rem;
  font-size: 2rem;
}
.address {
  margin-bottom: 0.25rem;
}
.dealInfoContainer {
  max-height: 300px;
  margin-bottom: 1rem;
}
.yBtn,
.yBtn:hover,
.yBtn:not(:disabled):not(.disabled):active,
.yBtn:not(:disabled):not(.disabled).active,
.yBtn:focus,
.yBtn.focus,
.show > .yBtn.dropdown-toggle {
  color: black;
  background-color: #ffd071;
}

.interBtn:hover {
  color: black;
  background-color: #fda846;
}
.interBtn,
.interBtn:not(:disabled):not(.disabled):active,
.interBtn:not(:disabled):not(.disabled).active,
.interBtn:focus,
.interBtn.focus,
.show > .interBtn.dropdown-toggle {
  color: black;
  background-color: #fcb666;
}
.favoriteBtn {
  position: relative;
  top: 48vh;
  float: right;
  width: 15%;
  z-index: 7;
}
.favoriteBtn:hover {
  cursor: pointer;
}
.conBtn {
  width: 12vh;
  height: 4vh;
}
.card-title {
  font-size: 1.2em;
  font-weight: bold;
}

.bounce-top {
  -webkit-animation: bounce-top 1.2s ease-in-out 1.6s 3 both;
  animation: bounce-top 1.2s ease-in-out 1.6s 3 both;
  background-color: #f8f9fa !important;
  border: none;
}

@-webkit-keyframes bounce-top {
  0% {
    -webkit-transform: translateY(-20px);
    transform: translateY(-20px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
    opacity: 1;
  }
  24% {
    opacity: 1;
  }
  40% {
    -webkit-transform: translateY(-18px);
    transform: translateY(-18px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  65% {
    -webkit-transform: translateY(-12px);
    transform: translateY(-12px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  82% {
    -webkit-transform: translateY(-6px);
    transform: translateY(-6px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  93% {
    -webkit-transform: translateY(-4px);
    transform: translateY(-4px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  25%,
  55%,
  75%,
  87% {
    -webkit-transform: translateY(0px);
    transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
  100% {
    -webkit-transform: translateY(0px);
    transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
    opacity: 1;
  }
}
@keyframes bounce-top {
  0% {
    -webkit-transform: translateY(-20px);
    transform: translateY(-20px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
    opacity: 1;
  }
  24% {
    opacity: 1;
  }
  40% {
    -webkit-transform: translateY(-18px);
    transform: translateY(-18px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  65% {
    -webkit-transform: translateY(-12px);
    transform: translateY(-12px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  82% {
    -webkit-transform: translateY(-6px);
    transform: translateY(-6px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  93% {
    -webkit-transform: translateY(-4px);
    transform: translateY(-4px);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  25%,
  55%,
  75%,
  87% {
    -webkit-transform: translateY(0px);
    transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
  100% {
    -webkit-transform: translateY(0px);
    transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
    opacity: 1;
  }
}
</style>
