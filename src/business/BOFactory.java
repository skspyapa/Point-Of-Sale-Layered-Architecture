package business;

import business.custom.impl.CustomerBOimpl;
import business.custom.impl.ItemBOimpl;
import business.custom.impl.OrdersBOimpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getInstance(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public <T extends SuperBO>T getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (T) new CustomerBOimpl();
            case ITEM:
                return (T) new ItemBOimpl();
            case ORDERS:
                return (T) new OrdersBOimpl();
                default:
                    return null;
        }
    }
}
