<template>
  <div>
    <ccv-combo-chart :data="chartData" :options="options" />
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
    ...mapState(dealStore, ["dealAvg", "deal", "chartData"]),
  },
  watch: {
    deal(newValue) {
      if (newValue == null) return;
      this.getDealAvg({ aptCode: newValue.aptCode });
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
