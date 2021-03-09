
let app = new Vue({
    el:"#box",
    data:{
        title:"商品详情",
        product:{},
        count:1,

    },

    methods: {
        findProduct(productId){
            axios.get(protal_url+'product/findProduct/'+productId)
            .then(function (response) {
              console.log(response);
              //this.product=response.data.data;
              app.$data.product=response.data.data;
            })
            .catch(function (error) {
              console.log(error);
            });
        },
        createOrder(productId){
            console.log(productId);
            
        },
        addProductToCart(productId){
            alert("暂未开发");
            console.log(productId);
        },
        toMyCart(){
            alert("暂未开发");
            console.log("toMyCart");
        },

        add:function(){
            if(this.count>=10){
                alert('每个人最多买10件');
            }else{
                this.count++;
            }
         },
        subtract:function(){
            if (this.count<=1){
                alert('受不了啦，宝贝不能再减少啦')
            }else {
                this.count--;
            }
        },
        payNow(){
            this.handleOrder(1);
        },
        createOrder(){
            this.handleOrder(0);
            
        },
        handleOrder(orderState){
            this.product.quantity=this.count;
            let orderExtendVO={
                order:{
                    // 0-> 待付款；1-> 待发货；2-> 已发货；3-> 已完成；4-> 已关闭；5-> 无效订单
                    status: orderState,
                },
                products:[
                    this.product,
                ],
                user:user={
                    id:1,
                },
            };
            axios.post(protal_url+'order/createOrder', orderExtendVO
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

        let productId=[[${productId}]];
        console.log(productId);
    },
    computed: {
        computeSumPrice: function () {
            return this.product.price*this.count;
        }
    },

})