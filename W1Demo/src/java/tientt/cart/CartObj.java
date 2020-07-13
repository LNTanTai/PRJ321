/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author natton
 */
public class CartObj implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItem(String ID) {
        if (items == null) {
            items = new HashMap<>();
        }
        int quantity = 1;
        if (items.containsKey(ID)) {
            quantity = items.get(ID) + 1;
        }
        items.put(ID, quantity);
    }

    public void deleteItem(String ID) {
        if (items == null) {
            return;
        }
        if (items.containsKey(ID)) {
            items.remove(ID);
            if (items.isEmpty()) {
                items = null;
            }
        }
    }
}
