<template>
  <div>
    <b-form @submit="onSubmit">
      <!-- <b-form-input type="hidden" name="currentPage" id="currentPage" /> -->
      <b-form-select name="key" id="key" v-model="key" :options="keyOptions" size="sm"></b-form-select>
      <b-form-input type="text" name="word" id="word" v-model="word" />
      <b-button type="submit" variant="primary" class="m-1">검색</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "BoardSearch",

  data() {
    return {
      key: "none",
      keyOptions: [
        { value: "none", text: "선택해주세요." },
        { value: "title", text: "제목" },
        { value: "userId", text: "작성자" },
        { value: "content", text: "내용" },
      ],
      word: "",
    };
  },
  computed: {
    ...mapState(["searchCondition"]),
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.search();
    },
    search() {
      const searchCondition = {
        key: this.key,
        word: this.word,
        currentPage: 1,
      };

      this.$store.dispatch("setArticles", searchCondition);
    },
  },
};
</script>
