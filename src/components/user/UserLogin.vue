<template>
  <section class="vh-100">
    <div class="container h-80">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card shadow-2-strong" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <h3 class="mb-5">로그인</h3>
              <b-form id="login-form">
                <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
                <div class="form-outline mb-4">
                  <input type="text" id="id" v-model="user.id" class="form-control form-control-lg" name="id" placeholder="아이디" required />
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="password" v-model="user.password" class="form-control form-control-lg" name="password" placeholder="비밀번호" required />
                </div>

                <button type="button" class="btn btn-warning btn-lg btn-block" id="loginBtn" @click="confirm">로그인</button>
                <button type="button" class="btn btn-block mb-5" @click="kakaoLogin"><img src="@/assets/img/kakao_login.png" /></button>
                <!--TODO 비밀번호찾기 라우터링크로-->
                <a href="${root}/user/findPassword">비밀번호 찾기</a>
              </b-form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const userStore = "userStore";
const interestStore = "interestStore";

export default {
  name: "UserStore",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
      url: "",
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  created() {
    this.SET_IS_LOGIN_ERROR(false);
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN_ERROR"]),
    ...mapActions(userStore, ["userConfirm", "getUser"]),
    ...mapActions(interestStore, ["getInterestList"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUser(token);
        this.getInterestList({
          id: this.user.id,
        });
        this.$router.push("/");
      }
    },
    kakaoLogin() {
      const API_KEY = process.env.VUE_APP_KAKAO_LOGIN_API_KEY;
      const REDIRECT_URI = process.env.VUE_APP_KAKAO_LOGIN_REDIRECT_URI;
      const url = "https://kauth.kakao.com/oauth/authorize?client_id=" + decodeURIComponent(API_KEY) + "&redirect_uri=" + decodeURIComponent(REDIRECT_URI) + "&response_type=code";
      window.location.replace(url);
    },
  },
};
</script>

<style></style>
