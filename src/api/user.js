import { apiInstance } from "./index.js";
{
  /* <script src="//developers.kakao.com/sdk/js/kakao.js"></script>; */
}

const api = apiInstance();

async function searchId(id, success, fail) {
  await api.get(`/user/${id}`).then(success).catch(fail);
}

async function confirmPassword(user, success, fail) {
  await api.post(`/user/confirmPassword`, user).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, user).then(success).catch(fail);
}

async function kakaoLogin() {
  //const API_KEY = process.env.VUE_APP_KAKAO_LOGIN_API_KEY;
  //const REDIRECT_URI = "http://localhost:8080/HappyHouse/user/login/oauth/kakao";
  // const url = "https://kauth.kakao.com/oauth/authorize?client_id=" + decodeURIComponent(API_KEY) + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
  //let i = window.location.assign(url);
  // Kakao.Auth.authorize({
  //   redirectUri: url,
  // });
  // if (window.Kakao.Auth.getAccessToken()) {
  //   window.Kakao.API.request({
  //     url: "/v1/user/unlink",
  //     success: function (response) {
  //       console.log(response);
  //     },
  //     fail: function (error) {
  //       console.log(error);
  //     },
  //   });
  //   window.Kakao.Auth.setAccessToken(undefined);
  // }
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
}

async function register(user, success, fail) {
  await api.post(`/user/`, user).then(success).catch(fail);
}

async function update(user, success, fail) {
  await api.put(`/user/`, user).then(success).catch(fail);
}

async function withdraw(id, success, fail) {
  await api.delete(`/user/${id}`).then(success).catch(fail);
}

export { searchId, confirmPassword, login, kakaoLogin, findById, register, update, withdraw };
