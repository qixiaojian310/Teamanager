<template>
  <!-- 1. 任务进度-->
  <!-- 2. 每个人的贡献-->
  <!-- 每个人的时间计划 -->
  <!-- task summary -->
  <!-- 贡献相当于是每个人承担的任务数量除以团队的总任务，任务点数的设计，任务点数相当于子任务的数量 -->
  <div style="margin:10px">
    <highcharts
      :constructorType="'chart'"
      class="hc"
      :options="option"
      :callback="afterChartCreate"
      ref="chart"
      :highchart="hcInstance"
    ></highcharts>
  </div>
</template>

<script>
import Highcharts from "highcharts";
// import gantt from "highcharts/modules/";
import noDataToDisplay from "highcharts/modules/no-data-to-display";
import { Chart } from "highcharts-vue";

// gantt(Highcharts);
noDataToDisplay(Highcharts);

export default {
  components: {
    highcharts: Chart,
  },
  name: "ContributionAllStatistics",
  props: {
    width: {
      type: Number,
    },
    height: {
      type: Number,
    },
    option: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      chart: {},
      hcInstance: Highcharts,
    };
  },
  mounted() {
    // this.$nextTick(() => {
    //   this.chart = Highcharts.chart("gantt", this.option);
    // });
  },
  methods: {
    afterChartCreate(chart) {
      this.chart = chart;
      console.log("afterChartCreate");
      this.option.chart.height = this.height;
      this.chart.setSize(this.width, this.height, true);
    },
  },
  watch: {
    width(newValue, oldValue) {
      this.$refs.chart.chart.setSize(newValue, this.height, true);
    },
  },
};
</script>

<style></style>
