let app = new Vue({
    el: "#box",
    data: {
        orderList:[],
        userId:1,
    },

    methods: {
        getOrderList(userId){
            axios.get(protal_url + 'ums/getOrderList/' + userId)
                .then(function (response) {
                    console.log(response);
                    app.$data.orderList = response.data.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        orderPay(order){
            //console.log(order);
             axios.post(protal_url+'order/pay', order
            )
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    },
    mounted() {
       this.getOrderList(this.userId);
    },
    computed: {
        
    },

})