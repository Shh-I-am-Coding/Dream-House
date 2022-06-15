import axios from "axios";
import { API_BASE_URL, API_ADDRESS_URL } from "@/config";

function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function addressInstance() {
  const instance = axios.create({
    baseURL: API_ADDRESS_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance, addressInstance };
