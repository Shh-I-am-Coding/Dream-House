import { apiInstance } from "./index.js";

const api = apiInstance();

function getNews(success, fail) {
  api.get("/news/").then(success).catch(fail);
}

export { getNews };
