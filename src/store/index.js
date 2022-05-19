import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import boardStore from "./modules/boardStore.js";

const store = new Vuex.Store({
  modules: {
    boardStore,
  },
});

export default store;
