<template>
  <!-- <h1><b>꿈의 집을 찾아드립니다!</b></h1> -->
  <!-- <carousel :autoplay="true" :nav="false" :dots="false" :items="1" style="width: 400px; margin: auto">
      <b-img :src="require('@/assets/img/main_house10.jpg')" />
      <b-img :src="require('@/assets/img/main_house09.jpg')" />
      <b-img :src="require('@/assets/img/main_house08.jpg')" />
      <b-img :src="require('@/assets/img/main_house07.jpg')" />
    </carousel> -->

  <!-- <b-container class="bv-example-row bv-example-row-flex-cols">
      <b-row class="h-100">
        <b-col class="h-100" style="background-color: orange">1 of 3</b-col>
        <b-col>2 of 3</b-col>
      </b-row>
    </b-container> -->
  <!-- </div> -->
  <b-container fluid>
    <b-row class="text-left">
      <b-col style="height: 88vh" class="w-100 d-flex align-items-center justify-content-center">
        <!-- <h1>꿈의집</h1> -->
        <b-img :src="require('@/assets/img/logo.png')" style="width: 230px"></b-img>
      </b-col>
      <b-col style="background-color: #ffb52b; height: 88vh; color: #3f3f3f; flex-direction: column" class="w-100 p-4 d-flex align-items-center justify-content-center">
        <h1 class="m-5">
          가장 편안해야할 내 집,<br />
          가장 행복해야할 내 집,<br /><br />
          <b>모두가 꿈꾸는 집을 찾아주는 곳</b>
        </h1>
        <!-- <b-button style="background-color: white; color: black">집 찾으러 가기</b-button> -->
        <p>실시간 인기 매물</p>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped></style>
