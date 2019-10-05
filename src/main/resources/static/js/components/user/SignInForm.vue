<template>
    <v-dialog v-model="dialog" max-width="450px" @click:outside="click">
        <template v-slot:activator="{ on }">
            <v-btn class="ma-2" color="light-blue" dark small v-on="on">
                <v-icon dark left>person</v-icon>
                Вход и регистрация
            </v-btn>
        </template>
        <v-card>
            <v-tabs v-model="tab" background-color="transparent">
                <v-tab>Вход</v-tab>
                <v-tab>Регистрация</v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
                <v-tab-item>
                    <v-card flat>
                        <v-card-text>
                            <form action="/login" method="post">
                                <v-col cols="12">
                                    <v-text-field
                                            name="username"
                                            label="Email"
                                            required
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field
                                            name="password"
                                            label="Password"
                                            type="password"
                                            required
                                    ></v-text-field>
                                </v-col>
                                <div class="text-center">
                                    <v-btn color="light-blue" dark type="submit">Войти</v-btn>
                                </div>
                            </form>
                        </v-card-text>
                    </v-card>
                </v-tab-item>
                <v-tab-item>
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
                                    <v-text-field
                                            label="Password*"
                                            type="password"
                                            required
                                            v-model="password"
                                            :error-messages="passwordErrors"
                                            @input="$v.password.$touch()"
                                            @blur="$v.password.$touch()"
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
                                    <v-btn color="light-blue" dark @click="register">Зарегистрироваться</v-btn>
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
                </v-tab-item>
            </v-tabs-items>
        </v-card>
    </v-dialog>
</template>

<script>
    import {validationMixin} from 'vuelidate'
    import {required, email, numeric} from 'vuelidate/lib/validators'
    import usersApi from "api/user"

    export default {
        name: "SignInForm",
        mixins: [validationMixin],
        validations: {
            name: {required},
            email: {required, email},
            phoneNumber: {required, numeric},
            password: {required}
        },
        data() {
            return {
                tab: null,
                dialog: false,
                alert: false,
                name: '',
                email: '',
                password: '',
                phoneNumber: '',
                alertMessage: '',
                alertColor: '',
            }
        },
        methods: {
            register() {
                this.$v.$touch()
                if (this.$v.$invalid) {

                } else {
                    const user = {
                        email: this.email,
                        password: this.password,
                        name: this.name,
                        phoneNumber: this.phoneNumber,
                    };

                    usersApi.save(user).then(result => {
                        if (result.data === false) {
                            this.alertMessage = 'Пользователь с таким Email уже существует!'
                            this.alertColor = 'red'
                        } else {
                            this.alertMessage = 'Регистрация прошла успешно.'
                            this.alertColor = 'green'
                        }
                    })

                    this.alert = true;
                }
            },
            click() {
                this.$v.$reset()
                this.alert = false
                this.dialog = false
                this.name = ''
                this.email = ''
                this.password = ''
                this.phoneNumber = ''
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
            passwordErrors() {
                const errors = []
                if (!this.$v.password.$dirty) return errors
                !this.$v.password.required && errors.push('Введите пароль')
                return errors
            }
        },
        mounted() {
            if (this.$route.query.needLogin === '1') {
                this.dialog = true
            }
        }
    }
</script>

<style scoped>

</style>