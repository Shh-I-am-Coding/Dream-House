<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit">
          <b-form-group id="userId-group" label="작성자:" label-for="userId" description="작성자를 입력하세요.">
            <b-form-input id="userId" v-model="article.userId" type="text" required placeholder="작성자 입력..." ref="userId"></b-form-input>
          </b-form-group>

          <b-form-group id="title-group" label="제목:" label-for="title" description="제목을 입력하세요.">
            <b-form-input id="title" v-model="article.title" type="text" required placeholder="제목 입력..." ref="title"></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea id="content" v-model="article.content" placeholder="내용 입력..." rows="10" max-rows="15" ref="content"></b-form-textarea>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1">수정</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "BoardModify",
  computed: {
    ...mapState(["article"]),
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    this.$store.dispatch("setArticle", articleNo);
  },
  methods: {
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
      this.$store.dispatch("updateArticle", this.article);
    },
  },
};
</script>

<style></style>
