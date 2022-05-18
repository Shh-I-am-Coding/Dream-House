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
          ${article.title} [${article.hit}]</h3><div><h6>${article.userId}</div><div>${article.regTime}</h6></div>`"
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
// import moment from "moment";
import { mapState } from "vuex";

export default {
  name: "BoardDetail",
  computed: {
    ...mapState(["article"]),
    message() {
      //TODO this.article인지 article인지 확인!
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    this.$store.dispatch("setArticle", articleNo);
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.push({ name: "boardModify" });
      // this.$router.replace({
      //   name: "boardModify",
      //   params: { articleNo: this.article.articleNo },
      // });
    },
    deleteArticle() {
      if (confirm("삭제하시겠습니까?")) {
        this.$router.push({ name: "boardDelete" });
        // this.$router.replace({
        //   name: "boardDelete",
        //   params: { articleNo: this.article.articleNo },
        // });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
