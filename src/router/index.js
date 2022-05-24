import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/HomeView.vue"),
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: ":articleNo",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleNo",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleNo",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      {
        path: "write",
        name: "noticeRegister",
        component: () => import("@/components/notice/NoticeRegister.vue"),
      },
      {
        path: ":articleNo",
        name: "noticeDetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
      {
        path: "modify/:articleNo",
        name: "noticeModify",
        component: () => import("@/components/notice/NoticeModify.vue"),
      },
      {
        path: "delete/:articleNo",
        name: "noticeDelete",
        component: () => import("@/components/notice/NoticeDelete.vue"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView.vue"),
    children: [
      {
        path: "login",
        name: "userLogin",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "join",
        name: "userJoin",
        component: () => import("@/components/user/UserJoin.vue"),
      },
      {
        path: "myInfo",
        name: "userMyInfo",
        component: () => import("@/views/UserMyInfoView.vue"),
        children: [
          {
            path: "home",
            name: "userMyInfoHome",
            component: () => import("@/components/user/UserMyInfo.vue"),
          },
          {
            path: "modify",
            name: "userModify",
            component: () => import("@/components/user/item/UserModify.vue"),
          },
          {
            path: "withdraw",
            name: "userWithdraw",
            component: () => import("@/components/user/item/UserWithdraw.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/deal",
    name: "deal",
    component: () => import("@/views/DealView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
