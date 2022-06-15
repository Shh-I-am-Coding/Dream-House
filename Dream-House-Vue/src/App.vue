<template>
  <div id="app">
    <navi-bar></navi-bar>
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
#app {
  font-family: "netmarble", Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

@font-face {
  font-family: "BMJUA_ttf";
  src: url("assets/fonts/BMJUA_ttf.ttf") format("truetype");
  font-weight: 300;
}

@font-face {
  font-family: "NEXON";
  src: url("assets/fonts/NEXON.otf") format("truetype");
  font-weight: 300;
}

@font-face {
  font-family: "CookieRun";
  src: url("assets/fonts/CookieRun Regular.ttf") format("truetype");
  font-weight: 300;
}

@font-face {
  font-family: "netmarble";
  src: url("assets/fonts/netmarbleL.ttf") format("truetype");
  font-weight: 300;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}
</style>
