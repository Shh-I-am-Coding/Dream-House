import router from "@/router";
import Swal from "sweetalert2";
import { create, update, remove, detail, getArticles } from "@/api/notice.js";

const noticeStore = {
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
      key: "title",
      word: "",
      currentPage: 1,
    },
    pageNavigation: {
      startRange: Boolean,
      startPage: Number,
      endPage: Number,
      endRange: Boolean,
      currentPage: Number,
      totalPageCount: Number,
    },
    isReaminSearchCondition: false,
  },
  mutations: {
    SET_ARTICLES: function (state, articles) {
      state.articles = articles;
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
    SET_IS_REMAIN_NOTICE_SEARCH_CONDITION: function (state, isReaminSearchCondition) {
      state.isReaminSearchCondition = isReaminSearchCondition;
    },
  },
  actions: {
    // 게시글 등록
    createArticle({ commit }, article) {
      create(article, () => {
        commit("SET_ARTICLE", article);
        Swal.fire({
          title: "공지사항 등록! 😆",
          text: "공지사항을 등록하였습니다.",
          icon: "success",
          confirmButtonText: "확인",
        });
        router.push("/notice");
      });
    },
    // 게시글 수정
    updateArticle({ state }, article) {
      update(article, () => {
        Swal.fire({
          title: "공지사항 수정! 😎",
          text: "공지사항을 수정하였습니다.",
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
        router.push("/notice");
      });
    },
    // 게시글 삭제
    deleteArticle({ state }, articleNo) {
      remove(articleNo, () => {
        Swal.fire({
          title: "공지사항 삭제! 👍",
          text: "공지사항을 삭제하였습니다.",
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
        router.push("/notice");
      });
    },
    // 게시글 상세보기
    detailArticle({ commit }, articleNo) {
      detail(articleNo, (response) => {
        if (response.data) {
          commit("SET_ARTICLE", response.data);
        }
      });
    },
    // 전체 게시판 글 목록 가져오기
    async setArticles({ commit }, searchCondition) {
      const params = {
        key: searchCondition.key,
        word: searchCondition.word,
        currentPage: searchCondition.currentPage,
      };
      await getArticles(params, (response) => {
        commit("SET_SEARCH_CONDITION", searchCondition);
        commit("SET_ARTICLES", response.data.articles);
        commit("SET_PAGE_NAVIGATION", response.data.pageNavigation);
      });
    },
  },
};

export default noticeStore;
