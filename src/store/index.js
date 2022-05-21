import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import boardStore from "./modules/boardStore.js";
import dealStore from "./modules/dealStore.js";

const store = new Vuex.Store({
  modules: {
    boardStore,
    dealStore,
  },
});

export default store;
