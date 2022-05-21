import { apiInstance } from "./index.js";

const api = apiInstance();

async function searchId(id, success, fail) {
  await api.get(`/user/${id}`).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, user).then(success).catch(fail);
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

export { searchId, login, findById, register, update, withdraw };
