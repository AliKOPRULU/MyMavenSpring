package com.ali.service;

import com.ali.domain.Item;
import com.ali.domain.ItemAddForm;
import com.ali.domain.User;
import com.ali.repository.ItemRepository;
import com.ali.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Ali on 24.10.2016.
 */
@Service
public class ItemServiceImpl implements ItemService {//domain.ItemAddForm
    //ItemRepository de CRUD işlemlerini implemen ettik
    private final ItemRepository itemRepository;//Final olan bir sınıf değişkenine sadece bir kere değer ataması yapilabilir ve bu atama sadece sınıf konstrüktöründe gerçekleşebilir.
    private final UserService userService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public void addItem(ItemAddForm form) {//ürün tipi ve adeti geliyor //Bu bilgilere göre Item objesini oluşturup, save methoduyla db’ye ekliyoruz.
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10);//generate random string
            Item item = new Item(inventoryCode, form.getItemType());
            itemRepository.save(item);
            System.out.println(itemRepository.findOne(item.getId()));
        }
    }

    @Override
    public Iterable<Item> getItems() {//Bunu yapmamızın sebebi Controller’da hem Service hem Repository kullanmak yerine, sadece Service’i kullanmak istememiz.
        return itemRepository.findAll();//CrudRepository‘den gelen methodları Service içinde kullanıyoruz.
    }

    public void deleteItemById(long id) {
        itemRepository.delete(id);
    }

    public Item getItemById(long id) {
        return itemRepository.findOne(id);//id verip Repodan DOA ile item çekiyoruz.
    }

    public Item assignItem(String userName, long itemId) {//ItemAssignForm‘dan gelecek olan kullanıcı adına sahip userın item listesine, itemId‘ye sahip Item‘ı ekliyoruz.
        User user = userService.getUserByUserName(userName);
        Item item = getItemById(itemId);

        Set<Item> itemList = user.getItems();
        itemList.add(item);
        user.setItems(itemList);

        item.setUser(user);
        return itemRepository.save(item);//item’ın yeni halini database’e eklemek (Esasında buna bir update işlemi demek daha doğru olur).
    }


}
