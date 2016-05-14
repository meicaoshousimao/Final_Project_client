package com.Pineapple.client.Treads;

import java.beans.PropertyVetoException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;

import com.Pineapple.Dao.model.Component;
import com.Pineapple.Dao.model.Computer;
import com.Pineapple.Dao.model.Order;
import com.Pineapple.Dao.model.Order_detial;
import com.Pineapple.client.MainFrame;
import com.Pineapple.client.iframe.PayandDelivery;

public class PayThread implements Runnable  
{  
	private Socket socket;  
    private String accept = "NULL";  
    private String username,passwd;  
    private DataInputStream in = null;  
    private DataOutputStream out = null;  
    private ObjectInputStream inBean = null;
    private ObjectOutputStream outBean = null;
    private Boolean check = false;
    
    private JDesktopPane desktopPane;
	private JLabel stateLabel;
	private PayandDelivery payframe;
	private Order order;
	private Map<String,Integer> computermap;
	private Map<String,Integer> componentmap;
	List<Order_detial> orderdetiallist;
	
    public PayThread(Order order,List<Order_detial> orderdetiallist, Map<String,Integer> computermap,Map<String,Integer> componentmap)  
    {  
    	this.order = order;
    	this.computermap = computermap;
    	this.componentmap = componentmap;
        this.orderdetiallist = orderdetiallist;
    }  
    public void run(){
    	payframe = new PayandDelivery(order,orderdetiallist,computermap,componentmap);		    	    	
    	desktopPane = MainFrame.getDesktopPane();
    	desktopPane.add(payframe);			    	    	
    	stateLabel = MainFrame.getStateLabel();
		stateLabel.setText(payframe.getTitle());//把状态栏中的标签设置成当前窗体名字
		try {
			payframe.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}			   
    }
}  
