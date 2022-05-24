import { apiInstance } from "./index.js";

const api = apiInstance();

function getList(params, sucess, fail) {
  api.get(`/interest/`, { params: params }).then(sucess).catch(fail);
}

function create(params, sucess, fail) {
  api.put(`/interest/`, params).then(sucess).catch(fail);
}
function remove(params, sucess, fail) {
  api.delete(`/interest/`, { params: params }).then(sucess).catch(fail);
}
export { getList, create, remove };
