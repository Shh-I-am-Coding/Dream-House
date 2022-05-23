<template>
  <div class="m-5">
    <b-form @submit="onSubmit">
      <b-container fluid>
        <b-row class="my-1">
          <b-col sm="2">
            <b-form-select class="search" name="key" id="key" v-model="key" :options="keyOptions"></b-form-select>
          </b-col>
          <b-col sm="8">
            <b-form-input class="search" type="text" name="word" id="word" v-model="word" />
          </b-col>
          <b-button type="submit" variant="secondary">검색</b-button>
        </b-row>
      </b-container>
    </b-form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardSearch",

  data() {
    return {
      key: "title",
      keyOptions: [
        { value: "title", text: "제목" },
        { value: "userId", text: "작성자" },
        { value: "content", text: "내용" },
      ],
      word: "",
    };
  },
  computed: {
    ...mapState(boardStore, ["searchCondition"]),
  },
  methods: {
    ...mapActions(boardStore, ["setArticles"]),

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

      this.setArticles(searchCondition);
    },
  },
};
</script>

<style scoped>
.search {
  display: inline;
}
</style>
