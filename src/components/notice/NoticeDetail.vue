<template>
  <b-container class="bv-example-row mt-4 text-left">
    <b-row class="mb-1">
      <b-col>
        <b-card class="mb-2" border-variant="dark" no-body>
          <template #header>
            <b-row class="pr-3 pl-3 pt-3">
              <b-col cols="9"
                ><h3>
                  <b>{{ article.title }}</b>
                </h3></b-col
              >
              <b-col class="text-right"><b class="mr-1">작성자</b> 관리자</b-col>
              <b-col class="text-right"><b class="mr-2">조회수</b> {{ article.hit }}</b-col>
            </b-row>
            <b-row class="pr-3">
              <b-col class="text-right"><b class="mr-2"></b> {{ dateFormat }}</b-col>
            </b-row>
          </template>

          <b-card-body class="text-left p-4 ml-2">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="this.getUserInfo() != null && this.getUserInfo().id === 'admin'">
        <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">수정</b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">삭제</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
import { mapActions, mapGetters, mapMutations, mapState } from "vuex";
import Swal from "sweetalert2";

const userStore = "userStore";
const noticeStore = "noticeStore";

export default {
  name: "NoticeDetail",
  computed: {
    ...mapState(noticeStore, ["article", "searchCondition", "isReaminSearchCondition"]),
    message() {
      if (String(this.article.content)) return String(this.article.content).split("\n").join("<br>");
      return "";
    },
    dateFormat() {
      return moment(new Date(this.article.regTime)).format("YYYY/MM/DD HH:mm");
    },
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    this.detailArticle(articleNo);
  },
  methods: {
    ...mapActions(noticeStore, ["detailArticle"]),
    ...mapMutations(noticeStore, ["SET_IS_REMAIN_NOTICE_SEARCH_CONDITION"]),
    ...mapGetters(userStore, ["getUserInfo"]),
    listArticle() {
      this.SET_IS_REMAIN_NOTICE_SEARCH_CONDITION(true);
      this.$router.push({ name: "noticeList" });
    },
    moveModifyArticle() {
      this.$router.push({ name: "noticeModify" });
    },
    deleteArticle() {
      Swal.fire({
        title: "삭제하시겠습니까? 🤔",
        text: "한번 삭제하면, 되돌릴 수 없습니다.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "네",
        cancelButtonText: "아니오",
      }).then((result) => {
        if (result.isConfirmed) {
          this.$router.push({ name: "noticeDelete" });
        }
      });
    },
  },
};
</script>

<style scoped>
.card-header {
  background-color: blanchedalmond;
}
</style>
