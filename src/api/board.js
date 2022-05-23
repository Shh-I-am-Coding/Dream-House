import { apiInstance } from "./index.js";

const api = apiInstance();

async function create(article, success, fail) {
  await api.post(`/board/`, article).then(success).catch(fail);
}

export { create };
