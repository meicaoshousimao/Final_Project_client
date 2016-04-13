package com.Pineapple.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	/**
	 * （商城）菜单
	 */
	private JMenu computershop_Menu = null;

	/**
	 * （商城）菜单项，位于（商城）菜单内
	 */
	private JMenuItem computershopItem = null;
	/**
	 * （购物车）菜单
	 */
	private JMenu shoppingbag_Menu = null;

	/**
	 * （购物车）菜单项，位于（购物车）菜单内
	 */
	private JMenuItem shoppingbagItem = null;
	/**
	 * （我的订单）菜单
	 */
	private JMenu myorder_Menu = null;

	/**
	 * （我的订单）菜单项，位于（我的订单）菜单内
	 */
	private JMenuItem myorderItem = null;
	/**
	 * （个人信息）菜单
	 */
	private JMenu myinformation_Menu = null;

	/**
	 * （个人信息）菜单项，位于（个人信息）菜单内
	 */
	private JMenuItem myinformationItem = null;
	///////////////////////////////////////////////////
	/**
	 * 状态栏的内部窗体提示标签
	 */
	private JLabel stateLabel = null;
	/**
	 * 容纳内部窗体的桌面面板
	 */
	private JDesktopPane desktopPanel = null;
	/**
	 * 内部窗体的集合（菜单项，内部窗体）映射哈希表
	 */
	private Map<JMenuItem, JInternalFrame> iFrames = null;
	/**
	 * 内部窗体的位置坐标
	 */
	private int nextFrameX, nextFrameY;
	////////////////////////////////////////////////////
	/**
	 * 默认的构造方法
	 * 
	 */
	private MenuBar() {
	}
	//初始化菜单栏界面的方法
	public MenuBar(JDesktopPane desktopPanel, JLabel label) {
		super();
		iFrames = new HashMap<JMenuItem, JInternalFrame>(); //一个菜单项与内部窗口映射的哈希表
		this.desktopPanel = desktopPanel;
		this.stateLabel = label;
		initialize();
	}

	/**
	 * 初始化菜单栏界面的方法
	 * 为菜单栏加入菜单
	 */
	private void initialize() {
		this.setSize(new Dimension(600, 24));
		add(getcomputershop_Menu());
		add(getshoppingbag_Menu());
		add(getmyorder_Menu());
		add(getmyinformation_Menu());
	}
/////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化商城菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getcomputershop_Menu() {
		if (computershop_Menu == null) {
			computershop_Menu = new JMenu();
			computershop_Menu.setText("商城");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			computershop_Menu.add(getcomputershopItem());
			
		}
		return computershop_Menu;
	}

	/**
	 * 初始化（商城）菜单项的方法 该方法定义菜单项打开待商城窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcomputershopItem() {
		if (computershopItem == null) {
			computershopItem = new JMenuItem();
			computershopItem.setText("商城");
			//computershopItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			computershopItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(computershopItem, Computershop.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return computershopItem;
	}
//////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化购物车菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getshoppingbag_Menu() {
		if (shoppingbag_Menu == null) {
			shoppingbag_Menu = new JMenu();
			shoppingbag_Menu.setText("购物车");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			shoppingbag_Menu.add(getshoppingbagItem());
			
		}
		return shoppingbag_Menu;
	}

	/**
	 * 初始化（购物车）菜单项的方法 该方法定义菜单项打开购物车窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getshoppingbagItem() {
		if (shoppingbagItem == null) {
			shoppingbagItem = new JMenuItem();
			shoppingbagItem.setText("购物车");
			//shoppingbagItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			shoppingbagItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(shoppingbagItem, Shoppingbag.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return shoppingbagItem;
	}
/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化我的订单菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getmyorder_Menu() {
		if (myorder_Menu == null) {
			myorder_Menu = new JMenu();
			myorder_Menu.setText("我的订单");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			myorder_Menu.add(getmyorderItem());
			
		}
		return myorder_Menu;
	}

	/**
	 * 初始化（我的订单）菜单项的方法 该方法定义菜单项打开我的订单窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getmyorderItem() {
		if (myorderItem == null) {
			myorderItem = new JMenuItem();
			myorderItem.setText("我的订单");
			//myorderItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			myorderItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(myorderItem, Myorder.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return myorderItem;
	}
///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化个人信息菜单的方法
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getmyinformation_Menu() {
		if (myinformation_Menu == null) {
			myinformation_Menu = new JMenu();
			myinformation_Menu.setText("信息中心");
			//jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//快捷键
			myinformation_Menu.add(getmyinformationItem());
			
		}
		return myinformation_Menu;
	}

	/**
	 * 初始化（个人信息）菜单项的方法 该方法定义菜单项打开个人信息窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getmyinformationItem() {
		if (myinformationItem == null) {
			myinformationItem = new JMenuItem();
			myinformationItem.setText("个人信息");
			//myinformationItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			myinformationItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(myinformationItem, Myinformation.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return myinformationItem;
	}


}
