import Vue from 'vue'
import Vuetify from "vuetify"
import '@babel/polyfill'
import 'api/resource'
import router from "router/router"
import Vuelidate from "vuelidate"
import App from 'pages/App.vue'
import store from "store/store";
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuelidate)
Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    store,
    router,
    render: a => a(App)
})
