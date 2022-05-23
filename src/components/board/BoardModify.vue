<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit">
          <b-form-input class="mb-2" id="userId" v-model="article.userId" type="text" required placeholder="작성자"></b-form-input>
          <b-form-input class="mb-4" id="title" v-model="article.title" type="text" required placeholder="제목"></b-form-input>
          <b-form-textarea class="mb-3" id="content" v-model="article.content" placeholder="내용을 입력해주세요." rows="10" max-rows="15"></b-form-textarea>
          <b-button type="button" variant="danger" class="m-1 float-right" @click="moveList">취소</b-button>
          <b-button type="submit" variant="warning" class="m-1 float-right">수정 완료</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardModify",
  computed: {
    ...mapState(boardStore, ["article"]),
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    this.detailArticle(articleNo);
  },
  methods: {
    ...mapActions(boardStore, ["detailArticle", "updateArticle"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userId && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userId.focus());
      err && !this.article.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.modifyArticle();
    },
    modifyArticle() {
      this.updateArticle(this.article);
    },
    moveList() {
      this.$router.push("/board");
    },
  },
};
</script>

<style></style>
