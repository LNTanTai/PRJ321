/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import tientt.utils.DBHelpers;

/**
 *
 * @author natton
 */
public class CartObj implements Serializable {

    Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItem(String itemID) {
        if (items == null) {
            items = new HashMap<>();
        }
        int quantity = 1;
        if (this.items.containsKey(itemID)) {
            quantity = this.items.get(itemID) + 1;
        }
        this.items.put(itemID, quantity);
    }

    public void removeItem(String ID) {
        if (this.items.containsKey(ID)) {
            this.items.remove(ID);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }


}
