import { sidoList, gugunList, dongList, dealList, APTList } from "@/api/deal.js";

const dealStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    deals: [],
    deal: null,
    sortOrder: "asc",
    sortBy: "aptName",
    // deal: {
    //   idx: Number,
    //   dealYear: Number,
    //   dealMouth: Number,
    //   dealDay: Number,
    //   area: String,
    //   floor: String,
    //   aptCode: Number,
    //   dealAmount: String,
    //   aptName: String,
    //   dongName: String,
    //   jibun: String,
    // },
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        //console.log(sido.code + " " + sido.name);
        state.sidos.push({ value: sido.code, text: sido.name });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      //수원시, 수원시 장안구 등 같은 시의 구가 여러곳 있는 경우 제거
      for (let i = 0; i < guguns.length - 1; i++) {
        if (i != guguns.length - 1) {
          if (guguns[i].name.split(" ")[1] == guguns[i + 1].name.split(" ")[1] && guguns[i].name.split(" ").length != guguns[i + 1].name.split(" ").length) {
            guguns.splice(i, 1);
            i--;
          }
        }
      }
      guguns.forEach((gugun) => {
        let name = gugun.name.split(" ")[1];
        if (gugun.name.split(" ")[2] != null) {
          name = name + " " + gugun.name.split(" ")[2];
        }
        state.guguns.push({ value: gugun.code, text: name });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      //console.log(guguns);
      dongs.forEach((dong) => {
        //console.log(dong);
        let name = dong.name.split(" ")[2];
        if (dong.name.split(" ")[3] != null) {
          name = name + " " + dong.name.split(" ")[3];
        }
        state.dongs.push({ value: dong.code, text: name });
        //state.dongs.push({ value: dong.code, text: dong.name });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_DEAL_LIST: (state, deals) => {
      state.deals = deals;
    },
    CLEAR_DEAL_LIST: (state) => {
      state.deals = [];
      //state.deal.splice(0, state.deal.length);
    },
    SET_DETAIL_DEAL: (state, deal) => {
      state.deal = deal;
    },
    CLEAR_DETAIL_DEAL: (state) => {
      state.deal = null;
    },
    CHANGE_SORTBY: (state, sortBy) => {
      state.sortOrder = sortBy;
    },
    CHANGE_SORTORDER: (state, sortOrder) => {
      state.sortOrder = sortOrder;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          //console.log(data);
          commit("SET_SIDO_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        regcode_pattern: sidoCode.substr(0, 2) + "*00000",
        is_ignore_zero: true,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          //console.log(data.regcodes);
          commit("SET_GUGUN_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, sidoCode) => {
      const params = {
        regcode_pattern: sidoCode.substr(0, 4) + "*",
        is_ignore_zero: true,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          //console.log(data.regcodes);
          commit("SET_DONG_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDealList: ({ commit }, params) => {
      //console.log(params);
      dealList(
        params,
        (response) => {
          //console.log(response.data);
          commit("SET_DEAL_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailDeal: ({ commit }, deal) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_DEAL", deal);
    },
    getAptList: ({ commit }, params) => {
      //console.log(params);
      APTList(
        params,
        (response) => {
          //console.log(response.data);
          commit("SET_DEAL_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default dealStore;
