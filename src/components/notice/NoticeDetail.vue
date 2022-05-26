<template>
  <b-container class="bv-example-row mt-4 text-left">
    <b-row class="pb-1 pt-3" style="background-color: blanchedalmond; border-top: 3px solid">
      <b-col>
        <h3 class="mb-3" style="padding-top: 5px">
          <b>{{ article.title }}</b>
        </h3>
      </b-col>
    </b-row>
    <b-row class="pr-3 pb-4 mb-3" style="background-color: blanchedalmond; border-bottom: 1px solid">
      <b-col cols="3" class="text-left"> <b class="mr-2">등록일</b>{{ dateFormat }} </b-col>
      <b-col cols="9" class="text-left p-0"><b class="mr-2">조회수</b> {{ article.hit }}</b-col>
    </b-row>
    <b-row class="p-3 pb-5 mb-3" v-html="message" style="border-bottom: 1px solid"> </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button class="listBtn" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="this.getUserInfo() != null && this.getUserInfo().id === 'admin'">
        <b-button @click="moveModifyArticle" class="modBtn mr-2">수정</b-button>
        <b-button class="delBtn" @click="deleteArticle">삭제</b-button>
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
.listBtn {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: rgb(255, 192, 119);
}
.listBtn:hover {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #ffa743;
}
.delBtn {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #fcc8b8;
}
.delBtn:hover {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #f8b6a3;
}
.modBtn {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #b4e2fc;
}
.modBtn:hover {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #99daff;
}
</style>
