package com.ali.repository;

import com.ali.domain.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ali on 24.10.2016.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {

}
