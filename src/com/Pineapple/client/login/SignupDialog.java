package com.Pineapple.client.login;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Pineapple.client.MainFrame;

import com.Pineapple.Dao.model.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
public class SignupDialog extends JFrame{
	
	private JTextField textFieldName;
	private JPasswordField passwordField = null;
	private JPasswordField repasswordField = null;
	private JTextField textFieldEmail;
	private JButton resetButton;
	
	
	private Client client;
	private String name;
	private char[] password; 
	private String email;
	private char[] repassword;
	
	private MainFrame mainFrame;//主框架类
	private Socket socketClient;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private ObjectOutputStream outBean = null;
	private String accept;
	
	
	public SignupDialog(Socket socketClient){
		super();
		this.socketClient = socketClient;
		Dimension size = getToolkit().getScreenSize();//提取屏幕尺寸
		setLocation((size.width - 320) / 2, (size.height - 250) / 2);//设定窗口位置为屏幕中央
		setSize(320, 250);//设置窗口大小
		setTitle("用户注册");//this指代本类,因为本类继承的JFrame
		JPanel signupPanel = new JPanel();
		mainFrame = new MainFrame();
		
		
		
		//设置尺寸和布局，并将它显示出来
		signupPanel.setBounds(10, 10, 460, 300);
		signupPanel.setLayout(new GridBagLayout());
		
		//所有的标签都设成final
		final JLabel username = new JLabel();
		username.setText("用户名：");
		setupComponent(signupPanel,username, 0, 0, 1, 0, false);
		textFieldName = new JTextField();
		// 定位用户名输入文本框
		setupComponent(signupPanel,textFieldName, 1, 0, 5, 200, true);
		////////////////////////////////////////////////////////
		final JLabel password = new JLabel("设置密码：");
		setupComponent(signupPanel,password, 0, 1, 1, 0, false);
		passwordField = new JPasswordField();
		// 定位密码输入文本框
		setupComponent(signupPanel,passwordField, 1, 1, 5, 100, true);
		////////////////////////////////////////////////////////
		final JLabel repassword = new JLabel();
		repassword.setText("重复密码：");
		setupComponent(signupPanel,repassword, 0, 2, 1, 0, false);
		repasswordField= new JPasswordField();
		// 定位重复密码文本框
		setupComponent(signupPanel,repasswordField, 1, 2, 5, 100, true);
		////////////////////////////////////////////////////////
		final JLabel email = new JLabel();
		email.setText("邮箱:");
		setupComponent(signupPanel,email,0,3,1,0,false);
		textFieldEmail = new JTextField();
		setupComponent(signupPanel,textFieldEmail, 1, 3, 5, 0, true);
		//添加按钮
				final JButton addButton = new JButton();
				//添加按钮的事件监听
				addButton.addActionListener(new ActionListener() {
					//先判断信息是否齐全，再执行数据库操作
					public void actionPerformed(final ActionEvent e) {
						if (textFieldName.getText().equals("")
								|| textFieldEmail.getText().equals("")
								|| passwordField.getPassword().equals("")
								|| repasswordField.getPassword().equals("")) {
							JOptionPane.showMessageDialog(null,
									"请完成未填写的信息。", "用户注册", JOptionPane.ERROR_MESSAGE);
							return;
						}
						 // 校验两次输入的密码是否相同
					       if (!Arrays.equals(passwordField.getPassword(), repasswordField.getPassword())) {
					           JOptionPane.showMessageDialog(null, "两次输入的密码不同！", "警告信息", JOptionPane.WARNING_MESSAGE);
					           return;
					       }
					      try{
					    	  String username = textFieldName.getText();
					    	  out = new DataOutputStream(socketClient.getOutputStream());
					    	  out.writeUTF("SIGNUP");
					    	  out.flush();
					    	  out.writeUTF(username);
					    	  out.flush();
					    	  in = new DataInputStream(socketClient.getInputStream());
					    	  accept = in.readUTF();
					       // 校验用户名是否存在
					       if (accept.equals("true")) {
					           JOptionPane.showMessageDialog(null, "用户名已存在", "警告信息", JOptionPane.WARNING_MESSAGE);
					           return;
					       		}
					
							}	
						 catch (Exception e1) {
							e1.printStackTrace();
						}
						Client client = new Client();
						client.setUsername(textFieldName.getText());
						client.setPassword(String.valueOf(passwordField.getPassword()));
						client.setEmail(textFieldEmail.getText());
						
						try {
							outBean = new ObjectOutputStream(socketClient.getOutputStream());
							outBean.writeObject(client);
							outBean.flush();
							accept = in.readUTF();							
							if (accept.equals("true")) {
								
								JOptionPane.showMessageDialog(null,
										"恭喜，用户注册成功", "注册成功成功",
										JOptionPane.INFORMATION_MESSAGE);
								mainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);//主界面的小红叉执行退出操作
								mainFrame.setVisible(true);//显示主框架
								MainFrame.getCzyStateLabel().setText(textFieldName.getText());//状态栏信息更改
								setVisible(false);
								return;
							}
							
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					
				
			
					}
				});
				addButton.setText("注册");
				setupComponent(signupPanel,addButton, 1, 4, 1, 1, false);
				
				resetButton = new JButton();
				resetButton.setText("重置");
				setupComponent(signupPanel,resetButton, 5, 4, 1, 1, false);
				// 重添按钮的事件监听类
						resetButton.addActionListener(new ActionListener() {
							public void actionPerformed(final ActionEvent e) {
								textFieldName.setText("");
								textFieldEmail.setText("");
								passwordField.setText("");
								repasswordField.setText("");
								
							}
						});
						
		setContentPane(signupPanel);//把signuppanel放到frame上					
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 设置组件位置并添加到容器中的方法
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param ipadx
	 * @param fill
	 */
	private void setupComponent(JPanel signupPanel,JComponent component, int gridx, int gridy,
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
		signupPanel.add(component, gridBagConstrains);
		
		
	}
	
	
	
					
	

}
