<template>
  <div class="container mt-2 px-5 mb-lg-5">
    <h3 class="p-3">회원정보 수정</h3>

    <validation-observer ref="observer" v-slot="{ handleSubmit }">
      <b-form @submit.stop.prevent="handleSubmit(onSubmit)">
        <b-input-group class="form-input" id="id">
          <b-form-input id="id" name="id" v-model="user.id" placeholder="아이디" readonly></b-form-input>
        </b-input-group>

        <validation-provider name="비밀번호" :rules="{ required: true, regex: '^(?=.*[a-zA-Z])(?=.*[0-9]).{7,44}$' }" v-slot="validationContext">
          <b-input-group class="form-input" id="password">
            <b-form-input
              id="password"
              name="password"
              type="password"
              v-model="user.password"
              :state="getValidationState(validationContext)"
              placeholder="비밀번호 (영문,숫자 포함 7자리 이상)"
              aria-describedby="password-feedback"
            ></b-form-input>
            <b-form-invalid-feedback class="feedback" id="password-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <validation-provider name="비밀번호 확인" :rules="{ required: true, confirmed: '비밀번호' }" v-slot="validationContext">
          <b-input-group class="form-input" id="password_confirm">
            <b-form-input
              id="password_confirm"
              name="password_confirm"
              type="password"
              v-model="password_confirm"
              :state="getValidationState(validationContext)"
              placeholder="비밀번호 확인"
              aria-describedby="confirm-feedback"
            ></b-form-input>
            <b-form-invalid-feedback class="feedback" id="confirm-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <validation-provider name="이름" :rules="{ required: true, max: 44 }" v-slot="validationContext">
          <b-input-group class="form-input" id="name">
            <b-form-input id="name" name="name" v-model="user.name" :state="getValidationState(validationContext)" placeholder="이름" aria-describedby="name-feedback"></b-form-input>
            <b-form-invalid-feedback class="feedback" id="name-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <validation-provider
          name="이메일"
          :rules="{ required: true, email: true, max: 44, regex: '^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$' }"
          v-slot="validationContext"
        >
          <b-input-group class="form-input" id="email">
            <b-form-input
              id="email"
              name="email"
              type="email"
              v-model="user.email"
              :state="getValidationState(validationContext)"
              placeholder="이메일"
              aria-describedby="email-feedback"
            ></b-form-input>
            <b-form-invalid-feedback class="feedback" id="email-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <validation-provider name="휴대폰번호" :rules="{ required: true, max: 44, regex: '^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$' }" v-slot="validationContext">
          <b-input-group class="form-input" id="phone">
            <b-form-input
              id="phone"
              name="phone"
              v-model="user.phone"
              :state="getValidationState(validationContext)"
              placeholder="휴대폰번호 (010-xxxx-xxxx)"
              aria-describedby="phone-feedback"
              @keyup="getPhoneMask()"
            ></b-form-input>
            <b-form-invalid-feedback class="feedback" id="phone-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <b-button type="submit" class="p-2" variant="warning">수정</b-button>
        <!-- <b-button type="button" variant="danger" @click="withdraw">탈퇴</b-button> -->
      </b-form>
    </validation-observer>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { ValidationObserver, ValidationProvider, extend } from "vee-validate";
import * as rules from "vee-validate/dist/rules";

Object.keys(rules).forEach((rule) => {
  extend(rule, {
    ...rules[rule],
    message: "'{_field_}' 이(가) 유효하지 않습니다.",
  });
});

const userStore = "userStore";

export default {
  name: "UserModify",
  data() {
    return {
      user: {
        id: null,
        password: null,
        name: null,
        email: null,
        phone: null,
      },
      password_confirm: null,
    };
  },
  components: {
    ValidationObserver,
    ValidationProvider,
  },
  created() {
    this.user.id = this.userInfo.id;
    this.user.password = this.userInfo.password;
    this.user.name = this.userInfo.name;
    this.user.email = this.userInfo.email;
    this.user.phone = this.userInfo.phone;
    this.password_confirm = this.userInfo.password;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["updateUserInfo", "deleteUserInfo"]),
    getValidationState({ dirty, validated, valid = null }) {
      return dirty || validated ? valid : null;
    },
    getPhoneMask() {
      this.user.phone = this.user.phone.replace(/[^0-9]/g, "").replace(/(\d{3})(\d{3,4})(\d{4})/, "$1-$2-$3");
    },
    onSubmit() {
      this.modify();
      this.$router.push({ name: "home" });
    },
    modify() {
      this.updateUserInfo(this.user);
    },
  },
};
</script>

<style scoped>
.form-control {
  display: inline;
}

.form-input {
  width: 50%;
  margin-top: 1.5%;
  margin-bottom: 2%;
}

.feedback {
  display: inline-block;
  text-align: left;
}
</style>
