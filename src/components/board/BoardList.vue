<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr v-for="(article, index) in articles" :key="index">
              <b-td>{{ index + 1 }}</b-td>
              <b-th class="text-left">
                <router-link :to="`/board/${article.articleNo}`">{{ article.title }}</router-link>
              </b-th>
              <b-td>{{ article.hit }}</b-td>
              <b-td>{{ article.userId }}</b-td>
              <b-td>{{ article.regTime | dateFormat }}</b-td>
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";

import { mapState } from "vuex";

export default {
  name: "BoardList",
  computed: {
    ...mapState(["articles"]),
  },
  created() {
    this.$store.dispatch("setArticles");
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
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
</style>
