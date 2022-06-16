<template>
  <div class="m-5">
    <b-form @submit="onSubmit">
      <b-container fluid>
        <b-row class="my-1">
          <b-col sm="2">
            <b-form-select class="search" name="key" id="key" v-model="searchCondition.key" :options="keyOptions"></b-form-select>
          </b-col>
          <b-col sm="8">
            <b-form-input class="search" type="text" name="word" id="word" v-model="searchCondition.word" />
          </b-col>
          <b-button type="submit" class="seachBtn">검색</b-button>
        </b-row>
      </b-container>
    </b-form>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardSearch",

  data() {
    return {
      keyOptions: [
        { value: "title", text: "제목" },
        { value: "userId", text: "작성자" },
        { value: "content", text: "내용" },
      ],
    };
  },
  created() {
    if (!this.isReaminSearchCondition) {
      this.searchCondition.key = "title";
      this.searchCondition.word = "";
      this.searchCondition.currentPage = 1;
      console.log(this.searchCondition);
    }
  },
  computed: {
    ...mapState(boardStore, ["searchCondition", "isReaminSearchCondition"]),
  },
  methods: {
    ...mapActions(boardStore, ["setArticles"]),
    ...mapMutations(boardStore, ["SET_IS_REMAIN_BOARD_SEARCH_CONDITION"]),

    onSubmit(event) {
      event.preventDefault();
      this.search();
    },
    search() {
      this.SET_IS_REMAIN_BOARD_SEARCH_CONDITION(true);
      this.setArticles(this.searchCondition);
    },
  },
};
</script>

<style scoped>
.search {
  display: inline;
}
.seachBtn {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #b4e2fc;
}
.seachBtn:hover {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #99daff;
}
</style>
