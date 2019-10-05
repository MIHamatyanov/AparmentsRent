<template>
    <div>
        <announcement-card v-for="announcement in announcements"
                           :announcement="announcement"
                           :key="announcement.id"
                           :editAnnouncement="editAnnouncement"/>
    </div>
</template>

<script>
    import AnnouncementCard from "components/announcements/AnnouncementCard.vue"

    export default {
        name: "AnnouncementsList",
        props: ['announcements'],
        components: {
            AnnouncementCard,
        },
        methods: {
            editAnnouncement(announcement) {
                this.$router.push({path: '/addNew', query: {id: announcement.id}})
            }
        },
    }
</script>

<style scoped>

</style>