const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    disableHostCheck: true,
  },
});
module.exports = {
  css: {
    loaderOptions: {
      scss: {
        additionalData: `
                  @import "@/common/scss/_variables.scss";
                  @import "@/common/scss/utilities.scss";
              `,
      },
    },
  },
  transpileDependencies: true,
  devServer: {
    allowedHosts: "all",
  },
};
