import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VeeValidate from "vee-validate";
import VueKakaoSdk from "vue-kakao-sdk";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

const apiKey = decodeURIComponent(process.env.VUE_APP_KAKAO_LOGIN_API_KEY);
Vue.use(VueKakaoSdk, { apiKey });

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  VeeValidate,
  render: (h) => h(App),
}).$mount("#app");
