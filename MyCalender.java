import java.awt.EventQueue;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;



public class MyCalender extends JFrame {
		String arr[][] = new String[6][7];
		int count[][] = new int[6][7];
		String[] weekdays = { "Sun", "Mon", "Tue", "Wed", "thu", "Fri", "Sat" };
		Calendar cal;
		Calendar calP;
		int maxDays;
		int preMax;
		int weekday;

		String[][] consolCal(int month, int year) {
			
			cal = Calendar.getInstance();
			cal.set(year, month - 1, 1);
			
			maxDays = cal.getActualMaximum(Calendar.DATE);
			weekday = cal.get(Calendar.DAY_OF_WEEK);

			int day = 1;
			int a = 1;
			int prev;
			int next = 1;
			
			calP = Calendar.getInstance();
			calP.set(year, month - 2, 1);
			prev = calP.getActualMaximum(Calendar.DATE);
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					count[i][j] = a;
					a++;
				}
			}
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(count[i][j] < weekday){
						arr[i][j] = prev - weekday + 2+ "";
						prev++;
					}else if(count[i][j] > weekday+maxDays - 1){
						arr[i][j] = next + "";
						next++;
					}else{
						arr[i][j] = day + "";
						day++;
					}
				}
			}
			return arr;
			
		}

	private JPanel contentPane;
	int month = 12; int year = 2015;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyCalender frame = new MyCalender();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyCalender() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Current month/year: " + month + "/" + year);
		JButton nextMonth = new JButton("Next Month");
		JButton lastMonth = new JButton("Last Month");
		JButton lastYear = new JButton("Last Year");
		JButton nextYear = new JButton("Next Year");
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lastYear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lastMonth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(nextYear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(nextMonth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lastMonth)
								.addComponent(nextMonth))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lastYear)
								.addComponent(nextYear)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		
		String[][] data = consolCal(month, year);
		
		class MyTableModel extends AbstractTableModel {
		    public String getColumnName(int col) {
		        return weekdays[col].toString();
		    }
		    public int getRowCount() { return data.length; }
		    public int getColumnCount() { return weekdays.length; }
		    public Object getValueAt(int row, int col) {
		        return data[row][col];
		    }
		    public boolean isCellEditable(int row, int col)
		        { return true; }
		    
		};

		JTable table_1 = new JTable(new MyTableModel());
		scrollPane.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);
		
		
		lastMonth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(month<=1){
					month = 13;
					year--;
				}
				month = month - 1;
				lblNewLabel.setText("Current month/year: " + month + "/" + year);
				
				MyTableModel model1 = new MyTableModel();
				for(int i=0; i < consolCal(month, year).length; i++){
					for(int j=0; j < 7; j++){
						model1.setValueAt(consolCal(month, year), i, j);
					}
				}
				table_1.setModel(model1);
			}
		});
		
		nextMonth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(month>=12){
					month = 0;
					year++;
				}
				month = month + 1;
				lblNewLabel.setText("Current month/year: " + month + "/" + year);
				
				MyTableModel model1 = new MyTableModel();
				for(int i=0; i < consolCal(month, year).length; i++){
					for(int j=0; j < 7; j++){
						model1.setValueAt(consolCal(month, year), i, j);
					}
				}
				table_1.setModel(model1);
			}
		});
		lastYear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				year = year - 1;
				lblNewLabel.setText("Current month/year: " + month + "/" + year);
				
				MyTableModel model1 = new MyTableModel();
				for(int i=0; i < consolCal(month, year).length; i++){
					for(int j=0; j < 7; j++){
						model1.setValueAt(consolCal(month, year), i, j);
					}
				}
				table_1.setModel(model1);
			}
		});
		nextYear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				year = year + 1;
				lblNewLabel.setText("Current month/year: " + month + "/" + year);
				
				MyTableModel model1 = new MyTableModel();
				for(int i=0; i < consolCal(month, year).length; i++){
					for(int j=0; j < 7; j++){
						model1.setValueAt(consolCal(month, year), i, j);
					}
				}
				table_1.setModel(model1);
			}
		});
	}
}

