package com.Pineapple.client.iframe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.Pineapple.Dao.model.Computer;

public class Checkdeadorder extends JInternalFrame{
	private JTable table;
	private Socket socketClient;
	public Checkdeadorder(Socket socketClient) {
		super();//先构造一个内部窗口
		this.socketClient = socketClient;
		setIconifiable(true);//开启内部窗口最小化功能
		setClosable(true);//开启内部窗口关闭功能
		setTitle("历史订单");//设置窗口标题
		getContentPane().setLayout(new GridBagLayout());//窗口内部布局设置
		setBounds(100, 100, 600, 375);//窗口大小设置

		table = new JTable();//新建一个表
		table.setEnabled(false);//设置表格使能关闭，即不与用户交互
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//表格自动调整尺寸方式
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();//表格模型强制转换为向量模型
		String[] tableHeads = new String[]{"型号", "名称", "类型", "价格", "图片",};//添加表头
		dftm.setColumnIdentifiers(tableHeads);//把表头设置为每栏的标示
		
		//把表格放置到有滚动条的面板上,控制表格位置
		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 0;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

////////////////////////////////////////////////////////////////////////////////////////////
		setupComponet(new JLabel("                    "), 2, 1, 2, 1, false);
		setupComponet(new JLabel("                              "), 4, 1, 3, 1, false);
		setupComponet(new JLabel("          "), 0, 1, 1, 1, false);
		setupComponet(new JLabel("          "), 8, 1, 1, 1, false);
		final JButton buynowButton = new JButton();
		//buynowButton.addActionListener(new QueryAction(dftm));
		setupComponet(buynowButton, 1, 1, 1, 1, false);
		buynowButton.setText("删除所选订单");
					
		final JButton clearButton = new JButton();
		//addtobagButton.addActionListener(new QueryAction(dftm));
		setupComponet(clearButton, 7, 1, 1, 1, false);
		clearButton.setText("清空历史订单");
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
		gridBagConstrains.insets = new Insets(0, 1, 0, 1);
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
		while (iterator.hasNext()) {
			Computer computer = (Computer) iterator.next();//获取链表中的元素
			Vector rowData = new Vector();
			rowData.add(computer.getId());
			rowData.add(computer.getName());
			rowData.add(computer.getType());
			rowData.add(computer.getPrice());
			rowData.add(computer.getPicture());
			dftm.addRow(rowData);
		}
	}
	

}
