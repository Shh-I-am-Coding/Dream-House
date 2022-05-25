<template>
  <div>
    <ccv-combo-chart :data="data" :options="options" />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import "@carbon/charts/styles.css";

const dealStore = "dealStore";

export default {
  name: "DealAvgChart",
  data() {
    return {
      data: [],
      ddaa: [],
      options: {
        title: "아파트 거래 추이",
        axes: {
          left: {
            mapsTo: "avgAmount",
            scaleType: "linear",
            title: "평균 거래가",
          },
          right: {
            mapsTo: "ppa",
            scaleType: "linear",
            title: "평당가격",
            correspondingDatasets: ["ppa"],
          },
          bottom: {
            title: "년도",
            mapsTo: "dealYear",
            scaleType: "labels",
          },
        },
        comboChartTypes: [
          {
            type: "simple-bar",
            correspondingDatasets: ["avgAmount"],
          },
          {
            type: "line",
            correspondingDatasets: ["ppa"],
          },
        ],
        height: "400px",
        toolbar: {
          enabled: false,
        },
      },
    };
  },
  computed: {
    ...mapState(dealStore, ["dealAvg", "deal"]),
  },
  watch: {
    deal(newValue) {
      if (newValue == null) return;
      this.data = [];
      this.getDealAvg({ aptCode: newValue.aptCode });
      for (let i = 0; i < this.dealAvg.length; i++) {
        this.data.push({
          group: "avgAmount",
          dealYear: this.dealAvg[i].dealYear,
          avgAmount: (Math.round(this.dealAvg[i].avgAmount / 100) * 100) / 10000,
        });
        this.data.push({
          group: "ppa",
          dealYear: this.dealAvg[i].dealYear,
          ppa: this.dealAvg[i].pricePerArea,
        });
      }
    },
  },
  methods: {
    ...mapActions(dealStore, ["getDealAvg"]),
  },
};
</script>

<style lang="scss" scoped>
@use "@carbon/styles";
// .cds--chart-holder .cds--cc--toolbar {
//   display: none;
// }
// .cds--chart-holder .cds--overflow-menu {
//   display: none;
// }
@import "https://fonts.googleapis.com/css?family=IBM+Plex+Sans+Condensed|IBM+Plex+Sans:400,600&display=swap";
</style>
