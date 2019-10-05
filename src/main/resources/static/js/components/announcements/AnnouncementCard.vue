<template>
    <v-container class="mt-5">
        <v-row>
            <v-col>
                <v-card
                        class="mx-auto"
                        outlined
                >
                    <v-list-item three-line>
                        <v-list-item-content class="align-self-start">
                            <v-list-item-title
                                    class="headline mb-1"
                            >
                                <span class="light-blue--text text--darken-3">{{announcement.flat.roomsCount}}-комн.кв., {{announcement.flat.metricArea}}
                                м<sup><small>2</small></sup>,
                                {{announcement.flat.floor}}/{{announcement.flat.totalFloors}} этаж</span>
                            </v-list-item-title>
                            <v-list-item-subtitle class="mb-5">
                                {{announcement.flat.address.district}} район, улица {{announcement.flat.address.street}}
                                ,{{announcement.flat.address.houseNumber}}
                            </v-list-item-subtitle>
                            <p class="title text--primary mb-7">{{announcement.flat.price}} ₽</p>

                            <p class="subtitle-1 mb-3">{{announcement.description}}</p>
                            <p>
                                <v-btn text class="light-blue--text" @click="show = !show">
                                    Дополнительно
                                </v-btn>
                            </p>
                            <v-expand-transition>
                                <div v-show="show">
                                    <v-card-text class="subtitle-1">{{announcement.user.phoneNumber}}</v-card-text>
                                </div>
                            </v-expand-transition>
                        </v-list-item-content>
                    </v-list-item>

                    <v-card-actions v-if="this.profile && (announcement.user.id === this.profile.id)">
                        <v-btn @click="edit" color="light-blue" dark>
                            Редактировать
                        </v-btn>
                        <v-btn @click="del">
                            Удалить
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapActions} from 'vuex'
    import {mapState} from 'vuex'

    export default {
        name: "AnnouncementCard",
        props: ['announcement', 'editAnnouncement'],
        computed: mapState(['profile']),
        data() {
            return {
                show: false
            }
        },
        methods: {
            ...mapActions(['removeAnnouncementAction']),
            edit() {
                this.editAnnouncement(this.announcement);
            },
            del() {
                this.removeAnnouncementAction(this.announcement);
            }
        },
    }
</script>

<style scoped>

</style>