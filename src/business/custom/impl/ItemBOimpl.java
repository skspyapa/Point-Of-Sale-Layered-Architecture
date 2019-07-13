package business.custom.impl;

import business.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOimpl implements ItemBO {
    private ItemDAO itemDAO =  DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    public List<ItemDTO> getAll() throws SQLException {

        List<Item> allItem = itemDAO.findAll();
        List<ItemDTO> dtos = new ArrayList<>();
        for (Item item:allItem) {
            ItemDTO itemDTO = new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
            dtos.add(itemDTO);
        }
        return dtos;
    }
    public boolean save(ItemDTO dto) throws SQLException {
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return itemDAO.save(item);
    }

    public boolean remove(String  code) throws SQLException {
        return itemDAO.delete(code);
    }

    public boolean update(ItemDTO dto) throws SQLException {
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return itemDAO.update(item);
    }
    public ItemDTO get(String code) throws SQLException {
        Item item = itemDAO.find(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    public String getMaxItemCode() throws SQLException {
        return itemDAO.findMaxItemCode();
    }
}
