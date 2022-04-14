<template>
  <Calendar
    backgroundText
    :select-mode="'range'"
    :begin="subTaskBegin"
    :end="subTaskEnd"
    @onSelect="selectDate"
    :language="'en'"
  />
</template>

<script>
import Calendar from "mpvue-calendar";

export default {
  name: "SubTaskCalendar",
  components: {
    Calendar,
  },
  props: {
		subTaskBegin: {
			type: String,
		},
		subTaskEnd: {
			type: String,
		},
  },
	methods: {
		selectDate(selectDate) {
			this.$emit("selectDate", selectDate);
		},
	},
};
</script>

<style></style>
