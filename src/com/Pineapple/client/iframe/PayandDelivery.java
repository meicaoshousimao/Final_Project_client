package com.Pineapple.client.iframe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Pineapple.Dao.model.Computer;
import com.Pineapple.Dao.model.Order;
import com.Pineapple.client.MainFrame;
import com.Pineapple.client.login.LoginDialog;

public class PayandDelivery extends JInternalFrame{
	private JPanel contentPane;
	private JButton confirmButton;
	private JCheckBox creditcard;
	private JCheckBox cash;
	private JCheckBox banktransfer;
	private final static ButtonGroup buttonGroup = new ButtonGroup();
	private static JTextField textFieldaddress;
	
	private ObjectOutputStream outBean = null;
	private DataInputStream in = null;
	private String accept;
	public PayandDelivery() {
		super();//先构造一个内部窗口
		setTitle("Payment&Delivery");
		setIconifiable(true);//开启内部窗口最小化功能
		setClosable(true);//开启内部窗口关闭功能
		setVisible(true);
		getContentPane().setLayout(new GridBagLayout());//窗口内部布局设置
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		//设置尺寸和布局，并将它显示出来
				contentPane.setBounds(100, 100, 400, 300);
				contentPane.setLayout(new GridBagLayout());
				contentPane.setVisible(true);
				
				//所有的标签都设成final
						final JLabel Paymentmethod = new JLabel();
						Paymentmethod.setText("Payment method:");
						setupComponent(Paymentmethod, 0, 0, 1, 20, false);
						creditcard = new JCheckBox("Credit Card    ");
						buttonGroup.add(creditcard);
						setupComponent(creditcard, 1, 0, 1, 10, false);
						cash = new JCheckBox("Cash              ");
						buttonGroup.add(cash);
						setupComponent(cash, 1, 1, 1, 10, false);
						banktransfer = new JCheckBox("Bank Transfer");
						buttonGroup.add(banktransfer);
						setupComponent(banktransfer, 1, 2, 1, 10, false);
						
						
						final JLabel Deliveryaddress = new JLabel();
						Deliveryaddress.setText("Delivery address:");
						setupComponent(Deliveryaddress,0,3,1,20,false);
						textFieldaddress = new JTextField();
						setupComponent(textFieldaddress, 1, 3, 3, 250, true);
		
						confirmButton = new JButton();
						confirmButton.setText("Confirm");
						confirmButton.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								Socket socketclient = MainFrame.getSocketClient();
								Order order = Checkcomputer.getOrder();
								order.setPayment(getPaymentmethod());
								order.setDelivery(getAddress());
								try {
									outBean = new ObjectOutputStream(socketclient.getOutputStream());
									outBean.writeObject(order);
									outBean.flush();
									in = new DataInputStream(socketclient.getInputStream());
									accept = in.readUTF();
									if(accept.equals("True")){
										JOptionPane.showMessageDialog(null,
												"订单已提交.", "交易成功",
												JOptionPane.INFORMATION_MESSAGE);
									}
									else{
										JOptionPane.showMessageDialog(null,
												"订单提交错误.", "交易失败",
												JOptionPane.ERROR_MESSAGE);
									}
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}																						
								setVisible(false);								
							}
							
						});
						setupComponent(confirmButton, 3, 4, 1, 0, false);
		getContentPane().add(contentPane);	
		
	}
	/**
	 * 设置组件位置并添加到容器中的方法
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param ipadx
	 * @param fill
	 */
	private void setupComponent(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);//设置最小间距
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;//设置宽度
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;//横向加长文本框以满足输入需求
		add(component, gridBagConstrains);
		
		
	}
	public  String getPaymentmethod(){
		String payment = null;
		if (creditcard.isSelected()){
			payment = creditcard.getText();
		}
		else if (cash.isSelected()){
			payment = cash.getText();
		}
		else if (banktransfer.isSelected()){
			payment = banktransfer.getText();
		}
		return payment;
	}
	public  String getAddress(){
		String address = textFieldaddress.getText();
		return address;
	}
	
}


