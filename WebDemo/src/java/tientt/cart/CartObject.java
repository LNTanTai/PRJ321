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
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String ID) {
        //1. Check existed of items
        if (items == null) {
            items = new HashMap<>();
        }//end if items is not existed
        //2. Check existed item
        int quantity = 1;
        if (this.items.containsKey(ID)) {
            quantity = this.items.get(ID) + 1;
        }
        this.items.put(ID, quantity);
    }

    public void removeItemToCart(String ID) {
        //1. Check existed of items
        if (items == null) {
            return;
        }
        //2. Check existed product
        if (items.containsKey(ID)) {
            this.items.remove(ID);
            if (items.isEmpty()){
                items = null;
            }
        }
    }
}
