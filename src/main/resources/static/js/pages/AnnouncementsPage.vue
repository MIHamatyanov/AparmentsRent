<template>
    <v-container>
        <announcements-list :announcements="announcements" />
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'
    import AnnouncementsList from "components/announcements/AnnouncementsList.vue"

    export default {
        name: "AnnouncementsPage",
        components: {
            AnnouncementsList,
        },
        computed: mapState(['announcements']),
        methods: {
            ...mapActions(['getAnnouncementsByRoomsCount']),
        },
        created() {
            this.getAnnouncementsByRoomsCount(this.$route.query.roomsCount)
        }
    }
</script>

<style scoped>

</style>