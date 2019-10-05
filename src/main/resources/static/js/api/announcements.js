import Vue from 'vue'

const announcements = Vue.resource('/api/announcement/{id}')

export default {
    get: roomsCount => announcements.get({roomsCount}),
    getByUserId: userId => announcements.get({userId}),
    getById: id => announcements.get({id}),
    add: announcement => announcements.save({}, announcement),
    update: announcement => announcements.update({id: announcement.id}, announcement),
    remove: id => announcements.remove({id}),
}