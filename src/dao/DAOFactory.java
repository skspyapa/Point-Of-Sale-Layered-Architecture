package dao;

import dao.custom.ItemDetailDAO;
import dao.custom.impl.CustomerDAOimpl;
import dao.custom.impl.ItemDAOimpl;
import dao.custom.impl.ItemDetailDAOimpl;
import dao.custom.impl.OrdersDAOimpl;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrdersDAO;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
               return (T) new CustomerDAOimpl();
            case ITEM:
                return (T)new ItemDAOimpl();
            case ORDER:
                return (T)new OrdersDAOimpl();
            case ITEM_DETAIL:
                return (T)new ItemDetailDAOimpl();
                default:
                    return null;
        }
    }
}
