<template>
  <div class="overflow-auto">
    <ul class="pagination justify-content-center">
      <li class="page-item">
        <a @click="movePage(1)" class="page-link">«</a>
      </li>
      <li class="page-item">
        <a @click="movePage(pageNavigation.startRange ? 1 : pageNavigation.startPage - 1)" class="page-link">‹</a>
      </li>
      <li v-for="i in range(pageNavigation.startPage, pageNavigation.endPage)" :key="i">
        <a @click="movePage(i)" class="page-link" :class="{ active: pageNavigation.currentPage == i }">{{ i }}</a>
      </li>
      <li class="page-item">
        <a @click="movePage(pageNavigation.endRange ? pageNavigation.endPage : pageNavigation.endPage + 1)" class="page-link">›</a>
      </li>
      <li class="page-item">
        <a @click="movePage(pageNavigation.totalPageCount)" class="page-link">»</a>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardPaging",
  data() {
    return {};
  },
  computed: {
    ...mapState(boardStore, ["articles", "searchCondition", "pageNavigation"]),
  },
  methods: {
    ...mapMutations(boardStore, ["SET_CURRENT_PAGE"]),
    ...mapActions(boardStore, ["setArticles"]),
    movePage(page) {
      this.SET_CURRENT_PAGE(page);
      this.setArticles(this.searchCondition);
    },
    range(start, end) {
      let list = [];
      for (let i = start; i <= end; i++) list.push(i);
      return list;
    },
  },
};
</script>
<style scope>
.page-link:hover {
  background-color: rgb(255, 235, 208);
  color: orange;
  cursor: pointer;
}

.page-link {
  color: rgb(252, 181, 0);
  font-weight: bold;
}

.active {
  font-size: 1.2rem;
  background-color: rgb(255, 211, 90);
  color: white;
}
</style>
