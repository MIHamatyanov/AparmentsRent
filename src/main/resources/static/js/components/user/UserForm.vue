<template>
    <v-card flat>
        <v-card-text>
            <v-container>
                <v-col cols="12">
                    <v-text-field
                            label="Имя*"
                            required
                            v-model="name"
                            :error-messages="nameErrors"
                            @input="$v.name.$touch()"
                            @blur="$v.name.$touch()"
                    ></v-text-field>
                </v-col>
                <v-col cols="12">
                    <v-text-field
                            label="Email*"
                            required
                            v-model="email"
                            :error-messages="emailErrors"
                            @input="$v.email.$touch()"
                            @blur="$v.email.$touch()"
                    ></v-text-field>
                </v-col>
                <v-col cols="12">
                    <v-text-field label="Номер телефона*"
                                  required
                                  v-model="phoneNumber"
                                  :error-messages="phoneErrors"
                                  @input="$v.phoneNumber.$touch()"
                                  @blur="$v.phoneNumber.$touch()"
                    ></v-text-field>
                </v-col>
                <div class="text-center">
                    <v-btn color="light-blue" dark @click="update">Обновить</v-btn>
                    <v-alert
                            class="mt-5"
                            :value="alert"
                            :color="alertColor"
                            dark
                            border="top"
                            transition="scale-transition"
                    >
                        {{alertMessage}}
                    </v-alert>
                </div>
            </v-container>
        </v-card-text>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex'
    import {validationMixin} from 'vuelidate'
    import {required, email, numeric} from 'vuelidate/lib/validators'
    import usersApi from "api/user"

    export default {
        name: "UserForm",
        mixins: [validationMixin],
        validations: {
            name: {required},
            email: {required, email},
            phoneNumber: {required, numeric},
        },
        props: ['user'],
        data() {
            return {
                alert: false,
                id: this.user.id,
                name: this.user.name,
                email: this.user.email,
                phoneNumber: this.user.phoneNumber,
                alertMessage: '',
                alertColor: '',
            }
        },
        methods: {
            ...mapActions(['updateUserInfo']),
            update() {
                this.$v.$touch()
                if (this.$v.$invalid) {

                } else {
                    const user = {
                        id: this.id,
                        email: this.email,
                        name: this.name,
                        phoneNumber: this.phoneNumber,
                    };


                    this.updateUserInfo(user).then(result => {
                        if (result === false) {
                            this.alertMessage = 'Пользователь с таким Email уже существует!'
                            this.alertColor = 'red'
                        } else {
                            this.alertMessage = 'Данные обновленны.'
                            this.alertColor = 'green'
                        }
                    })

                    this.alert = true;
                }
            },
            click() {
                this.$v.$reset()
                this.alert = false
                this.name = this.user.name,
                this.email = this.user.email,
                this.phoneNumber = this.user.phoneNumber
            }
        },
        computed: {
            nameErrors() {
                const errors = []
                if (!this.$v.name.$dirty) return errors
                !this.$v.name.required && errors.push('Введите имя')
                return errors
            },
            emailErrors() {
                const errors = []
                if (!this.$v.email.$dirty) return errors
                !this.$v.email.email && errors.push('Неверный Email')
                !this.$v.email.required && errors.push('Введите Email')
                return errors
            },
            phoneErrors() {
                const errors = []
                if (!this.$v.phoneNumber.$dirty) return errors
                !this.$v.phoneNumber.required && errors.push('Введите номер телефона')
                !this.$v.phoneNumber.numeric && errors.push('Номер телефона должен соержать только цифры')
                return errors
            },
        },
    }
</script>

<style scoped>

</style>