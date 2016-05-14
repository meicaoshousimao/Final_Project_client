package com.Pineapple.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.Pineapple.client.iframe.Checkactiveorder;
import com.Pineapple.client.iframe.Checkcomponent;
import com.Pineapple.client.iframe.Checkcomputer;
import com.Pineapple.client.iframe.Checkdeadorder;
import com.Pineapple.client.iframe.Checkshoppingbag;

public class MenuBar extends JMenuBar{
	/**
	 * （商城）菜单
	 */
	private JMenu computershop_Menu = null;

	/**
	 * （电脑商城）菜单项，位于（商城）菜单内
	 */
	private JMenuItem computershopItem = null;
	/**
	 * （配件商城）菜单项，位于（商城）菜单内
	 */
	private JMenuItem componentshopItem = null;
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
	 * （当前订单）菜单项，位于（我的订单）菜单内
	 */
	private JMenuItem activeorderItem = null;
	/**
	 * （历史订单）菜单项，位于（我的订单）菜单内
	 */
	private JMenuItem deadorderItem = null;
	/**
	 * （个人信息）菜单
	 */
	private JMenu myinformation_Menu = null;

	/**
	 * （个人信息）菜单项，位于（个人信息）菜单内
	 */
	private JMenuItem myinformationItem = null;
	/**
	 * （更改密码信息）菜单项，位于（个人信息）菜单内
	 */
	private JMenuItem changepasswordItem = null;
	/**
	 * （更改邮箱信息）菜单项，位于（个人信息）菜单内
	 */
	private JMenuItem changeemailItem = null;
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
	private Socket socketClient;
	////////////////////////////////////////////////////
	/**
	 * 默认的构造方法
	 * 
	 */
	private MenuBar() {
	}
	//初始化菜单栏界面的方法
	public MenuBar(JDesktopPane desktopPanel, JLabel label,Socket socketClient) {
		super();
		this.socketClient = socketClient;
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
			computershop_Menu.add(getcomponentshopItem());
			
		}
		return computershop_Menu;
	}

	/**
	 * 初始化（电脑商城）菜单项的方法 该方法定义菜单项打开待商城窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcomputershopItem() {
		if (computershopItem == null) {
			computershopItem = new JMenuItem();
			computershopItem.setText("电脑商城");
			//computershopItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			computershopItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(computershopItem, Checkcomputer.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return computershopItem;
	}
	/**
	 * 初始化（配件商城）菜单项的方法 该方法定义菜单项打开待商城窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getcomponentshopItem() {
		if (componentshopItem == null) {
			componentshopItem = new JMenuItem();
			componentshopItem.setText("配件商城");
			//componentrshopItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			componentshopItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(componentshopItem, Checkcomponent.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return componentshopItem;
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
					createIFrame(shoppingbagItem, Checkshoppingbag.class);//鼠标监听，打开内部窗口
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
			myorder_Menu.add(getactiveorderItem());
			myorder_Menu.add(getdeadorderItem());
			
		}
		return myorder_Menu;
	}

	/**
	 * 初始化（当前订单）菜单项的方法 该方法定义菜单项打开我的订单窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getactiveorderItem() {
		if (activeorderItem == null) {
			activeorderItem = new JMenuItem();
			activeorderItem.setText("当前订单");
			//activeorderItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			activeorderItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(activeorderItem, Checkactiveorder.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return activeorderItem;
	}
	/**
	 * 初始化（历史订单）菜单项的方法 该方法定义菜单项打开我的订单窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getdeadorderItem() {
		if (deadorderItem == null) {
			deadorderItem = new JMenuItem();
			deadorderItem.setText("历史订单");
			//deadorderItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			deadorderItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(deadorderItem, Checkdeadorder.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return deadorderItem;
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
			myinformation_Menu.add(getchangepasswordItem());
			myinformation_Menu.add(getchangeemailItem());
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
	/**
	 * 初始化（更改密码）菜单项的方法 该方法定义菜单项打开个人信息窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getchangepasswordItem() {
		if (changepasswordItem == null) {
			changepasswordItem = new JMenuItem();
			changepasswordItem.setText("更改密码");
			//changepasswordItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			changepasswordItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(changepasswordItem, changepassword.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return changepasswordItem;
	}
	/**
	 * 初始化（更改邮箱）菜单项的方法 该方法定义菜单项打开个人信息窗口,并使窗口处于被选择状态
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getchangeemailItem() {
		if (changeemailItem == null) {
			changeemailItem = new JMenuItem();
			changeemailItem.setText("更改邮箱");
			//changeemailItem.setIcon(new ImageIcon(getClass().getResource(
			//		"/res/icon/ordertocheck.png")));
			changeemailItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(changeemailItem, changeemail.class);//鼠标监听，打开内部窗口
				}
			});
		}
		return changeemailItem;
	}
///////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * 创建内部窗体的方法，该方法使用发射技术获取内部窗体的构造方法，从而创建内部窗体。
	 * 
	 * @param item：激活该内部窗体的菜单项
	 * @param clazz：内部窗体的Class对象
	 */
	private JInternalFrame createIFrame(JMenuItem item, Class clazz) {
		Constructor constructor = null;
		try {
			constructor = clazz.getConstructor(new Class[]{});
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//获取内部窗体的构造器
		JInternalFrame iFrame = iFrames.get(item);//以菜单项为key，查找对应的内部窗体
		try {
			//如果不存在当前窗体，或者窗体处于关闭状态，就执行以下操作
			if (iFrame == null || iFrame.isClosed()) {
				iFrame = (JInternalFrame) constructor
						.newInstance();//使用内部窗体构造器，构造一个新窗体
			/*	Field field1=clazz.getDeclaredField("socketClient");
				field1.set(iFrame, socketClient);*/
				iFrames.put(item, iFrame);//把新窗体至于内部窗体哈希表中
				iFrame.setFrameIcon(item.getIcon());//内部窗体图标取自菜单项图标
				iFrame.setLocation(nextFrameX, nextFrameY);//设置内部窗体位置
				////////////////////////////////////////////////////////////////////////////////
				int frameH = iFrame.getPreferredSize().height;//获取内部窗体高度
				int panelH = iFrame.getContentPane().getPreferredSize().height;//获取桌面高度
				//设置下一个内部窗口打开位置，避免窗口重叠看不到的现象
				int fSpacing = frameH - panelH;
				nextFrameX += fSpacing;
				nextFrameY += fSpacing;
				if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
					nextFrameX = 0;
				if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
					nextFrameY = 0;
				/////////////////////////////////////////////////////////////////////////////////
				desktopPanel.add(iFrame);//把内部窗口放到桌面上
				iFrame.setResizable(false);//内部窗体大小不可改变
				iFrame.setMaximizable(false);//内部窗体不能最大化
				iFrame.setVisible(true);//使内部窗体可见
			}
			//如果已存在当前窗体，或者执行完创建操作后，就执行以下操作
			iFrame.setSelected(true);//使当前窗体处于被选中状态
			stateLabel.setText(iFrame.getTitle());//把状态栏中的标签设置成当前窗体名字
			//为当前内部窗体添加监听
			iFrame.addInternalFrameListener(new InternalFrameAdapter() {
				/**
				 * 内部窗体的激活方法，获取内部窗口，并将状态栏标签设置为内部窗口名称
				 */
				public void internalFrameActivated(InternalFrameEvent e) {
					super.internalFrameActivated(e);
					JInternalFrame frame = e.getInternalFrame();
					stateLabel.setText(frame.getTitle());
				}
				/**
				 * 内部窗体的最小化方法，状态栏标签更改为“未选择窗口”
				 */
				public void internalFrameDeactivated(InternalFrameEvent e) {
					stateLabel.setText("No window choosed");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iFrame;
	}


}
