package HomeWork;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


	public class Lottery extends JFrame {

		private JPanel contentPane;
		public LinkedHashSet<String> ChoseNumSet = new LinkedHashSet<>();
		public LinkedHashSet<String> RandomChose = new LinkedHashSet<>();
		public LinkedHashSet<String> BigLottery = new LinkedHashSet<>();
		public LinkedHashSet<String> temp = new LinkedHashSet<>();
		public LinkedHashSet<String> temp2 = new LinkedHashSet<>();
		public LinkedHashSet<JButton> mybutton = new LinkedHashSet<>();
		JButton btnOpen = new JButton("本期開獎");
		JButton btnResult = new JButton("中獎結果");
		
		public String strRandomChose = "";
		private JTextField tfMyChoseNum;
		private JTextField tf1;
		private JTextField tf7;
		private JTextField tf6;
		private JTextField tf5;
		private JTextField tf4;
		private JTextField tf3;
		private JTextField tf2;
		private JTextField tf8;
		private JTextField tf9;
		private JTextField tf10;
		private JTextField tf11;
		private JTextField tf12;
		private JTextField tf13;
	    int i;
	    private JTextField tfMessage;
	    
		

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) 
		{
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Lottery frame = new Lottery();						
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
		public Lottery() 
		{
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("大樂透");
			setBounds(100, 100, 713, 724);
			contentPane = new JPanel();
			contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(0, 0, 0));
			panel_1.setPreferredSize(new Dimension(420, 360));
			contentPane.add(panel_1, BorderLayout.NORTH);
			panel_1.setLayout(null);
			
			Makebutton(7,7,panel_1);
					
			JPanel tfMyNum = new JPanel();
			tfMyNum.setBackground(new Color(0, 0, 0));
			contentPane.add(tfMyNum, BorderLayout.CENTER);
			tfMyNum.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("您的號碼");
			lblNewLabel.setBackground(new Color(128, 128, 0));
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setOpaque(true);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			lblNewLabel.setBounds(22, 23, 83, 36);
			tfMyNum.add(lblNewLabel);
			
			JLabel label = new JLabel("開獎號碼");
			label.setBackground(new Color(128, 128, 0));
			label.setForeground(new Color(0, 191, 255));
			label.setOpaque(true);
			label.setVerticalAlignment(SwingConstants.CENTER);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			label.setBounds(22, 89, 83, 36);
			tfMyNum.add(label);
			

			btnOpen.addActionListener(new ActionListener() {
				javax.swing.Timer t = new javax.swing.Timer(33, this);
				public void actionPerformed(ActionEvent e) {
					btnResult.setEnabled(false);
					i++;
					t.start();
					BigLottery.clear();
					getRandom(BigLottery,temp2, 7);				
					Iterator<String> bl =BigLottery.iterator();
					
						 tf1.setText(bl.next());
				         tf2.setText(bl.next());
				         tf3.setText(bl.next());
				         tf4.setText(bl.next());
				         tf5.setText(bl.next());
				         tf6.setText(bl.next());
				         tf7.setText(bl.next());
				        
				    while(i==30) 
					{
						 i=0;
						 t.stop();						
					}
				    btnResult.setEnabled(true); 
				}
			});
			
			JButton btnRandom = new JButton("電腦選號");
			btnRandom.addActionListener(new ActionListener() {
				javax.swing.Timer t = new javax.swing.Timer(33, this);
				
				public void actionPerformed(ActionEvent e) {
					btnResult.setEnabled(false);
					i++;
					t.start();
				    resetbg();
					resetfg();
					RandomChose.clear();
					ChoseNumSet.clear();
					strRandomChose="";
							
					getRandom(RandomChose,temp, 6);
					Iterator<String> rc =RandomChose.iterator();				
				    while(rc.hasNext())
					{										
						strRandomChose += rc.next()+"     ";
					}
					ChangeColor (RandomChose,Color.orange);				
				    tfMyChoseNum.setText(strRandomChose);
					
				   while(i==30) 
				   {
					   i=0;
					   t.stop();						
				   }
				   btnOpen.setEnabled(true);
				   btnResult.setEnabled(true);
				}
				
			});
			btnRandom.setFocusPainted(false);
			btnRandom.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			btnRandom.setBounds(540, 23, 113, 36);
			tfMyNum.add(btnRandom);
			
			
			btnOpen.setFocusPainted(false);
			btnOpen.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			btnOpen.setBounds(540, 89, 113, 36);
			tfMyNum.add(btnOpen);
			
			
			btnResult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if(ChoseNumSet.isEmpty()==true)
					{
					
						ArrayList<String> list1 = new ArrayList<>();
						BigLottery.retainAll(RandomChose);
						ChangeColor(BigLottery,Color.pink);
						if(BigLottery.contains(tf7.getText()))
						{
							switch(BigLottery.size())
							{
							    case 1:
								   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
								   break;
							    case 2:
									   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
									   break;
							    case 3:
									   tfMessage.setText("恭喜您中了柒獎,獲得獎金 新台幣 400 元 !!");
									   break;
							    case 4:
									   tfMessage.setText("恭喜您中了陸獎,獲得獎金 新台幣 1000 元 !!");
									   break;
							    case 5:
									   tfMessage.setText("恭喜您中了肆獎!!");
									   break;
							    case 6:
									   tfMessage.setText("恭喜您中了貳獎!!");
									   break;
								default:
									   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
							}
						}
						else
						{
							switch(BigLottery.size())
							{
							    case 1:
								   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
								   break;
							    case 2:
									   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
									   break;
							    case 3:
									   tfMessage.setText("恭喜您中了普獎,獲得獎金 新台幣 400 元 !!");
									   break;
							    case 4:
									   tfMessage.setText("恭喜您中了伍獎,獲得獎金 新台幣 2000 元 !!");
									   break;
							    case 5:
									   tfMessage.setText("恭喜您中了參獎!!");
									   break;
							    case 6:
									   tfMessage.setText("恭喜您中了頭獎!!");
									   break;
								default:
									   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
							}
						}
												
						Iterator<String> bl =BigLottery.iterator();
						while(bl.hasNext())
						{
							list1.add(bl.next());
						}					
						while(list1.size()<6)
						{
							list1.add("");
						}
						Iterator<String> l1 =list1.iterator();					
							 tf8.setText(l1.next());
					         tf9.setText(l1.next());
					         tf10.setText(l1.next());
					         tf11.setText(l1.next());
					         tf12.setText(l1.next());
					         tf13.setText(l1.next());
					         

					         BigLottery.addAll(temp2);	

					}					
					else
					{   
				        if(ChoseNumSet.size()==6)
				        {
				        	ArrayList<String> list1 = new ArrayList<>();
							BigLottery.retainAll(ChoseNumSet);
							ChangeColor(BigLottery,Color.pink);
							if(BigLottery.contains(tf7.getText()))
							{
								switch(BigLottery.size())
								{
								    case 1:
									   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
									   break;
								    case 2:
										   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
										   break;
								    case 3:
										   tfMessage.setText("恭喜您中了柒獎,獲得獎金 新台幣 400 元 !!");
										   break;
								    case 4:
										   tfMessage.setText("恭喜您中了陸獎,獲得獎金 新台幣 1000 元 !!");
										   break;
								    case 5:
										   tfMessage.setText("恭喜您中了肆獎!!");
										   break;
								    case 6:
										   tfMessage.setText("恭喜您中了貳獎!!");
										   break;
									default:
										   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
								}
							}
							else
							{
								switch(BigLottery.size())
								{
								    case 1:
									   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
									   break;
								    case 2:
										   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
										   break;
								    case 3:
										   tfMessage.setText("恭喜您中了普獎,獲得獎金 新台幣 400 元 !!");
										   break;
								    case 4:
										   tfMessage.setText("恭喜您中了伍獎,獲得獎金 新台幣 2000 元 !!");
										   break;
								    case 5:
										   tfMessage.setText("恭喜您中了參獎!!");
										   break;
								    case 6:
										   tfMessage.setText("恭喜您中了頭獎!!");
										   break;
									default:
										   tfMessage.setText("很抱歉您沒中獎,下次手氣會更好!!");
								}
							}
							Iterator<String> bl =BigLottery.iterator();
							while(bl.hasNext())
							{
								list1.add(bl.next());
							}
							while(list1.size()<6)
							{
								list1.add("");
							}
							Iterator<String> l1 =list1.iterator();	
							     tf8.setText(l1.next());
					             tf9.setText(l1.next());
					             tf10.setText(l1.next());
					             tf11.setText(l1.next());
					             tf12.setText(l1.next());
					             tf13.setText(l1.next());

					             BigLottery.addAll(temp2);		
				        }
				        else
				        {
				        	JOptionPane.showMessageDialog(null,"請選滿6個號碼");
				        }
								 
					}
					
					btnOpen.setEnabled(false);
				}
			});
			btnResult.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			btnResult.setFocusPainted(false);
			btnResult.setBounds(540, 153, 113, 36);
			tfMyNum.add(btnResult);
			
			tfMyChoseNum = new JTextField();
			tfMyChoseNum.setHorizontalAlignment(SwingConstants.CENTER);
			tfMyChoseNum.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tfMyChoseNum.setEditable(false);
			tfMyChoseNum.setBounds(115, 23, 328, 36);
			tfMyNum.add(tfMyChoseNum);
			tfMyChoseNum.setColumns(10);
			
			tf1 = new JTextField();
			tf1.setHorizontalAlignment(SwingConstants.CENTER);
			tf1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf1.setEditable(false);
			tf1.setBounds(115, 89, 46, 36);
			tfMyNum.add(tf1);
			tf1.setColumns(10);
			
			tf7 = new JTextField();
			tf7.setBackground(new Color(255, 255, 0));
			tf7.setHorizontalAlignment(SwingConstants.CENTER);
			tf7.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf7.setEditable(false);
			tf7.setColumns(10);
			tf7.setBounds(453, 89, 46, 36);
			tfMyNum.add(tf7);
			
			tf6 = new JTextField();
			tf6.setHorizontalAlignment(SwingConstants.CENTER);
			tf6.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf6.setEditable(false);
			tf6.setColumns(10);
			tf6.setBounds(397, 89, 46, 36);
			tfMyNum.add(tf6);
			
			tf5 = new JTextField();
			tf5.setHorizontalAlignment(SwingConstants.CENTER);
			tf5.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf5.setEditable(false);
			tf5.setColumns(10);
			tf5.setBounds(341, 89, 46, 36);
			tfMyNum.add(tf5);
			
			tf4 = new JTextField();
			tf4.setHorizontalAlignment(SwingConstants.CENTER);
			tf4.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf4.setEditable(false);
			tf4.setColumns(10);
			tf4.setBounds(285, 89, 46, 36);
			tfMyNum.add(tf4);
			
			tf3 = new JTextField();
			tf3.setHorizontalAlignment(SwingConstants.CENTER);
			tf3.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf3.setEditable(false);
			tf3.setColumns(10);
			tf3.setBounds(229, 89, 46, 36);
			tfMyNum.add(tf3);
			
			tf2 = new JTextField();
			tf2.setHorizontalAlignment(SwingConstants.CENTER);
			tf2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf2.setEditable(false);
			tf2.setColumns(10);
			tf2.setBounds(171, 89, 46, 36);
			tfMyNum.add(tf2);
			
			JLabel label_1 = new JLabel("中獎號碼");
			label_1.setVerticalAlignment(SwingConstants.CENTER);
			label_1.setOpaque(true);
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setForeground(new Color(255, 0, 0));
			label_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			label_1.setBackground(new Color(128, 128, 0));
			label_1.setBounds(22, 153, 83, 36);
			tfMyNum.add(label_1);
			
			tf8 = new JTextField();
			tf8.setHorizontalAlignment(SwingConstants.CENTER);
			tf8.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf8.setEditable(false);
			tf8.setColumns(10);
			tf8.setBounds(115, 153, 46, 36);
			tfMyNum.add(tf8);
			
			tf9 = new JTextField();
			tf9.setHorizontalAlignment(SwingConstants.CENTER);
			tf9.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf9.setEditable(false);
			tf9.setColumns(10);
			tf9.setBounds(171, 153, 46, 36);
			tfMyNum.add(tf9);
			
			tf10 = new JTextField();
			tf10.setHorizontalAlignment(SwingConstants.CENTER);
			tf10.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf10.setEditable(false);
			tf10.setColumns(10);
			tf10.setBounds(229, 153, 46, 36);
			tfMyNum.add(tf10);
			
			tf11 = new JTextField();
			tf11.setHorizontalAlignment(SwingConstants.CENTER);
			tf11.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf11.setEditable(false);
			tf11.setColumns(10);
			tf11.setBounds(285, 153, 46, 36);
			tfMyNum.add(tf11);
			
			tf12 = new JTextField();
			tf12.setHorizontalAlignment(SwingConstants.CENTER);
			tf12.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf12.setEditable(false);
			tf12.setColumns(10);
			tf12.setBounds(341, 153, 46, 36);
			tfMyNum.add(tf12);
			
			tf13 = new JTextField();
			tf13.setHorizontalAlignment(SwingConstants.CENTER);
			tf13.setFont(new Font("微軟正黑體", Font.BOLD, 20));
			tf13.setEditable(false);
			tf13.setColumns(10);
			tf13.setBounds(397, 153, 46, 36);
			tfMyNum.add(tf13);
			
			tfMessage = new JTextField();
			tfMessage.setEditable(false);
			tfMessage.setForeground(new Color(0, 0, 0));
			tfMessage.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			tfMessage.setBounds(22, 226, 421, 72);
			tfMyNum.add(tfMessage);
			tfMessage.setColumns(10);
									
		}
		
		
		public void Makebutton(int icount1,int icount2,JPanel Jp)
		{		
			for(int i=0;i<icount1;i+=1)
			{
				for(int j=0;j<icount2;j+=1)
				{
					JButton dbutton = new JButton();
					if(7*i+j+1<10)
					{
						dbutton.setText("0"+Integer.toString(7*i+j+1));
					}
					else
					{
						dbutton.setText(Integer.toString(7*i+j+1));
					}
					dbutton.setPreferredSize(new Dimension(60, 40));
					dbutton.setBackground(Color.lightGray);
					dbutton.setFocusPainted(false);
					dbutton.setMinimumSize(new Dimension(60, 40));
					dbutton.setMaximumSize(new Dimension(60, 40));
					dbutton.setFont(new Font("微軟正黑體", Font.BOLD, 22));
					dbutton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					dbutton.setBounds(36+j*87, 10+i*50, 87, 40);
					dbutton.addActionListener(new ActionListener() {
									
						@Override
						public void actionPerformed(ActionEvent e) {
											RandomChose.clear();
											btnOpen.setEnabled(true);
											resetbg2();
							if(dbutton.getBackground()==Color.lightGray)
							{
								if(ChoseNumSet.size()<6)
								{								
									strRandomChose="";
									dbutton.setBackground(Color.red);
									dbutton.setForeground(Color.WHITE);
									ChoseNumSet.add(dbutton.getText());	
									temp.add(dbutton.getText());
									Iterator<String> cns =ChoseNumSet.iterator();
									 while(cns.hasNext())
								        {
										  strRandomChose += cns.next()+"     ";
								        }

									tfMyChoseNum.setText(strRandomChose);
								}
								else
								{
									
									JOptionPane.showMessageDialog(null,"最多只能輸入6個號碼");																
								}
																
							}						
							else
							{							
								strRandomChose="";
								dbutton.setBackground(Color.lightGray);
								dbutton.setForeground(Color.black);
								ChoseNumSet.remove(dbutton.getText());
								temp.remove(dbutton.getText());
								Iterator<String> cns =ChoseNumSet.iterator();
								 while(cns.hasNext())
							        {
									  strRandomChose += cns.next()+"     ";
							        }

								tfMyChoseNum.setText(strRandomChose);
							}
						
						}										
					});				
					Jp.add(dbutton);
					mybutton.add(dbutton);
				}			
			}
		}
		
		public void resetbg()
		{		
			Iterator<JButton> bt = mybutton.iterator();
			while(bt.hasNext())
	        {
			  bt.next().setBackground(Color.lightGray);		  
	        }
		}
		public void resetfg()
		{
			Iterator<JButton> bt = mybutton.iterator();
			while(bt.hasNext())
	        {
				bt.next().setForeground(Color.black);
	        }
		}
		
		public void resetbg2()
		{		
			Iterator<JButton> bt = mybutton.iterator();
			while(bt.hasNext())
	        {
				if(bt.next().getBackground()==Color.orange)
				{
					Iterator<JButton> bt2 = mybutton.iterator();
					while(bt2.hasNext())
					{
						bt2.next().setBackground(Color.lightGray);	
					}				
				}
										  	  
	        }
		}
		
		public void getRandom (Collection<String> set1,Collection<String> set2,int count)
		{				
			set1.clear();
			set2.clear(); 
		       int x;
			   String s1 ="";
			   String s2 ="";	                	
			   while(set1.size()<count)
			   {
				  x=1+(int)(Math.random()*49); 
				  
				  if(x<10)
				  {
					  s1="0"+String.valueOf(x);
					  if(set1.add(s1)==true)
					  {
						  set1.add(s1);	
						  set2.add(s1);
				      }					  		  
				  }
				  else
				  {
					  s2=String.valueOf(x);
					  if(set1.add(s2)==true)
					  {
						  set1.add(String.valueOf(s2));	
						  set2.add(String.valueOf(s2));
					  }				  
				  } 			  
			    }
		        		
		}
		public void ChangeColor (Collection<String> e,Color c)
		{   
			
			
			Iterator<String> rc =e.iterator();
			while(rc.hasNext())
			{	
				int a= Integer.valueOf((rc.next()))-1;
				String Newo="";
				if(a<10)
				{
					if(a==0)
					{					
						Iterator<JButton> jbt = mybutton.iterator();					
						jbt.next().setBackground(c);					
					}
					else
					{
						Newo="0"+String.valueOf(a);
						Iterator<JButton> jbt = mybutton.iterator();
						while(jbt.hasNext())
						{
							if(Newo.equals(jbt.next().getText()))
							{
								jbt.next().setBackground(c);
							}
						}
					}
				}
				else
				{
					Newo=String.valueOf(a);
					Iterator<JButton> jbt = mybutton.iterator();
					while(jbt.hasNext())
					{
						if(Newo.equals(jbt.next().getText()))
						{
							jbt.next().setBackground(c);
						}
					}
				}			
			}		
		}
}




 

 