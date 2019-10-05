<template>
    <v-container>
        <announcement-form :announcement="announcement"/>
    </v-container>
</template>

<script>
    import {mapActions} from 'vuex'
    import {mapState} from 'vuex'
    import AnnouncementForm from "components/announcements/AnnouncementForm.vue"

    export default {
        name: "NewAnnouncementPage",
        components: {
            AnnouncementForm
        },
        computed: mapState(['announcement']),
        methods: mapActions(['getAnnouncementById']),
        created() {
            if (this.$route.query.id) {
                this.getAnnouncementById(this.$route.query.id)
            }
        }
    };
</script>

<style scoped>

</style>