/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.item;

/**
 *
 * @author natton
 */
public class ItemDTO {
    private String ID;
    private String name;
    private double price;

    public ItemDTO() {
    }

    public ItemDTO(String ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
