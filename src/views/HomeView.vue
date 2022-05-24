<template>
  <div id="app">
    <div class="w-100">
      <hooper :itemsToShow="3" pagination="no" :autoPlay="true" :playSpeed="2000" :infiniteScroll="true">
        <slide style="height: 80vh; flex-direction: column" class="d-flex align-items-center justify-content-center">
          <b-img :src="require('@/assets/img/logo.png')" style="width: 230px"></b-img>
        </slide>
        <slide style="background-color: #ffd17c; color: #3f3f3f; flex-direction: column; height: 80vh" class="d-flex align-items-center justify-content-center">
          <h1 class="m-5">
            가장 편안해야할 내 집,<br />
            가장 행복해야할 내 집,<br /><br />
            <b>모두가 꿈꾸는 집을 찾아주는 곳</b>
          </h1>
        </slide>
        <slide style="height: 90vh; color: #3f3f3f; flex-direction: column" class="d-flex align-items-center justify-content-center">
          <h1><b>오늘의 뉴스</b></h1>
          <b-card-group deck class="mt-4 mr-5">
            <b-card border-variant="light" header="" class="text-left">
              <b-card-text>
                <ul style="color: black; list-style: none">
                  <li v-for="(article, index) in news" :key="index" style="border-bottom: 1px solid #d1af50; margin: 5px; padding: 8px">
                    <a :href="article.link" v-html="article.title"></a>
                  </li>
                </ul>
              </b-card-text>
            </b-card>
          </b-card-group>
        </slide>
        <slide style="height: 90vh; color: #3f3f3f; flex-direction: column" class="d-flex align-items-center justify-content-center">
          <h1><b>오늘의 뉴스</b></h1>
          <b-card-group deck class="mt-4 mr-5">
            <b-card border-variant="light" header="" class="text-left">
              <b-card-text>
                <ul style="color: black; list-style: none">
                  <li v-for="(article, index) in news" :key="index" style="border-bottom: 1px solid #88ab88; margin: 5px; padding: 8px">
                    <a :href="article.link" v-html="article.title"></a>
                  </li>
                </ul>
              </b-card-text>
            </b-card>
          </b-card-group>
        </slide>
        <slide style="height: 90vh; color: #3f3f3f; flex-direction: column" class="d-flex align-items-center justify-content-center">
          <h1><b>오늘의 뉴스</b></h1>
          <b-card-group deck class="mt-4 mr-5">
            <b-card border-variant="light" header="" class="text-left">
              <b-card-text>
                <ul style="color: black; list-style: none">
                  <li v-for="(article, index) in news" :key="index" style="border-bottom: 1px solid #88ab88; margin: 5px; padding: 8px">
                    <b><a :href="article.link" v-html="article.title"></a></b>
                  </li>
                </ul>
              </b-card-text>
            </b-card>
          </b-card-group>
        </slide>
      </hooper>
    </div>
  </div>
  <!-- <b-container fluid> </b-container> -->
</template>

<script>
import { Hooper, Slide } from "hooper";
import "hooper/dist/hooper.css";
import { getNews } from "@/api/news.js";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  data() {
    return {
      news: {
        title: "",
        link: "",
      },
    };
  },
  created() {
    getNews((response) => {
      this.news = response.data;
    });
  },
  components: {
    Hooper,
    Slide,
  },
};
</script>

<style>
.hooper-list {
  overflow: inherit;
}

li a {
  font-weight: bold;
  color: #e77920;
}

li a:hover {
  color: orange;
}
</style>
