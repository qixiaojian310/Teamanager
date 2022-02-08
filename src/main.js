import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// const app = createApp(App).use(store).use(router).mount('#app')
const app = createApp(App);
app.use(router)
app.use(store)
app.mount("#app")
