/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientt.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class ContextListener implements ServletContextListener {

    private List<String> loadAuthenticationListFromFile(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        List<String> authList = new ArrayList<>();
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                authList.add(line);
            }
            fr.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return authList;
    }

    private Map<String, String> loadSiteMapFromFile(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        Map<String, String> siteMap = new HashMap<>();
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                int index = line.indexOf("=");
                String key = line.substring(0, index);
                String value = line.substring(index + 1);
                siteMap.put(key, value);
            }
            fr.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return siteMap;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contex = sce.getServletContext();
        
        String path = sce.getServletContext().getRealPath("/WEB-INF/siteMap.txt");
        Map<String, String> siteMap = loadSiteMapFromFile(path);
        contex.setAttribute("SITE_MAP", siteMap);
        
        path = sce.getServletContext().getRealPath("/WEB-INF/authenticationList.txt");
        List<String> authList = loadAuthenticationListFromFile(path);
        contex.setAttribute("AUTH_LIST", authList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
