function getIndex(list, id){
    for (var i=0; i< list.length; i++){
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var messageApi = Vue.resource('/api/v1/message{/id}');

Vue.component('message-row', {
    props: ['mr', 'editMethod'],
    template: '<div>' +
        '{{mr.id}}  -  {{mr.text}} ' +
        '<span>' +
        '<input type="button" value="Редактировать" @click="edit"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function (){
            this.editMethod(this.mr);
        }
    }
});

Vue.component('message-form', {
    props:['msgs', 'mUpd'],

        data: function () {
            return {
                textInput: '',
                id: ''
            }
        },

        watch: {
        mUpd: function(newVal, oldVal){
            this.textInput = newVal.text;
            this.id = newVal.id;
        }
        },

        template:
            '<div>' +
                '<input type="text" placeholder="Сообщение" v-model="textInput"/>' +
                '<input type="button" value="Сохранить" @click="save"/>' +
            '</div>',
        methods: {
            save: function () {

                var message22 = { text: this.textInput };

                if (this.id) {
                    messageApi.update({id: this.id}, message22).then(result =>
                    result.json().then(data =>
                    {
                        var index = getIndex(this.msgs, data.id,)
                        this.msqs.splice(index, 1, data);
                        this.textInput = '';
                        this.id = '';
                    }))
                } else {
                    messageApi.save({}, message22).then(res =>
                        res.json().then(d => {
                            this.msgs.push(d);
                            this.textInput = '';
                        })
                    )
                }




            }
        }
    }
);

Vue.component('messages-list1', {
    props: ['mess'],

    data: function (){
      return   {
          messageEdit: null
      }
    },

    template: '<div>' +
        '<message-form :msgs="mess" :mUpd="messageEdit"></message-form>' +
        '<message-row v-for="m in mess" :key="m.id" :mr="m" :editMethod="editMessage"></message-row>' +
        '</div>',

    created: function () {
        messageApi.get().then(result =>
            result.json().then(data =>
                data.forEach(messageFromDB => this.mess.push(messageFromDB)
                )
            )
        )
    },
    methods: {
        editMessage: function (mEdit) {
            this.messageEdit = mEdit;
        }
    }
});


var app = new Vue({
    el: '#app',
    template: '<messages-list1 :mess="messages"/>',
    data: {
        messages: []
    }
});