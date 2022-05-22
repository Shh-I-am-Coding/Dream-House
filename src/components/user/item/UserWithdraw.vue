<template>
  <div class="container mt-5 px-5 mb-lg-5">
    <h3 class="p-3">회원 탈퇴</h3>

    <div class="p-3">
      <h4>비밀번호 확인</h4>
      <b-form>
        <b-input-group class="form-input">
          <b-form-input type="password" v-model="user.password" placeholder="비밀번호 확인"></b-form-input>
          <b-button type="button" @click="onSubmit">비밀번호 확인</b-button>
        </b-input-group>
      </b-form>
      <b-button v-show="password_confirm" type="button" variant="danger" @click="withdraw" style="width: 150px; margin-top: 20px">탈퇴</b-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";

const userStore = "userStore";

export default {
  name: "UserWithdraw",
  data() {
    return {
      user: {
        id: "",
        password: "",
      },
      password_confirm: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo", "isPasswordConfirmed"]),
  },
  created() {
    this.password_confirm = false;
    this.user.id = this.userInfo.id;
  },
  methods: {
    ...mapGetters(userStore, ["getPasswordConfirmed"]),
    ...mapActions(userStore, ["checkPassword", "deleteUserInfo"]),
    async onSubmit() {
      await this.checkPassword(this.user);
      if (!this.getPasswordConfirmed()) {
        this.password_confirm = false;
        alert("비밀번호가 틀립니다.");
      } else {
        this.password_confirm = true;
        alert("비밀번호를 확인했습니다.");
      }
    },
    withdraw() {
      let isWithDraw = confirm("탈퇴하시겠습니까?");
      if (isWithDraw) this.deleteUserInfo(this.userInfo.id);
    },
  },
};
</script>

<style scoped>
.form-input {
  width: 50%;
  margin-top: 1.5%;
  margin-bottom: 2%;
}
</style>
