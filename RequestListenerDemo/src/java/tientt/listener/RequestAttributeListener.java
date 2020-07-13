/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class RequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        String name = (String) srae.getName();
        String value = srae.getValue().toString();
        System.out.println("An attribute has been add: " + name + "-" + value);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
    }
}
