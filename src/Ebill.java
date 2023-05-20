import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.awt.*;

public class Ebill implements ActionListener{

	JFrame frame;
	JPanel panel;
	JLabel title;
	JPanel content;
	JLabel cidLabel;
	JLabel cnameLabel;
	JLabel unitLabel;
	JTextField cidTextField;
	JTextField cnameTextField;
	JTextField unitTextField;
	JTextArea billText;
	JButton calButton;
	JButton printButton;
	
	Ebill() {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(new Dimension(600,500));
		
		panel = new JPanel();
		panel.setBackground(new Color(102,102,102));
		panel.setBounds(0,0,600,500);
		panel.setLayout(null);
		
		title = new JLabel("Electricity Billing System");
		title.setOpaque(true);
		title.setFont(new Font("Ink Free", Font.BOLD, 25));
		title.setForeground(new Color(50,50,200));
		title.setBackground(new Color(102,102,102));
		title.setBounds(150,10,300,50);
		
		cidLabel = new JLabel("Customer ID");
		cidLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
		cidLabel.setForeground(new Color(0,0,204));
		cidLabel.setBounds(10,30,110,25);
		
		cidTextField = new JTextField();
		cidTextField.setBorder(BorderFactory.createBevelBorder(1));
		cidTextField.setBounds(140,30,100,25);
		
		cnameLabel = new JLabel("Customer Name");
		cnameLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
		cnameLabel.setForeground(new Color(0,0,204));
		cnameLabel.setBounds(10,85,110,25);
		
		cnameTextField = new JTextField();
		cnameTextField.setBorder(BorderFactory.createBevelBorder(1));
		cnameTextField.setBounds(140,85,100,25);
		
		unitLabel = new JLabel("Unit");
		unitLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
		unitLabel.setForeground(new Color(0,0,204));
		unitLabel.setBounds(10,145,110,25);
		
		unitTextField = new JTextField();
		unitTextField.setBorder(BorderFactory.createBevelBorder(1));
		unitTextField.setBounds(140,145,100,25);
		
		billText = new JTextArea();
		billText.setBorder(BorderFactory.createBevelBorder(1));
		billText.setEditable(false);
		billText.setBounds(280,30,190,245);
		
		calButton = new JButton("Calculate");
		calButton.setFont(new Font("MV Boli", Font.PLAIN, 15));
		calButton.setFocusable(false);
		calButton.setBounds(75,225,100,25);
		calButton.addActionListener(this);
		
		printButton = new JButton("Print");
		printButton.setFont(new Font("MV Boli", Font.PLAIN, 15));
		printButton.setFocusable(false);
		printButton.setBounds(280,305,100,25);
		printButton.addActionListener(this);
		
		content= new JPanel();
		content.setLayout(null);
		content.setBorder(BorderFactory.createBevelBorder(1));
		content.setBounds(50,80,500,350);
		content.setBackground(Color.gray);
		
		content.add(cidLabel);
		content.add(cidTextField);
		content.add(cnameLabel);
		content.add(cnameTextField);
		content.add(unitLabel);
		content.add(unitTextField);
		content.add(billText);
		content.add(calButton);
		content.add(printButton);
		panel.add(title);
		panel.add(content);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
	public void showBill(String cid, String cname, int unit, double totalAmount) {
		
		DecimalFormat df = new DecimalFormat("#.#");
		
		billText.setText("");
		billText.setText("Customer ID: " + cid + "\n");
		billText.setText(billText.getText() + "Customer Name: " + cname + "\n");
		billText.setText(billText.getText() + "Unit: " + String.valueOf(unit) + "\n");
		billText.setText(billText.getText() + "Amount: $" + String.valueOf(df.format(totalAmount)) + "\n");
		billText.setText(billText.getText() + "Thank you for using our service <3");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == calButton) {
			String cid = cidTextField.getText();
			String cname = cnameTextField.getText();
			int unit = Integer.parseInt(unitTextField.getText());
			double totalAmount;
			
			// referring to HK CLP energy charge bimonthly rate (HK$/unit)
			if(unit > 4200) {
				totalAmount = (0.87*400)+(1.004*600)+(1.162*800)+(1.47*800)+(1.699*800)+(1.803*800)+(unit-4200)*1.815;
			}
			else if(unit > 3400) {
				totalAmount = (0.87*400)+(1.004*600)+(1.162*800)+(1.47*800)+(1.699*800)+(unit-3400)*1.803;
			}
			else if (unit > 2600) {
				totalAmount = (0.87*400)+(1.004*600)+(1.162*800)+(1.47*800)+(unit-2600)*1.699;
			}
			else if(unit > 1800) {
				totalAmount = (0.87*400)+(1.004*600)+(1.162*800)+(unit-1800)*1.47;
			}
			else if (unit > 1000) {
				totalAmount = (0.87*400)+(1.004*600)+(unit-1000)*1.162;
			}
			else if (unit > 400) {
				totalAmount = (0.87*400)+(unit-400)*1.004;
			}
			else {
				totalAmount = unit*0.87;
			}
			
			showBill(cid, cname, unit, totalAmount);
		}
		
		if(e.getSource() == printButton) {
			try {
				billText.print();
			} catch (PrinterException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
	
}
