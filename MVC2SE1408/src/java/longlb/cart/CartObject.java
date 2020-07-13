/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.cart;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Long Le
 */
public class CartObject implements Serializable{
    private String cartId;
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
	return items;
    }
    
    public void addItemToCart(String title){
	//1. Check existed of items
	if (this.items == null){
	    this.items = new HashMap<>();
	} //end if items not existed
	
	//2.Check existed of produts
	int quantity = 1;
	if (this.items.containsKey(title)){
	    quantity = this.items.get(title) + 1;
	} //end if cart hase title
	
	this.items.put(title, quantity);
    }
    
    public void removeItemFromCart(String title){
	//1. Check existed item
	if (this.items == null){
	    return;
	}
	
	//2.Check existed product
	if (this.items.containsKey(title)){
	    this.items.remove(title);
	    
	    //remove cart if cart is empty
	    if (this.items.isEmpty()){
		this.items = null;
	    }
	}
    }
    
    public void generateID(){
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-HHmmss");
	this.cartId = sdf.format(date).toString();
    }

    /**
     * @return the cartId
     */
    public String getCartId() {
	return cartId;
    }
}
