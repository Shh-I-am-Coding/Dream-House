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
import Swal from "sweetalert2";

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
        Swal.fire({
          title: "틀렸어요! 🥴",
          text: "비밀번호를 다시 확인해주세요.",
          icon: "error",
          confirmButtonText: "확인",
        });
      } else {
        this.password_confirm = true;
        Swal.fire({
          title: "확인 성공!",
          text: "비밀번호 확인에 성공하였습니다.",
          icon: "success",
          confirmButtonText: "확인",
        });
      }
    },
    withdraw() {
      Swal.fire({
        title: "탈퇴하시겠습니까? 🤔",
        text: "한번 탈퇴하면, 되돌릴 수 없습니다.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "네",
        cancelButtonText: "아니오",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteUserInfo(this.userInfo.id);
        }
      });
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
