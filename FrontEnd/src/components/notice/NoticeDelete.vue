<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글삭제</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const noticeStore = "noticeStore";

export default {
  name: "NoticeDelete",
  computed: {
    ...mapState(noticeStore, ["article"]),
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    this.deleteArticle(articleNo);
  },
  methods: {
    ...mapActions(noticeStore, ["deleteArticle"]),
  },
};
</script>

<style></style>
