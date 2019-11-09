package calculator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class calcul extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args){
		String[] buttonName= {"12","","2","=","","+","-","*","/","OK"};
		JFrame frame = new JFrame("Easy Calculator");
		frame.setBounds(200, 200, 300, 150);
		// 更改默认布局管理器为GridLayout
		frame.setLayout(new GridLayout(2, 5, 1, 1));
		// frame.setLayout(new GridLayout());
		// frame.setLayout(new GridLayout(3, 3));
		JButton[] buttons= {new JButton(buttonName[0]),new JButton(buttonName[1]),new JButton(buttonName[2]),
				new JButton(buttonName[3]),new JButton(buttonName[4]),new JButton(buttonName[5]),
				new JButton(buttonName[6]),new JButton(buttonName[7]),new JButton(buttonName[8]),
				new JButton(buttonName[9])};
		JTextField result=new JTextField(0);
		for (int i = 0; i < 10; i++){
			if(i==4)
			{
				frame.add(result);
			}
			else{
				buttons[i].addActionListener(new ActionListener() {
					String outPut= "";
					Integer calcul=12;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String action=e.getActionCommand();
						if(action.equals("+")){
							calcul=calcul+2;
							buttons[1].setText(action);
						}
						else if(action.equals("-"))
						{
							calcul=calcul-2;
							buttons[1].setText(action);
						}
						else if(action.equals("*"))
						{
							calcul=calcul*2;
							buttons[1].setText(action);
						}
						else if(action.equals("/")) {
							calcul=calcul/2;
							buttons[1].setText(action);
						}
						else if(action.equals("OK"))
						{
							buttons[1].setText("");
							result.setText("");
						}
						outPut=calcul+"";
						result.setText(outPut);
						calcul=12;
					}
				});
				frame.add(buttons[i]);	
			}
			}
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
