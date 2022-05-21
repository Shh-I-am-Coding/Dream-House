<template>
  <div v-if="userInfo">
    <b-form v-if="userInfo">
      <b-form-group id="input-group-1" label="아이디" label-for="input-1">
        <b-form-input id="input-1" v-model="userInfo.id" required readonly></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="비밀번호:" label-for="input-2">
        <b-form-input id="input-2" v-model="userInfo.password" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="이름" label-for="input-3">
        <b-form-input id="input-3" v-model="userInfo.name" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-4" label="이메일" label-for="input-4">
        <b-form-input id="input-4" v-model="userInfo.email" required></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-5" label="전화번호" label-for="input-5">
        <b-form-input id="input-5" v-model="userInfo.phone" required></b-form-input>
      </b-form-group>

      <b-button type="button" variant="warning" @click="modify">수정</b-button>
      <b-button type="button" variant="danger" @click="withdraw">탈퇴</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserMyInfo",
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["updateUserInfo", "deleteUserInfo"]),
    modify() {
      this.updateUserInfo(this.userInfo);
    },
    withdraw() {
      this.deleteUserInfo(this.userInfo.id);
    },
  },
};
</script>

<style></style>
