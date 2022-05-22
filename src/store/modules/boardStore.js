import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const boardStore = {
  namespaced: true,
  state: {
    articles: [],
    article: {
      articleNo: Number,
      userId: String,
      title: String,
      content: String,
      hit: Number,
      regTime: String,
    },
    searchCondition: {
      key: String,
      word: String,
      currentPage: Number,
    },
    pageNavigation: {
      startRange: Boolean,
      startPage: Number,
      endPage: Number,
      endRange: Boolean,
      currentPage: Number,
      totalPageCount: Number,
    },
  },
  getters: {
    // searchUserCnt: function (state) {
    //   return state.searchUsers.length > 0 ? state.searchUsers.length : null;
    // },
  },
  mutations: {
    SET_ARTICLES: function (state, articles) {
      state.articles = articles;
    },
    CREATE_ARTICLE: function (state, article) {
      state.article = article;
    },
    SET_ARTICLE: function (state, article) {
      state.article = article;
    },
    SET_SEARCH_CONDITION: function (state, searchCondition) {
      state.searchCondition = searchCondition;
    },
    SET_CURRENT_PAGE: function (state, currentPage) {
      state.searchCondition.currentPage = currentPage;
    },
    SET_PAGE_NAVIGATION: function (state, pageNavigation) {
      state.pageNavigation = pageNavigation;
    },
  },
  actions: {
    // 게시글 등록
    createArticle: function ({ commit }, article) {
      const API_URL = `http://localhost:8080/HappyHouse/board/create`;
      axios({
        url: API_URL,
        method: "post",
        data: article,
      })
        .then(() => {
          // mutation
          commit("CREATE_ARTICLE", article);
          Swal.fire({
            title: "게시글 등록! 😆",
            text: "글을 등록하였습니다.",
            icon: "success",
            confirmButtonText: "확인",
          });
          router.push("/board");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 게시글 수정
    updateArticle: function ({ state }, article) {
      const API_URL = `http://localhost:8080/HappyHouse/board/`;
      axios({
        url: API_URL,
        method: "put",
        data: article,
      })
        .then(() => {
          Swal.fire({
            title: "게시글 수정! ☺️",
            text: "글을 수정하였습니다.",
            icon: "success",
            confirmButtonText: "확인",
          });
          let index;
          for (let i = 0; i < state.articles.length; i++) {
            if ((state.articles[i].articleNo = article.articleNo)) {
              index = i;
            }
          }
          state.articles[index] = article;
          router.push("/board");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 게시글 삭제
    deleteArticle: function ({ state }, articleNo) {
      const API_URL = `http://localhost:8080/HappyHouse/board/${articleNo}`;
      axios({
        url: API_URL,
        method: "delete",
      })
        .then(() => {
          Swal.fire({
            title: "게시글 삭제! 👍",
            text: "글을 삭제하였습니다.",
            icon: "success",
            confirmButtonText: "확인",
          });
          let index;
          for (let i = 0; i < state.articles.length; i++) {
            if ((state.articles[i].articleNo = articleNo)) {
              index = i;
            }
          }
          state.articles.splice(index, 1);
          router.push("/board");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 게시글 상세보기
    setArticle: function ({ commit }, articleNo) {
      const API_URL = `http://localhost:8080/HappyHouse/board/${articleNo}`;
      axios({
        url: API_URL,
        method: "get",
      })
        .then((res) => {
          commit("SET_ARTICLE", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 전체 게시판 글 목록 가져오기
    setArticles: function ({ commit }, searchCondition) {
      const API_URL = `http://localhost:8080/HappyHouse/board/`;
      axios({
        url: API_URL,
        method: "post",
        data: searchCondition,
      })
        .then((res) => {
          commit("SET_SEARCH_CONDITION", searchCondition);
          commit("SET_ARTICLES", res.data.articles);
          commit("SET_PAGE_NAVIGATION", res.data.pageNavigation);
          console.log(res.data.articles);
          console.log(res.data.pageNavigation);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default boardStore;
