import Vue from 'vue'
import VueRouter from 'vue-router'
import SearchForm from "pages/SearchPage.vue"
import AnnouncementsList from "pages/AnnouncementsPage.vue"
import Profile from "pages/Profile.vue"
import NewAnnouncementPage from "pages/NewAnnouncementPage.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: SearchForm},
    {path: '/announcements', component: AnnouncementsList},
    {path: '/profile', component: Profile},
    {path: '/addNew', component: NewAnnouncementPage},
    {path: '*', component: SearchForm}
]

export default new VueRouter({
    mode: 'history',
    routes
})