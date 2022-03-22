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
var chart = {}

export default {
  name: "GanttChart",
  props: {
    width: {
      type: Number,
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
    chart = Highcharts.chart(this.id, this.option);
  },
  watch:{
    width(newValue,oldValue){
      chart.setSize(newValue,undefined,true)
    }
  }
}
</script>

<style scoped>
.x-bar{
  margin-bottom: 1rem;
}

</style>