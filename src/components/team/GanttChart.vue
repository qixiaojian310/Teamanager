<template>
  <div  class="x-bar">
    <div :id="id" :option="option" style="width:100%"></div>
  </div>
</template>

<script>
import Highcharts from "highcharts/highstock";
import gantt from 'highcharts/modules/gantt';
import noDataToDisplay from "highcharts/modules/no-data-to-display";
gantt(Highcharts);
noDataToDisplay(Highcharts);

export default {
  name: "GanttChart",
  props: {
    width:{
      type:Number,
      default:400
    },
    id:{
      type:String,
      default:"ganttChart"
    },
    option:{
      type:Object,
      default: () => {}
    },
  },
  mounted(){
    Highcharts.chart(this.id, this.option);
  }
}
</script>

<style>

</style>