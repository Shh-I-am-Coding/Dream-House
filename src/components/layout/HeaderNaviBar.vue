<template>
  <div style="border-bottom: 1px solid #dfdfdf">
    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'home' }"> <b-img :src="require('@/assets/img/logo05.png')" style="padding: 10px; width: 200px"></b-img></router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link :to="{ name: 'deal' }" class="menuItem">매매가 검색</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'home' }" class="menuItem">관심지역 설정</router-link>
          </b-nav-item>
          <b-nav-item class="link align-self-center menuItem" @click.prevent="qna">문의게시판</b-nav-item>

          <!-- <b-nav-item href="#">
            <router-link :to="{ name: 'board' }" class="menuItem">문의게시판</router-link>
          </b-nav-item> -->
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto" v-if="!userInfo">
          <b-nav-item href="#">
            <router-link :to="{ name: 'userLogin' }" class="menuItem">로그인</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'userJoin' }" class="menuItem">회원가입</router-link>
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item class="align-self-center"
            ><router-link :to="{ name: 'userMyInfoHome' }" class="link align-self-center">
              <b-avatar variant="primary" v-text="userInfo.id.charAt(0).toUpperCase()"></b-avatar> {{ userInfo.name }} 님 환영합니다.</router-link
            >
          </b-nav-item>
          <b-nav-item class="align-self-center"><router-link :to="{ name: 'userMyInfoHome' }" class="link align-self-center menuItem">마이페이지</router-link></b-nav-item>
          <b-nav-item class="link align-self-center menuItem" @click.prevent="logout">로그아웃</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import Swal from "sweetalert2";

const userStore = "userStore";
const boardStore = "boardStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO", "SET_IS_DUPLICATED"]),
    ...mapMutations(boardStore, ["SET_IS_REMAIN_SEARCH_CONDITION"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      this.SET_IS_DUPLICATED(true);
      sessionStorage.removeItem("access-token");
      Swal.fire({
        title: "로그아웃 성공! 🙋‍♀️",
        text: "로그아웃 되었습니다.",
        icon: "success",
        confirmButtonText: "확인",
      });
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    async qna() {
      this.SET_IS_REMAIN_SEARCH_CONDITION(false);
      this.$router.push({ name: "board" });
    },
  },
};
</script>

<style scoped>
.menuItem {
  color: #2c3e50;
  font-size: 1.1em;
}

a:hover {
  text-decoration: none;
  color: rgb(255, 178, 70);
}

.navbar-light .navbar-nav .nav-link {
  color: #2c3e50;
}

.navbar-light .navbar-nav .nav-link:hover {
  text-decoration: none;
  color: rgb(255, 178, 70);
}
</style>
