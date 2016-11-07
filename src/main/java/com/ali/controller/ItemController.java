package com.ali.controller;

/**
 * Created by Ali on 24.10.2016.
 */

import com.ali.domain.ItemAddForm;
import com.ali.domain.ItemAssignForm;
import com.ali.service.ItemService;
import com.ali.service.UserService;
import com.sun.javafx.collections.MappingChange;
import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @RequestMapping("/items/add")
    public ModelAndView itemAddPage() {
        return new ModelAndView("addItem", "itemForm", new ItemAddForm());
    }

    public String handleItemAdd(@Valid @ModelAttribute("itemForm") ItemAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "addItem";
        itemService.addItem(form);
        return "redirect:/items";
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
//handleItemDelete methodunda ise itemı silme işlemini kontrol ediyoruz.
    public String handleItemDelete(@PathVariable Long id) {// id değişkenindeki değere sahip itemı siliyoruz.//URL’de kullandığımız bu  değişkenlere PathVariable diyoruz.
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

//    @RequestMapping("/items")//eski hali
//    public ModelAndView getItemsPage() {
//        return new ModelAndView("items", "items", itemService.getItems());// items.html sayfasına, ismi items olan modelimizi gönderiyoruz.
//    }

    @RequestMapping("/items")
    public ModelAndView getItemsPage() {//Birden fazla model alma
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("items", itemService.getItems());
        model.put("userNames", userService.getUserNames());
        model.put("assignForm", new ItemAssignForm());
        return new ModelAndView("items", model);
    }

    public String handleItemAssign(@ModelAttribute("user") ItemAssignForm form, @PathVariable("id") long id) {
        itemService.assignItem(form.getUserName(), id);
        return "redirect:/items";
    }

}
