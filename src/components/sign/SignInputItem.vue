<template>
  <div class="sign-input-item" :class="{'sign-input-item-focus' : focusState}">
    <label :class="{ 'new-label': focusState }" :for="name">{{ name }}</label>
    <input
      :type="type"
      :id="name"
      v-model="inputValue"
      @input="changeStyleChange"
      @focus="changeStyleFocus"
      @blur="changeStyleBlur"
    />
  </div>
</template>

<script>
export default {
  name: "SignInputItem",
  props: ["inputValue", "placeholder", "name", "type"],
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
    },
    changeStyleChange: function () {
      console.log(this.inputValue.length);
      this.inputValue.length > 0
        ? (this.emptyState = false)
        : (this.emptyState = true);
    },
  },
};
</script>

<style scoped>
input {
  position: absolute;
  outline: 0;
  border: none;
  width: 80%;
  font-size: 1.8rem;
  left: 0%;
  z-index: 2;
  color: #6A9D67;
}
label {
  position: absolute;
  top: 1.3rem;
  left: 0%;
  font-size: 1.6rem;
  z-index: 3;
  color: #70665E;
  transition: font-size 0.3s cubic-bezier(0.4, 0, 0.2, 1),
    bottom 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}
.sign-input-item {
  position: relative;
  height: 3rem;
  margin-bottom: 1rem;
  z-index: 10;
  padding-bottom: 0.4rem;
  padding-top: 1.3rem;
  margin-left: 2rem;
  margin-right: 2rem;
  border-bottom: 4px solid #ccc;
  transition: border-bottom 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.sign-input-item-focus {
  border-bottom: 4px dotted #6A9D67;
  transition: border-bottom 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.new-label {
  top: 0 !important;
  font-size: 1rem !important;
  color: #6A9D67;
  transition: font-size 0.3s cubic-bezier(0.4, 0, 0.2, 1),
    bottom 0.3s cubic-bezier(0.4, 0, 0.2, 1), color 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}
</style>