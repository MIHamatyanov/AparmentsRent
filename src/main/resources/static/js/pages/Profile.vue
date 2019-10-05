<template>
    <v-container>
        <v-row>
            <v-col cols="2">
                <v-navigation-drawer
                        permanent
                        absolute
                >
                    <template v-slot:prepend>
                        <v-list>
                            <v-list-item>
                                <v-avatar color="red">
                                    <span class="white--text headline">{{profile.name[0]}}</span>
                                </v-avatar>
                            </v-list-item>

                            <v-list-item
                                    link
                                    two-line
                            >
                                <v-list-item-content>
                                    <v-list-item-title class="title">{{profile.name}}</v-list-item-title>
                                    <v-list-item-subtitle>{{profile.email}}</v-list-item-subtitle>
                                    <v-list-item-subtitle>{{profile.phoneNumber}}</v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </template>

                    <v-divider></v-divider>

                    <v-list
                            nav
                            dense
                    >
                        <v-list-item link @click="content='account'">
                            <v-list-item-icon>
                                <v-icon>person</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title>Аккаунт</v-list-item-title>
                        </v-list-item>
                        <v-list-item link @click="content='announcements'">
                            <v-list-item-icon>
                                <v-icon>web_asset</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title>Мои объявления</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-navigation-drawer>
            </v-col>
            <v-col>
                <v-content>
                    <user-form v-if="content === 'account'" :user="profile" />
                    <announcements-list v-if="content === 'announcements'" :announcements="announcements" />
                </v-content>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
    import {mapState} from 'vuex'
    import { mapActions } from 'vuex'
    import UserForm from "components/user/UserForm.vue"
    import AnnouncementsList from "components/announcements/AnnouncementsList.vue"

    export default {
        name: "Profile",
        computed: mapState(['profile', 'announcements']),
        components: {
            UserForm,
            AnnouncementsList
        },
        data() {
            return {
                content: ''
            }
        },
        methods: {
            ...mapActions(['getAnnouncementsByUserId'])
        },
        created() {
            this.getAnnouncementsByUserId(this.profile.id)
        }
    }
</script>

<style scoped>

</style>