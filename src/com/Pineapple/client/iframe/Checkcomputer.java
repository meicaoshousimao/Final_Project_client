package com.Pineapple.client.iframe;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.Pineapple.Dao.model.Computer;

public class Checkcomputer extends JInternalFrame{
	private JTable table;
	private JTextField conditionContent;
	//private JComboBox conditionOperation;
	private JComboBox conditionName;
	private JCheckBox checkbox;
	private JButton button;
	public Socket socketClient = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private ObjectInputStream inBean = null;
	private ObjectOutputStream outBean = null;
	private List<Computer> list;
	
	public Checkcomputer() {
		super();//先构造一个内部窗口
		
		setIconifiable(true);//开启内部窗口最小化功能
		setClosable(true);//开启内部窗口关闭功能
		setTitle("电脑商城");//设置窗口标题
		getContentPane().setLayout(new GridBagLayout());//窗口内部布局设置
		setBounds(100, 100, 650, 375);//窗口大小设置

		table = new JTable();//新建一个表
		table.setEnabled(true);//设置表格使能关闭，即不与用户交互/打开
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//表格自动调整尺寸方式
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();//表格模型强制转换为向量模型
		String[] tableHeads = new String[]{ "选取","图片","型号", "名称", "类型", "价格","颜色","屏幕尺寸","硬盘","内存","显卡","处理器","自定义配置"};//添加表头
		dftm.setColumnIdentifiers(tableHeads);//把表头设置为每栏的标示
///////////////////////////////////////////////////////////////////////////////////////////////
		// 方法一：直接方式 使用TableColumn的setCellRenderer方法（推荐）
		           // 此方法可以设置某一列的渲染（即使用某一个组件--即控件来显示单元格数据）
		           table.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer(){
		  
		                /*(non-Javadoc)
		                * 此方法用于向方法调用者返回某一单元格的渲染器（即显示数据的组建--或控件）
		  				* 可以为JCheckBox JComboBox JTextArea 等
		                * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		                */
		               @Override
		               public Component getTableCellRendererComponent(JTable table,
		                       Object value, boolean isSelected, boolean hasFocus,
		                       int row, int column) {
		                   // 创建用于返回的渲染组件
		                   JCheckBox ck = new JCheckBox();
		                   // 使具有焦点的行对应的复选框选中
		                   ck.setSelected(isSelected);
		                   // 设置单选box.setSelected(hasFocus);
		                   // 使复选框在单元格内居中显示
		                   ck.setHorizontalAlignment((int) 0.5f);
		                   return ck;
		               }});
		 ////////////////////////////////////////////////////////////////////////////////
		//把表格放置到有滚动条的面板上,控制表格位置
		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
//查询器//////////////////////////////////////////////////////////////////////////
		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"型号",
				"名称", "类型"}));
		setupComponet(conditionName, 1, 0, 1, 30, true);

	/*	conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"等于",
				"包含"}));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);*/

		conditionContent = new JTextField();
		setupComponet(conditionContent, 2, 0, 2, 140, true);

		final JButton queryButton = new JButton();
		//queryButton.addActionListener(new QueryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("查询");
////////////////////////////////////////////////////////////////////////////////////////////
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				  try {
					out = new DataOutputStream(socketClient.getOutputStream());
					out.writeUTF("SHOWALL");
		    	    out.flush();
		    	    inBean = new ObjectInputStream(socketClient.getInputStream());
		    	    List<Computer> list = (List<Computer>)inBean.readObject();
		    	    updateTable(list, dftm);
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		    	  
				//List<Computer> list = DBCheckcomputer.select();				  
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部商品");
////////////////////////////////////////////////////////////////////////////////////////////
		final JButton buynowButton = new JButton();
		//buynowButton.addActionListener(new QueryAction(dftm));
		setupComponet(buynowButton, 1, 2, 1, 1, false);
		buynowButton.setText("购买选中商品");
		
		final JButton addtobagButton = new JButton();
		//addtobagButton.addActionListener(new QueryAction(dftm));
		setupComponet(addtobagButton, 4, 2, 1, 1, false);
		addtobagButton.setText("添加到购物车");
	}
	
	/**
	 * 添加组件方法
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param ipadx
	 * @param fill
	 */
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	
	/**
	 * 把数据库查询结果表投放到TABLE模型中
	 * @param list
	 * @param dftm
	 */
	private void updateTable(List<Computer> list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();//判断表有多少行
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);//把表中第i行现有内容去掉		
		Iterator iterator = list.iterator();//创建一个迭代器，用于遍历链表
		checkbox = new JCheckBox("");
		button = new JButton("自定义配置");
		while (iterator.hasNext()) {
			Computer computer = (Computer) iterator.next();//获取链表中的元素
			Vector rowData = new Vector();
			rowData.add(null);
			rowData.add(computer.getPicture());
			rowData.add(computer.getId());
			rowData.add(computer.getName());
			rowData.add(computer.getType());
			rowData.add(computer.getPrice());
			
			rowData.add(computer.getColor());
			rowData.add(computer.getSize());
			rowData.add(computer.getStock());
			rowData.add(computer.getMemory());
			rowData.add(computer.getGraphic());
			rowData.add(computer.getProcessor());
			rowData.add(null);
			
			dftm.addRow(rowData);
		}
	}
	
	

}

