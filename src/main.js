import {
  createApp
} from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import '@/assets/css/font-awesome.min.css'
import router from './router'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import aos from './aos/index'


const app = createApp(App).use(store)
app.use(router)
app.use(ElementPlus)
app.use(store)
app.use(VueAxios,axios)
app.use(aos)
app.config.devtools = true;
axios.defaults.baseURL = "http://localhost:8080/api"
// ANCHOR 默认情况axios的跨域不会增加cookie，我们需要增加cookie的要求才可以
axios.defaults.withCredentials=true;

const vm = app.mount('#app')