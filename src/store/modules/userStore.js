import jwt_decode from "jwt-decode";
import { searchId, login, findById, update, register, withdraw } from "@/api/user.js";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isDuplicated: true,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    isIdDuplicated: function (state) {
      return state.isDuplicated;
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
            alert("회원가입에 성공하였습니다.");
          } else {
            console.log("회원가입 실패!");
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
            alert("회원정보가 수정되었습니다.");
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
            alert("탈퇴되었습니다.");
            if (this.$route.path != "/") this.$router.push({ name: "home" });
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
