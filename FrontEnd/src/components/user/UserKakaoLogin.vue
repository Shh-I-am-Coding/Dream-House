<template>
  <div class="container mt-5 px-5 mb-lg-5">
    <h3>카카오 회원가입</h3>
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "UserKakaoLogin",
  created() {
    let code = this.$route.query.code;
    this.checkKakaoUser(code);
    // let token = sessionStorage.getItem("access-token");
    if (this.isLogin) {
      this.getInterestList({
        id: this.user.id,
      });
    }
    this.SET_IS_KAKAO_LOGIN(true);
    this.$router.push({ name: "home" });
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_KAKAO_LOGIN"]),
    ...mapActions(userStore, ["checkKakaoUser"]),
  },
};
</script>
