import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "",
      component: () =>
        import(
          /* webpackChunkName: "Welcome" */ "./welcome/views/WelcomeView.vue"
        ),
    },
    {
      path: "/trading",
      name: "",
      component: () =>
        import(
          /* webpackChunkName: "app - ContainerMain" */ "./trading/views/TradingView.vue"
        ),
    },
    { path: "*", redirect: "/" },
  ],
});
