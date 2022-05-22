import jwt_decode from "jwt-decode";
import { searchId, confirmPassword, login, findById, update, register, withdraw } from "@/api/user.js";
import router from "@/router";
import Swal from "sweetalert2";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isDuplicated: true,
    isPasswordConfirmed: false,
    userInfo: null,
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
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.id,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async join({ commit }, user) {
      await register(
        user,
        (response) => {
          if (response.data == "success") {
            commit("SET_IS_LOGIN", false);
            Swal.fire({
              title: "안녕하세요! 🙋‍♂️",
              text: "회원가입에 성공하였습니다.",
              icon: "success",
              confirmButtonText: "확인",
            });
          } else {
            Swal.fire({
              title: "실패! 😨",
              text: "회원가입에 실패하였습니다.",
              icon: "error",
              confirmButtonText: "확인",
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
              title: "성공! 🎉",
              text: "회원정보가 수정되었습니다.",
              icon: "success",
              confirmButtonText: "확인",
            });
          } else {
            console.log("수정 실패!");
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
            commit("SET_USER_INFO", null);
            commit("SET_IS_DUPLICATED", true);
            sessionStorage.removeItem("access-token");
            Swal.fire({
              title: "성공! 👏",
              text: "회원탈퇴가 완료되었습니다.",
              icon: "success",
              confirmButtonText: "확인",
            });
            router.push({ name: "home" });
          } else {
            console.log("탈퇴 불가!");
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
