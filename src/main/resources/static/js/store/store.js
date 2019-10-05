import Vue from 'vue'
import Vuex from 'vuex'
import announcementsApi from "api/announcements"
import userApi from "api/user"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        announcement: '',
        announcements: [],
        profile: frontendData.profile
    },
    mutations: {
        addAnnouncementMutation(state, announcement) {
            state.announcements = [
                ...state.announcements,
                announcement
            ]
        },
        updateAnnouncementMutation(state, announcement) {
            const updateIndex = state.announcements.findIndex(item => item.id === announcement.id)

            state.announcements = [
                ...state.announcements.slice(0, updateIndex),
                announcement,
                ...state.announcements.slice(updateIndex + 1)
            ]
        },
        removeAnnouncementMutation(state, announcement) {
            const deletionIndex = state.announcements.findIndex(item => item.id === announcement.id)

            if (deletionIndex > -1) {
                state.announcements = [
                    ...state.announcements.slice(0, deletionIndex),
                    ...state.announcements.slice(deletionIndex + 1)
                ]
            }
        },
        removeAllAnnouncementsMutation(state) {
            state.announcements = []
        },
        redactAnnouncementMutation(state, announcement) {
            state.announcement = announcement
        },
        updateUserMutation(state, user) {
            state.profile = user
            frontendData.profile = user
        }
    },
    actions: {
        async addAnnouncementAction({commit}, announcement) {
            const result = await announcementsApi.add(announcement)
            const data = await result.json()
            commit('addAnnouncementMutation', data)
        },
        async updateAnnouncementAction({commit}, announcement) {
            const result = await announcementsApi.update(announcement)
            const data = await result.json()
            commit('updateAnnouncementMutation', data)
        },
        async removeAnnouncementAction({commit}, announcement) {
            const result = await announcementsApi.remove(announcement.id)
            if (result.ok && result.data === true) {
                commit('removeAnnouncementMutation', announcement)
            }
        },
        async getAnnouncementsByRoomsCount({commit, state}, roomsCount) {
            const result = await announcementsApi.get(roomsCount)
            const data = await result.json()
            commit('removeAllAnnouncementsMutation')

            data.forEach(flat => commit('addAnnouncementMutation', flat))
        },
        async getAnnouncementById({commit}, id) {
            const result = await announcementsApi.getById(id)
            const data = await result.json()

            commit('redactAnnouncementMutation', data)
        },
        async updateUserInfo({commit}, user) {
            const result = await userApi.update(user);

            if (result.data === "") {
                return false;
            } else {
                commit('updateUserMutation', result.data);
                return true;
            }
        },
        async getAnnouncementsByUserId({commit, state}, userId) {
            const result = await announcementsApi.getByUserId(userId)
            const data = await result.json()
            commit('removeAllAnnouncementsMutation')

            data.forEach(flat => commit('addAnnouncementMutation', flat))
        },
    }
})