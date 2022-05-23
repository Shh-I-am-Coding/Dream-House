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
                <b-th>
                  <router-link :to="`${Number(article.articleNo)}`" class="title">{{ article.title }}</router-link>
                </b-th>
                <b-td>{{ article.userId }}</b-td>
                <b-td>{{ article.regTime | dateFormat }}</b-td>
                <b-td>{{ article.hit }}</b-td>
              </b-tr>
            </tbody>
          </b-table-simple>
        </b-col>
        <b-col v-else> <p>글 목록이 없습니다.</p> </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button variant="warning" @click="moveWrite()">글쓰기</b-button>
        </b-col>
      </b-row>
      <board-paging></board-paging>
      <board-search></board-search>
    </b-container>
  </div>
</template>

<script>
import moment from "moment";
import BoardSearch from "@/components/board/item/BoardSearch.vue";
import BoardPaging from "@/components/board/item/BoardPaging.vue";

import { mapActions, mapState, mapMutations } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardList",
  components: {
    BoardSearch,
    BoardPaging,
  },
  computed: {
    ...mapState(boardStore, ["articles", "searchCondition", "isReaminSearchCondition"]),
  },
  async created() {
    this.SET_IS_REMAIN_SEARCH_CONDITION(this.isReaminSearchCondition);
    if (!this.isReaminSearchCondition) {
      this.searchCondition.key = "title";
      this.searchCondition.word = "";
      this.searchCondition.currentPage = 1;
    }
    await this.setArticles(this.searchCondition);
  },
  methods: {
    ...mapActions(boardStore, ["setArticles"]),
    ...mapMutations(boardStore, ["SET_IS_REMAIN_SEARCH_CONDITION"]),
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD HH.mm");
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
