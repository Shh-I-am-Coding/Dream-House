import { apiInstance, addressInstance } from "./index.js";

const api = apiInstance();
const address = addressInstance();

function sidoList(success, fail) {
  address
    .get(``, { params: { regcode_pattern: "*00000000" } })
    .then(success)
    .catch(fail); //params아닌 문자열이 가능?
}

function gugunList(params, success, fail) {
  address.get(``, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  address.get(``, { params: params }).then(success).catch(fail);
}

function dealList(params, success, fail) {
  api.get(`/deal/getAptNameList`, { params: params }).then(success).catch(fail);
}

function APTList(params, success, fail) {
  api.get(`/deal/getApt`, { params: params }).then(success).catch(fail);
}

function dealAvg(params, success, fail) {
  api.get("/deal/getDealAvg", { params: params }).then(success).catch(fail);
}

function topHits(success, fail) {
  api.get(`/deal/getTopHits`).then(success).catch(fail);
}
export { sidoList, gugunList, dongList, dealList, APTList, dealAvg, topHits };
