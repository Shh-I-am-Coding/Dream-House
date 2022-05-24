import { getList, create, remove } from "@/api/interest.js";

const interestStore = {
  namespaced: true,
  state: {
    interests: [],
  },

  getters: {},

  mutations: {
    SET_INTEREST_LIST: (state, interests) => {
      state.interests = interests;
      console.log("SET FIN " + interests.length);
    },
    CLEAR_INTEREST_LIST: (state) => {
      console.log("CLEAR FIN ");
      state.interests = [];
    },
  },
  actions: {
    getInterestList: ({ commit }, params) => {
      console.log("get");
      console.log(params);
      getList(
        params,
        (response) => {
          console.log("GET FIN");
          commit("SET_INTEREST_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    createInterest: ({ commit }, params) => {
      console.log("create");
      console.log(params);
      create(
        params,
        (response) => {
          if (response.data == "success") {
            getList(
              { id: params.id },
              (response) => {
                commit("SET_INTEREST_LIST", response.data);
              },
              (error) => {
                console.log(error);
              }
            );
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deleteInterest: ({ commit }, params) => {
      console.log("DEL");
      console.log(params);
      remove(
        params,
        (response) => {
          if (response.data == "success") {
            getList(
              params,
              (response) => {
                console.log("GET FIN");
                commit("SET_INTEREST_LIST", response.data);
              },
              (error) => {
                console.log(error);
              }
            );
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default interestStore;
