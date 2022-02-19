<template>
  <div class="sign-input-item" :class="{ 'sign-input-item-focus': focusState }" :style="{height:inputHeight+'rem'}">
    <label :class="{ 'new-label': focusState }" :for="name" :style="{fontSize:fontSize,transition:' font-size 0.5s cubic-bezier(0.4, 0, 0.2, 1)'}">{{ name }}</label>
    <input
      :type="type"
      :id="name"
      :value="inputValue"
      @input="changeStyleChange"
      @focus="changeStyleFocus"
      @blur="changeStyleBlur"
      :style="{fontSize: fontSize,backgroundColor: backgroundColor}"
    />
  </div>
</template>

<script>
export default {
  name: "SignInputItem",
  props: {
    inputValue: {
      type: String,
      default: "",
    },
    name: {
      type: String,
      default: "",
    },
    placeholder:{
      type: String,
      default: "",
    },
    type: {
      type: String,
      default: "text",
    },
    fontSize: {
      type: String,
      default: "1.8rem",
    },
    backgroundColor: {
      type: String,
      default: "#ffffff",
    },
    inputHeight:{
      type:Number,
      default:3
    }
  },
  data() {
    return {
      focusState: false,
      emptyState: true,
    };
  },
  methods: {
    changeStyleFocus: function () {
      this.focusState = true;
    },
    changeStyleBlur: function () {
      this.emptyState == false
        ? (this.focusState = true)
        : (this.focusState = false);
      this.inputValue.length > 0
        ? (this.focusState = true)
        : (this.focusState = false);
    },
    changeStyleChange: function (event) {
      this.inputValue.length > 0
        ? (this.focusState = true)
        : (this.focusState = false);
      this.$emit("update:inputValue", event.target.value);
    },
  },

  mounted() {
    this.inputValue.length > 0
      ? (this.focusState = true)
      : (this.focusState = false);
  },
};
</script>

<style scoped>
input {
  position: absolute;
  outline: 0;
  border: none;
  width: 80%;
  left: 0%;
  z-index: 2;
  color: #6a9d67;
}
label {
  position: absolute;
  top: 1.3rem;
  left: 0%;
  z-index: 3;
  color: #70665e;
  transition: font-size 0.5s cubic-bezier(0.4, 0, 0.2, 1),
    bottom 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}
.sign-input-item {
  position: relative;
  margin-bottom: 1rem;
  z-index: 10;
  padding-bottom: 0.4rem;
  padding-top: 1.3rem;
  margin-left: 2rem;
  margin-right: 2rem;
  border-bottom: 4px solid #ccc;
  transition: border-bottom 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}

.sign-input-item-focus {
  border-bottom: 4px dotted #6a9d67;
  transition: border-bottom 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}

.new-label {
  top: 0 !important;
  font-size: 1rem !important;
  color: #6a9d67;
  transition: font-size 0.5s cubic-bezier(0.4, 0, 0.2, 1),
    bottom 0.5s cubic-bezier(0.4, 0, 0.2, 1),
    color 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}
</style>