package com.ali.domain;

/**
 * Created by Ali on 24.10.2016.
 */

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity//Item modelimiz aynı zamanda oluşturacağımız database için bir Entity olacak.
public class Item {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    @Column(name = "code", nullable = false, updatable = false, unique = true)
    private String inventoryCode;//her item için farklı olacak bir stok kodu

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "type", nullable = false)
    private String type;//bilgisayar, telefon, yatak vs.

    public Item() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item(String inventoryCode, String type) {
        this.inventoryCode = inventoryCode;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", inventoryCode='" + inventoryCode + '\'' + ", type='" + type + '\'' + '}';
    }

    @ManyToOne
    @JoinColumn(name = "user_id")//itemin bir User'ı var
    private User user;

}
