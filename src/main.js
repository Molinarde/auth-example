import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueAxios from "vue-axios";
import axios from "axios";
import './plugins/element.js'
import SetupInterseptors from "./service/interceptors/SetupInterseptors";

Vue.config.productionTip = false
Vue.use(VueAxios, axios)

SetupInterseptors(store)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
