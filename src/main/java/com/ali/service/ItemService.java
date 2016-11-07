package com.ali.service;

import com.ali.domain.Item;
import com.ali.domain.ItemAddForm;

/**
 * Created by Ali on 24.10.2016.
 */

public interface ItemService {
    void addItem(ItemAddForm form);//domain altında ItemAddForm get set kullanıyoruz

    Iterable<Item> getItems();

    void deleteItemById(long id);//bunları birde ItemServiceImp içine implement edelim

    Item getItemById(long id);

    Item assignItem(String userName, long itemId);
}
