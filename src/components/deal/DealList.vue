<template>
  <div>
    <div style="position: absolute; top: 210px; left: 10px; z-index: 3">
      <b-button v-b-toggle.sidebar-aptList class="mr-2 yBtn">아파트 리스트</b-button>
      <b-button v-b-toggle.sidebar-interestList class="interBtn" @click="isLogin">관심지역</b-button>
    </div>
    <b-sidebar id="sidebar-aptList" title="아파트 리스트" shadow>
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
    <b-sidebar id="sidebar-dealInfo" title="아파트 정보" right shadow>
      <b-container v-if="dealInfos && dealInfos.length != 0" class="bv-example-row overflow-auto">
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
      </b-container>
      <b-button class="yBtn" v-b-toggle="['sidebar-dealInfo']">돌아가기</b-button>
    </b-sidebar>
  </div>
</template>

<script>
import DealListItem from "@/components/deal/DealListItem.vue";
import { mapState } from "vuex";
import Swal from "sweetalert2";
import DealInfoListItem from "./DealInfoListItem.vue";

const dealStore = "dealStore";
const userStore = "userStore";
const interestStore = "interestStore";

export default {
  name: "DealList",
  components: {
    DealListItem,
    DealInfoListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(dealStore, ["deals", "deal", "dealInfos"]),
    ...mapState(userStore, ["userInfo"]),
    ...mapState(interestStore, ["interests"]),
  },
  methods: {
    isLogin() {
      if (this.userInfo == null) {
        console.log("NeedLogin");
        Swal.fire({
          title: "로그인이 필요합니다! 💦",
          text: "회원가입을 하고 관심지역을 등록해 보세요.",
          icon: "warning",
          confirmButtonText: "확인",
        });
      }
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

#sidebar-dealInfo {
  height: 80vh;
  max-height: 80vh;
  position: sticky;
  top: 10vh;
}
.b-sidebar-body {
  height: 300px;
}
#sidebar-aptList {
  height: 80vh;
  max-height: 80vh;
  position: sticky;
  top: 195px;
}
#sidebar-interestList {
  height: 80vh;
  max-height: 80vh;
  position: sticky;
  top: 195px;
}
.card_body {
  margin: 10px;
}
.infoBtn {
  color: black;
  width: 25vh;
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
.yBtn {
  color: black;
  background-color: #ffd071;
}
.yBtn:hover {
  color: black;
  background-color: #ffc756;
}
.yBtn:not(:disabled):not(.disabled):active {
  color: black;
  background-color: #ffd071;
}
.yBtn:not(:disabled):not(.disabled).active {
  color: black;
  background-color: #ffd071;
}
.yBtn:focus {
  color: black;
  background-color: #ffd071;
}
.yBtn.focus {
  color: black;
  background-color: #ffd071;
}
.show > .yBtn.dropdown-toggle {
  color: black;
  background-color: #ffd071;
}
.interBtn {
  background-color: #fcb666;
  color: black;
}
.interBtn:hover {
  color: black;
  background-color: #fda846;
}
.interBtn:not(:disabled):not(.disabled):active {
  color: black;
  background-color: #fcb666;
}
.interBtn:not(:disabled):not(.disabled).active {
  color: black;
  background-color: #fcb666;
}
.interBtn:focus {
  color: black;
  background-color: #fcb666;
}
.interBtn.focus {
  color: black;
  background-color: #fcb666;
}
.show > .interBtn.dropdown-toggle {
  color: black;
  background-color: #fcb666;
}

.card-title {
  font-size: 1.2em;
  font-weight: bold;
}
</style>
