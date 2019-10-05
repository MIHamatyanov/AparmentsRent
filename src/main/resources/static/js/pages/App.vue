<template>
    <v-app>
        <v-app-bar app color="white">
            <v-toolbar-title>Сервис поиска квартир</v-toolbar-title>

            <v-divider
                    class="mx-4"
                    vertical
            ></v-divider>

            <v-btn
                    text
                    href="/"
            >
                Главная
            </v-btn>

            <div class="flex-grow-1"></div>

            <sign-in-form v-if="!profile"></sign-in-form>
            <div v-else>

                <v-btn
                        text
                        :disabled="$route.path ==='/addNew'"
                        @click="addNew"
                >
                    Добавить объявление
                </v-btn>

                <v-btn
                        text
                        :disabled="$route.path ==='/profile'"
                        @click="showProfile"
                >
                    {{profile.name}}
                </v-btn>

                <v-btn color="light-blue" dark icon href="/logout">
                    <v-icon>exit_to_app</v-icon>
                </v-btn>
            </div>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState} from 'vuex'
    import SignInForm from "components/user/SignInForm.vue"

    export default {
        name: "App",
        components: {
            SignInForm,
        },
        computed: mapState(['profile']),
        methods: {
            showProfile() {
                this.$router.push('/profile')
            },
            addNew() {
                this.$router.push('/addNew')
            }
        },
        beforeMount() {
            if (this.$route.path === '/login') {
                this.$router.replace('/?needLogin=1')
            }
        }
    }
</script>

<style scoped>
</style>