<template>
  <div  class="x-bar">
    <div id="gantt" :option="option" style="width:100%"></div>
  </div>
</template>

<script>
import Highcharts from "highcharts/highstock";
import gantt from 'highcharts/modules/gantt';
import noDataToDisplay from "highcharts/modules/no-data-to-display";

gantt(Highcharts);
noDataToDisplay(Highcharts);
var chart = {}

export default {
  name: "GanttChart",
  props: {
    width: {
      type: Number,
    },
    height:{
      type: Number,
    },
    option:{
      type:Object,
      default: () => {}
    },
  },
  mounted(){
    this.$nextTick(() => {
      chart = Highcharts.chart('gantt', this.option);
      chart.setSize(this.width, this.height,true);
    })
  },
  watch:{
    width(newValue,oldValue){
      chart.setSize(newValue,this.height,true)
    },
    height(newValue,oldValue){
      chart.setSize(this.width,newValue,true)
    },
  }
}
</script>

<style scoped>
.x-bar{
  margin-bottom: 1rem;
}

</style>