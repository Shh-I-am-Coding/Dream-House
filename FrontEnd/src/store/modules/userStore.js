import jwt_decode from "jwt-decode";
import { searchId, confirmPassword, login, parseKakaoUser, sendMail, findById, update, register, withdraw } from "@/api/user.js";
import router from "@/router";
import Swal from "sweetalert2";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isKakaoLogin: false,
    isLoginError: false,
    isDuplicated: true,
    isPasswordConfirmed: false,
    userInfo: null,
    certifiedCode: "",
  },
  getters: {
    getUserInfo: function (state) {
      return state.userInfo;
    },
    isIdDuplicated: function (state) {
      return state.isDuplicated;
    },
    getPasswordConfirmed: function (state) {
      return state.isPasswordConfirmed;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_KAKAO_LOGIN: (state, isKakaoLogin) => {
      state.isKakaoLogin = isKakaoLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_DUPLICATED: (state, isDuplicated) => {
      state.isDuplicated = isDuplicated;
    },
    SET_IS_PASSWORD_CONFIRMED: (state, isPasswordConfirmed) => {
      state.isPasswordConfirmed = isPasswordConfirmed;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_CERTIFIED_CODE: (state, certifiedCode) => {
      state.certifiedCode = certifiedCode;
    },
  },
  actions: {
    async checkIdDuplicated({ commit }, id) {
      await searchId(id, (response) => {
        if (response.data === "success") {
          commit("SET_IS_DUPLICATED", true);
        } else {
          commit("SET_IS_DUPLICATED", false);
        }
      });
    },
    async checkPassword({ commit }, user) {
      await confirmPassword(user, (response) => {
        if (response.data === "success") {
          commit("SET_IS_PASSWORD_CONFIRMED", true);
        } else {
          commit("SET_IS_PASSWORD_CONFIRMED", false);
        }
      });
    },
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUser({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.id,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("?????? ?????? ??????!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async checkKakaoUser({ commit }, code) {
      await parseKakaoUser(code, (response) => {
        let kakaoUser = response.data;
        commit("SET_USER_INFO", kakaoUser);
        commit("SET_IS_LOGIN", true);
        commit("SET_IS_KAKAO_LOGIN", true);
        commit("SET_IS_LOGIN_ERROR", false);
      });
    },
    async sendCertifiedCode({ commit }, email) {
      await sendMail(email, (response) => {
        commit("SET_CERTIFIED_CODE", response.data);
      });
    },
    async join({ commit }, user) {
      await register(
        user,
        (response) => {
          if (response.data == "success") {
            commit("SET_IS_LOGIN", false);
            Swal.fire({
              title: "???????????????! ?????????????",
              text: "??????????????? ?????????????????????.",
              icon: "success",
              confirmButtonText: "??????",
            });
          } else {
            Swal.fire({
              title: "??????! ????",
              text: "??????????????? ?????????????????????.",
              icon: "error",
              confirmButtonText: "??????",
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async updateUserInfo({ commit }, user) {
      await update(
        user,
        (response) => {
          if (response.data == "success") {
            commit("SET_USER_INFO", user);
            Swal.fire({
              title: "??????! ????",
              text: "??????????????? ?????????????????????.",
              icon: "success",
              confirmButtonText: "??????",
            });
          } else {
            console.log("?????? ??????!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async deleteUserInfo({ commit }, userId) {
      await withdraw(
        userId,
        (response) => {
          if (response.data == "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_KAKAO_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_DUPLICATED", true);
            sessionStorage.removeItem("access-token");
            Swal.fire({
              title: "??????! ????",
              text: "??????????????? ?????????????????????.",
              icon: "success",
              confirmButtonText: "??????",
            });
            router.push({ name: "home" });
          } else {
            console.log("?????? ??????!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
