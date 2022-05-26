<template>
  <b-container class="bv-example-row mt-4 text-center">
    <h3>
      <b>공지사항<img src="@/assets/img/notice_logo.png" width="4%" class="mb-2 ml-2" /></b>
    </h3>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "NoticeView",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
</style>
