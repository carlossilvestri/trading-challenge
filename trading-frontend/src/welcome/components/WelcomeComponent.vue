<template>
  <header class="header animate__animated animate__fadeIn">
    <div class="header-background">
      <div class="cont-text-welcome">
        <div class="cont-img">
          <img
            class="animate__animated animate__backInDown img-vue"
            src="@/common/assets/logo.png"
            alt="Vue"
          />
        </div>
        <div class="cont-texts mt-5">
          <h3 class="title-welcome">Bienvenido a Trading VueJS + Java</h3>
          <p class="subtext">
            En esta prueba, evaluaremos su capacidad para construir la
            aplicación usando Vuejs con la librería trading-vue-js para el
            frontend y Java + Spring boot para el backend.
          </p>
        </div>
        <div class="cont-button-welcome mt-4">
          <router-link to="/trading" tag="button" class="btn-1"
            >Continuar</router-link
          >
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "WelcomeComponent",
};
</script>

<style lang="scss">
.header {
  .header-background {
    display: block;
    overflow: hidden;
    position: relative;
    width: 100%;
    height: 100vh;
    background: url("../../common/assets/img/trading.svg");
    background-position: center;
    background-size: cover;
    .cont-text-welcome {
      display: flex;
      flex-direction: column;
      top: 20%;
      position: relative;
      .cont-texts {
        background-color: rgba(0, 0, 0, 0.7);
      }
      .cont-img {
        text-align: center;
        .img-vue {
          max-width: 100%;
          display: block;
          margin: 0 auto;
        }
      }
      .title-welcome {
        font-family: $main-font;
        font-style: normal;
        font-weight: normal;
        font-size: 36px;
        line-height: 44px;
        text-align: center;
        color: #ffffff;
        margin: 0px;
      }
      .cont-button-welcome {
        margin: 0 auto;
        font-family: $secondary-font;
        font-weight: bold;
        font-size: 18px;
      }
      .subtext {
        font-family: $main-font;
        font-weight: normal;
        font-size: 18px;
        line-height: 30px;
        text-align: center;
        color: #ffffff;
      }
    }
  }
}
</style>
