import Vue from 'vue'

const users = Vue.resource('/api/user/{id}')

export default {
    save: user => users.save({}, user),
    update: user => users.update({id: user.id}, user),
}