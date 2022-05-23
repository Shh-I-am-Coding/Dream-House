import { apiInstance, addressInstance } from "./index.js";

const api = apiInstance();
const address = addressInstance();

function sidoList(sucess, fail) {
  address
    .get(``, { params: { regcode_pattern: "*00000000" } })
    .then(sucess)
    .catch(fail); //params아닌 문자열이 가능?
}

function gugunList(params, sucess, fail) {
  address.get(``, { params: params }).then(sucess).catch(fail);
}

function dongList(params, sucess, fail) {
  address.get(``, { params: params }).then(sucess).catch(fail);
}

function dealList(params, sucess, fail) {
  api.get(`/deal/getAptNameList`, { params: params }).then(sucess).catch(fail);
}

function APTList(params, sucess, fail) {
  api.get(`/deal/getApt`, { params: params }).then(sucess).catch(fail);
}

export { sidoList, gugunList, dongList, dealList, APTList };
