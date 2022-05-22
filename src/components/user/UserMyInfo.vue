<template>
  <div>
    <h1 class="m-2">안녕하세요, {{ userInfo.name }} 님 👋</h1>
    <div>
      <b-img :src="require('@/assets/img/myinfo_home.png')" rounded fluid alt="Responsive image" width="1000"></b-img>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserMyInfo",
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {},
};
</script>

<style scoped>
.link {
  color: orange;
  font-weight: bold;
  font-size: 18px;
}
</style>
