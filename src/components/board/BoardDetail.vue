<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleNo}.
          ${article.title} [${article.hit}]</h3><div><h6>${article.userId}</div><div>${dateFormat(article.regTime)}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
import { mapActions, mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardDetail",
  computed: {
    ...mapState(boardStore, ["article"]),
    message() {
      if (String(this.article.content)) return String(this.article.content).split("\n").join("<br>");
      return "";
    },
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    this.setArticle(articleNo);
  },
  methods: {
    ...mapActions(boardStore, ["setArticle"]),
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.push({ name: "boardModify" });
    },
    deleteArticle() {
      if (confirm("삭제하시겠습니까?")) {
        this.$router.push({ name: "boardDelete" });
      }
    },
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD HH:mm");
    },
  },
};
</script>

<style></style>
