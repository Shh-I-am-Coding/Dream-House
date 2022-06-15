import { apiInstance } from "./index.js";

const api = apiInstance();

function create(article, success, fail) {
  api.post(`/notice/`, article).then(success).catch(fail);
}

function update(article, success, fail) {
  api.put(`/notice/`, article).then(success).catch(fail);
}

function remove(articleNo, success, fail) {
  api.delete(`/notice/${articleNo}`).then(success).catch(fail);
}

function detail(articleNo, success, fail) {
  api.get(`/notice/${articleNo}`).then(success).catch(fail);
}

async function getArticles(param, success, fail) {
  await api.get(`/notice/`, { params: param }).then(success).catch(fail);
}

export { create, update, remove, detail, getArticles };
