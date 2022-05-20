<template>
  <div>
    <b-navbar toggleable="lg" type="light" style="background-color: #ffcd90; height: 90px">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'home' }">해피하우스</router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#" class="link">
            <router-link :to="{ name: 'house' }">매매가 검색</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'home' }">관심지역 설정</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'board' }">문의게시판</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'home' }">마이페이지</router-link>
          </b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#">
            <router-link :to="{ name: 'login' }">로그인</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'home' }">회원가입</router-link>
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script></script>

<style scoped>
.menuItem {
  color: rgb(170, 170, 170);
}
</style>
