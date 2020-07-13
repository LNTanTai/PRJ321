/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.bean;

import java.io.Serializable;
import java.util.Map;
import tientt.tbl_Mobile.Tbl_MobileDTO;

/**
 *
 * @author natton
 */
public class CartBean implements Serializable {

    private Map<Tbl_MobileDTO, Integer> items;

    public Map<Tbl_MobileDTO, Integer> getItems() {
        return items;
    }

    public CartBean() {
    }

    public void setItems(Map<Tbl_MobileDTO, Integer> items) {
        this.items = items;
    }
    public void addItem(String id)
}
