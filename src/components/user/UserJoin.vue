<template>
  <div class="container mt-5 px-5 mb-lg-5">
    <h3>회원가입</h3>

    <validation-observer ref="observer" v-slot="{ handleSubmit }">
      <b-form @submit.stop.prevent="handleSubmit(onSubmit)">
        <validation-provider name="아이디" :rules="{ required: true, max: 44, alpha_num: true }" v-slot="validationContext">
          <b-input-group class="form-input" id="id">
            <b-form-input id="id" name="id" v-model="user.id" :state="id_confirm && getValidationState(validationContext)" placeholder="아이디" aria-describedby="id-feedback"></b-form-input>
            <b-input-group-append>
              <b-button class="modBtn" @click="checkDuplicated">중복확인</b-button>
            </b-input-group-append>
            <b-form-invalid-feedback class="feedback" id="id-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

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
            <b-input-group-append>
              <b-button class="modBtn" @click="sendCertification(getValidationState(validationContext))">인증코드 전송</b-button>
            </b-input-group-append>
            <b-form-invalid-feedback class="feedback" id="email-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <validation-provider name="이메일 인증" :rules="{ required: true }" v-slot="validationContext">
          <b-input-group class="form-input" id="email_certification">
            <b-form-input
              id="email_certification"
              name="email_certification"
              type="text"
              v-model="email_code"
              :state="email_confirm && getValidationState(validationContext)"
              placeholder="이메일 인증코드 입력"
              aria-describedby="email-certification-feedback"
            ></b-form-input>
            <b-input-group-append>
              <b-button class="modBtn" @click="checkCertification">인증코드 확인</b-button>
            </b-input-group-append>
            <b-form-invalid-feedback class="feedback" id="email-certification-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
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
              @keyup="getPhoneMask(user.phone)"
            ></b-form-input>
            <b-form-invalid-feedback class="feedback" id="phone-feedback">{{ validationContext.errors[0] }}</b-form-invalid-feedback>
          </b-input-group>
        </validation-provider>

        <b-button type="submit" variant="warning">등록</b-button>
      </b-form>
    </validation-observer>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
import { ValidationObserver, ValidationProvider, extend } from "vee-validate";
import * as rules from "vee-validate/dist/rules";
import Swal from "sweetalert2";

Object.keys(rules).forEach((rule) => {
  extend(rule, {
    ...rules[rule],
    message: "'{_field_}' 이(가) 유효하지 않습니다.",
  });
});

const userStore = "userStore";

export default {
  name: "UserJoin",
  data() {
    return {
      user: {
        id: "",
        password: "",
        name: "",
        email: "",
        phone: "",
      },
      password_confirm: null,
      id_confirm: false,
      isKakaoUser: false,
      email_code: "",
      email_confirm: false,
    };
  },
  components: {
    ValidationObserver,
    ValidationProvider,
  },
  computed: {
    ...mapState(userStore, ["userInfo", "certifiedCode"]),
  },
  methods: {
    ...mapActions(userStore, ["join", "get", "checkIdDuplicated", "sendCertifiedCode"]),
    ...mapGetters(userStore, ["isIdDuplicated"]),
    getValidationState({ dirty, validated, valid = null }) {
      return dirty || validated ? valid : null;
    },
    async checkDuplicated() {
      await this.checkIdDuplicated(this.user.id);
      if (this.isIdDuplicated()) {
        Swal.fire({
          title: "아이디 중복! 🥲",
          text: "이미 존재하는 아이디입니다.",
          icon: "warning",
          confirmButtonText: "확인",
        });
        this.id_confirm = false;
      } else {
        Swal.fire({
          title: "사용 가능! 😸",
          text: "사용 가능한 아이디입니다.",
          icon: "success",
          confirmButtonText: "확인",
        });
        this.id_confirm = true;
      }
    },
    async sendCertification(isValid) {
      if (!isValid) {
        Swal.fire({
          title: "유효하지 않은 이메일입니다! 🙅‍♂️",
          text: "이메일을 올바르게 작성해주세요.",
          icon: "error",
          confirmButtonText: "확인",
        });
        return;
      }
      await this.sendCertifiedCode(this.user.email);
      Swal.fire({
        title: "인증코드 전송! 📨",
        text: "메일 확인 후 인증코드를 입력해주세요.",
        icon: "success",
        confirmButtonText: "확인",
      });
    },
    checkCertification() {
      if (this.email_code === this.certifiedCode) {
        this.email_confirm = true;
        Swal.fire({
          title: "인증 성공! 🎉",
          text: "이메일 인증에 성공하였습니다.",
          icon: "success",
          confirmButtonText: "확인",
        });
      } else {
        Swal.fire({
          title: "인증코드가 다릅니다! 🥲",
          text: "다시 인증해주세요.",
          icon: "error",
          confirmButtonText: "확인",
        });
      }
    },
    getPhoneMask(phone) {
      this.user.phone = phone.replace(/[^0-9]/g, "").replace(/(\d{3})(\d{3,4})(\d{4})/, "$1-$2-$3");
    },
    async onSubmit() {
      if (this.isIdDuplicated() || !this.id_confirm) {
        Swal.fire({
          title: "실패! 😵",
          text: "아이디 중복 여부를 확인해주세요.",
          icon: "error",
          confirmButtonText: "확인",
        });
        return;
      }
      this.join(this.user);
      this.id_confirm = false;
      this.email_confirm = false;
      this.$router.push({ name: "home" });
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
  margin-left: auto;
  margin-right: auto;
  margin-top: 1.5%;
  margin-bottom: 2%;
}

.feedback {
  display: inline-block;
  text-align: left;
}

.modBtn {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #b4e2fc;
  border-color: #99daff;
}
.modBtn,
.modBtn:not(:disabled):not(.disabled):active,
.modBtn:not(:disabled):not(.disabled).active,
.modBtn:focus,
.modBtn.focus,
.show > .modBtn.dropdown-toggle {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #b4e2fc;
  border-color: #99daff;
}
.modBtn:hover {
  color: rgb(70, 70, 70);
  font-weight: bold;
  background-color: #99daff;
  border-color: #99daff;
}
</style>
