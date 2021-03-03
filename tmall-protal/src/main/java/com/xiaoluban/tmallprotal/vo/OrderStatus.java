package com.xiaoluban.tmallprotal.vo;

/**
 * @author txb
 * @date 2021/3/3 10:24
 */
public enum  OrderStatus {

    //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
    TOPAY(0,"待付款"),TOSEND(1,"待发货"),SENDED(2,"已发货"),
    FINISH(3,"已完成"), CLOSE(4,"已关闭"),EXPIRED(5,"无效订单");

    OrderStatus(int state,String desp){
        this.state=state;
        this.desp=desp;
    }

    private int state;
    private String desp;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
