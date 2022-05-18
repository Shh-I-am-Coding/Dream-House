<template>
  <div class="overflow-auto">
    <ul class="pagination justify-content-center">
      <li class="page-item">
        <a href="javascript:movePage(1);" class="page-link">«</a>
      </li>
      <li class="page-item">
        <a href="javascript:movePage(${navigation.startRange? 1 : navigation.startPage-1})" class="page-link">‹</a>
      </li>
      <c:forEach var="i" begin="${navigation.startPage}" end="${navigation.endPage}">
        <li class="${navigation.currentPage==i? 'page-item active' : 'page-item'}">
          <a href="javascript:movePage(${i})" class="page-link">${i}</a>
        </li>
      </c:forEach>
      <li class="page-item">
        <a href="javascript:movePage(${navigation.endRange? navigation.endPage : navigation.endPage+1})" class="page-link">›</a>
      </li>
      <li class="page-item">
        <a href="javascript:movePage(${navigation.totalPageCount})" class="page-link">»</a>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "BoardPaging",
  data() {
    return {};
  },
  computed: {
    ...mapState(["articles", "searchCondition"]),
  },
  watch: {
    currentPage: function () {
      this.$store.commit("SET_CURRENT_PAGE", this.currentPage);
      this.$store.dispatch("setArticles", this.searchCondition);
    },
  },
  methods: {},
};
</script>
