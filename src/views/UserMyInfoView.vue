<template>
  <div class="h-100">
    <b-row class="m-5">
      <b-col
        ><div class="float-left text-left p-3">
          <br /><br /><br />
          <p><router-link :to="{ name: 'userMyInfoHome' }" class="link">마이페이지</router-link></p>
          <p><router-link :to="{ name: 'userModify' }" class="link">회원정보 수정</router-link></p>
          <p><router-link :to="{ name: 'userWithdraw' }" class="link">회원탈퇴</router-link></p>
          <p><router-link :to="{ name: 'house' }" class="link">관심지역 관리</router-link></p>
        </div></b-col
      >
      <b-col cols="9" class="h-100 text-left">
        <router-view></router-view>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserMyInfoView",
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {},
};
</script>

<style scoped>
.link {
  color: orange;
  font-weight: bold;
  font-size: 18px;
}
</style>
