<template>
  <div>
    <div style="position: absolute; top: 210px; left: 10px; z-index: 3">
      <b-button v-b-toggle.sidebar-aptList variant="warning">아파트 리스트</b-button>
    </div>
    <b-sidebar id="sidebar-aptList" title="아파트 리스트" shadow>
      <b-container v-if="deals && deals.length != 0" class="bv-example-row mt-3 overflow-auto">
        <deal-list-item v-for="(deal, index) in deals" :key="index" :deal="deal" v-b-toggle="['sidebar-dealInfo', 'sidebar-aptList']" />
      </b-container>
      <b-container v-else class="bv-example-row mt-3">
        <b-row>
          <b-col><b-alert show>검색된 주택 목록이 없습니다.</b-alert></b-col>
        </b-row>
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
            <b-button v-b-toggle="'collaspe-' + index" class="infoBtn" variant="warning">
              {{ dealInfo.dealYear }}/{{ dealInfo.dealMonth }}/{{ dealInfo.dealDay }} : {{ dealInfo.dealAmount | price }}
            </b-button>
            <b-collapse :id="'collaspe-' + index" class="mt-2">
              <b-card class="infoCard">HELLO WORLD!</b-card>
            </b-collapse>
          </div>
        </b-container>
      </b-container>
      <b-button variant="warning" v-b-toggle="['sidebar-dealInfo', 'sidebar-aptList']">아파트 리스트로 돌아가기</b-button>
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
    ...mapState(dealStore, ["deals", "deal", "dealInfos"]),
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
.card_body {
  margin: 10px;
}
.infoBtn {
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
</style>
