<template>
  <div class="m-5">
    <b-container class="bv-example-row">
      <b-row>
        <b-col v-if="articles.length">
          <b-table-simple hover responsive>
            <b-thead style="background-color: #ffdfa4">
              <b-tr style="background-color: #ffdfa4">
                <b-th>제목</b-th>
                <b-th>작성자</b-th>
                <b-th>작성일</b-th>
                <b-th>조회수</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <b-tr v-for="(article, index) in articles" :key="index">
                <b-th style="width: 600px">
                  <router-link :to="`${Number(article.articleNo)}`" class="title">{{ article.title }}</router-link>
                </b-th>
                <b-td>관리자</b-td>
                <b-td>{{ article.regTime | dateFormat }}</b-td>
                <b-td>{{ article.hit }}</b-td>
              </b-tr>
            </tbody>
          </b-table-simple>
        </b-col>
        <b-col v-else> <p>글 목록이 없습니다.</p> </b-col>
      </b-row>
      <b-row class="mb-1" v-if="this.getUserInfo() != null && this.getUserInfo().id === 'admin'">
        <b-col class="text-right">
          <b-button variant="warning" @click="moveWrite"><b>글쓰기</b></b-button>
        </b-col>
      </b-row>
      <notice-paging></notice-paging>
      <notice-search></notice-search>
    </b-container>
  </div>
</template>

<script>
import moment from "moment";
import NoticeSearch from "@/components/notice/item//NoticeSearch.vue";
import NoticePaging from "@/components/notice/item/NoticePaging.vue";

import { mapActions, mapState, mapMutations, mapGetters } from "vuex";

const userStore = "userStore";
const noticeStore = "noticeStore";

export default {
  name: "NoticeList",
  components: {
    NoticeSearch,
    NoticePaging,
  },
  computed: {
    ...mapState(noticeStore, ["articles", "searchCondition", "isReaminSearchCondition"]),
  },
  async created() {
    this.SET_IS_REMAIN_NOTICE_SEARCH_CONDITION(this.isReaminSearchCondition);
    if (!this.isReaminSearchCondition) {
      this.searchCondition.key = "title";
      this.searchCondition.word = "";
      this.searchCondition.currentPage = 1;
    }
    await this.setArticles(this.searchCondition);
  },
  methods: {
    ...mapActions(noticeStore, ["setArticles"]),
    ...mapMutations(noticeStore, ["SET_IS_REMAIN_NOTICE_SEARCH_CONDITION"]),
    ...mapGetters(userStore, ["getUserInfo"]),
    moveWrite() {
      this.$router.push({ name: "noticeRegister" });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY/MM/DD  HH:mm");
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.title {
  color: #3e3e3e;
}
a:hover {
  text-decoration: none;
  color: orange;
}
</style>
