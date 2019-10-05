<template>
    <div>
    <v-card ref="form">
        <v-row>
            <v-col cols="2">
                <v-subheader>Количество комнат</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="roomsCount"
                        :error-messages="roomsCountError"
                        @input="$v.roomsCount.$touch()"
                        @blur="$v.roomsCount.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Метраж</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="metricArea"
                        :error-messages="metricAreaError"
                        @input="$v.metricArea.$touch()"
                        @blur="$v.metricArea.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Этаж</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="floor"
                        :error-messages="floorError"
                        @input="$v.floor.$touch()"
                        @blur="$v.floor.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Этажей в доме</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="totalFloors"
                        :error-messages="totalFloorsError"
                        @input="$v.totalFloors.$touch()"
                        @blur="$v.totalFloors.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Район</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="district"
                        :error-messages="districtError"
                        @input="$v.district.$touch()"
                        @blur="$v.district.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Улица</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="street"
                        :error-messages="streetError"
                        @input="$v.street.$touch()"
                        @blur="$v.street.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Номер дома</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        v-model="houseNumber"
                        :error-messages="houseNumberError"
                        @input="$v.houseNumber.$touch()"
                        @blur="$v.houseNumber.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Цена</v-subheader>
            </v-col>
            <v-col cols="1">
                <v-text-field
                        solo
                        suffix="₽"
                        v-model="price"
                        :error-messages="priceError"
                        @input="$v.price.$touch()"
                        @blur="$v.price.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2">
                <v-subheader>Описание</v-subheader>
            </v-col>
            <v-col cols="3">
                <v-textarea
                        clearable
                        clear-icon="clear"
                        auto-grow
                        solo
                        rows="3"
                        row-height="25"
                        v-model="description"
                        :error-messages="descriptionError"
                        @input="$v.description.$touch()"
                        @blur="$v.description.$touch()"
                />
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="3">
                <v-btn @click="save" color="light-blue" dark>
                    Сохранить
                </v-btn>
            </v-col>
        </v-row>
    </v-card>
    <v-snackbar
            v-model="snackbar"
            :timeout="timeout"
    >
        {{ text }}
        <v-btn
                color="blue"
                text
                @click="snackbar = false"
        >
            X
        </v-btn>
    </v-snackbar>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import {validationMixin} from 'vuelidate'
    import {required, numeric} from 'vuelidate/lib/validators'

    export default {
        name: "AnnouncementForm",
        props: ['announcement'],
        mixins: [validationMixin],
        validations: {
            roomsCount: {required, numeric},
            metricArea: {required, numeric},
            floor: {required, numeric},
            totalFloors: {required, numeric},
            district: {required},
            street: {required},
            houseNumber: {required, numeric},
            price: {required, numeric},
            description: {required},
        },
        data() {
            return {
                roomsCount: '',
                metricArea: '',
                floor: '',
                totalFloors: '',
                district: '',
                street: '',
                houseNumber: '',
                price: '',
                description: '',
                id: '',
                snackbar: false,
                text: '',
                timeout: 2000
            }
        },
        watch: {
            announcement(newVal, oldVal) {
                console.log(newVal)
                this.roomsCount = newVal.flat.roomsCount;
                this.metricArea = newVal.flat.metricArea;
                this.floor = newVal.flat.floor;
                this.totalFloors = newVal.flat.totalFloors;
                this.district = newVal.flat.address.district;
                this.street = newVal.flat.address.street;
                this.houseNumber = newVal.flat.address.houseNumber;
                this.price = newVal.flat.price;
                this.description = newVal.description;
                this.id = newVal.id;
            }
        },
        methods: {
            ...mapActions(['addAnnouncementAction', 'updateAnnouncementAction', 'getAnnouncementById']),
            save() {
                this.$v.$touch()
                if (this.$v.$invalid) {

                } else {
                    const announcement = {
                        id: this.id,
                        flat: {
                            roomsCount: this.roomsCount,
                            metricArea: this.metricArea,
                            floor: this.floor,
                            totalFloors: this.totalFloors,
                            address: {
                                district: this.district,
                                street: this.street,
                                houseNumber: this.houseNumber
                            },
                            price: this.price,
                        },
                        description: this.description
                    };

                    if (this.id) {
                        this.updateAnnouncementAction(announcement)
                        this.text = 'Объявление обновлено!'
                        this.snackbar = true
                        setTimeout(() => this.$router.go(-1), 2000)
                    } else {
                        this.addAnnouncementAction(announcement)
                        this.text = 'Объявление подано!'
                        this.snackbar = true
                        setTimeout(() => this.$router.go(-1), 2000)
                    }

                    this.roomsCount = '';
                    this.metricArea = '';
                    this.floor = '';
                    this.totalFloors = '';
                    this.district = '';
                    this.street = '';
                    this.houseNumber = '';
                    this.price = '';
                    this.description = '';
                    this.id = '';
                    this.$v.$reset()
                }
            },
        },
        computed: {
            roomsCountError() {
                const errors = []
                if (!this.$v.roomsCount.$dirty) return errors
                !this.$v.roomsCount.required && errors.push('Введите количество комнат')
                !this.$v.roomsCount.numeric && errors.push('Только цифровое значение!')
                return errors
            },
            metricAreaError() {
                const errors = []
                if (!this.$v.metricArea.$dirty) return errors
                !this.$v.metricArea.required && errors.push('Введите метраж')
                !this.$v.metricArea.numeric && errors.push('Только цифровое значение!')
                return errors
            },
            floorError() {
                const errors = []
                if (!this.$v.floor.$dirty) return errors
                !this.$v.floor.required && errors.push('Введите этаж')
                !this.$v.floor.numeric && errors.push('Только цифровое значение!')
                return errors
            },
            totalFloorsError() {
                const errors = []
                if (!this.$v.totalFloors.$dirty) return errors
                !this.$v.totalFloors.required && errors.push('Введите кол-во этажей')
                !this.$v.totalFloors.numeric && errors.push('Только цифровое значение!')
                return errors
            },
            districtError() {
                const errors = []
                if (!this.$v.district.$dirty) return errors
                !this.$v.district.required && errors.push('Введите район')
                return errors
            },
            streetError() {
                const errors = []
                if (!this.$v.street.$dirty) return errors
                !this.$v.street.required && errors.push('Введите улицу')
                return errors
            },
            houseNumberError() {
                const errors = []
                if (!this.$v.houseNumber.$dirty) return errors
                !this.$v.houseNumber.required && errors.push('Введите номер дома')
                !this.$v.houseNumber.numeric && errors.push('Только цифровое значение!')
                return errors
            },
            priceError() {
                const errors = []
                if (!this.$v.price.$dirty) return errors
                !this.$v.price.required && errors.push('Введите цену')
                !this.$v.price.numeric && errors.push('Только цифровое значение!')
                return errors
            },
            descriptionError() {
                const errors = []
                if (!this.$v.description.$dirty) return errors
                !this.$v.description.required && errors.push('Введите описание')
                return errors
            },
        }
    }
</script>

<style scoped>

</style>