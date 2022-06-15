import { apiInstance } from "./index.js";

const api = apiInstance();

function create(article, success, fail) {
  api.post(`/board/`, article).then(success).catch(fail);
}

function update(article, success, fail) {
  api.put(`/board/`, article).then(success).catch(fail);
}

function remove(articleNo, success, fail) {
  api.delete(`/board/${articleNo}`).then(success).catch(fail);
}

function detail(articleNo, success, fail) {
  api.get(`/board/${articleNo}`).then(success).catch(fail);
}

async function getArticles(param, success, fail) {
  await api.get(`/board/`, { params: param }).then(success).catch(fail);
}

export { create, update, remove, detail, getArticles };
