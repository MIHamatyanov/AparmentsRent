<template>
    <v-container>
        <v-row align="center" justify="center">
            <v-col lg="2">
                <v-select
                        v-model="selected"
                        :items="counts"
                        :menu-props="{ maxHeight: '300' }"
                        label="Количество комнат"
                        multiple
                        append-icon="arrow_drop_down"
                >
                </v-select>
            </v-col>
            <v-col lg="2">
                <v-btn text @click="search">
                    Найти
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

    export default {
        name: "SearchForm",
        data() {
            return {
                selected: [],
                counts: ['1', '2', '3', '4']
            }
        },
        methods: {
            search() {
                this.$router.push({ path: '/announcements', query: { roomsCount: this.selected.join() } })
            }
        }
    }
</script>

<style scoped>

</style>