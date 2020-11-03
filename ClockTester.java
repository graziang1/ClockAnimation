/*
	Gianna Graziano
	Clock Assignment Part 2
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
//Builds the frame of the clock and initiates the animation
public class ClockTester{
	public static void main(String[] args){
		ClockIcon shape = new ClockIcon(0,0,100,0,0,0);
		ShapeIcon icon = new ShapeIcon(shape,600,600); 


		final JLabel label = new JLabel(icon);
		label.setBackground(Color.blue);
		label.setOpaque(true);


		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.lightGray);

		JTextField enterHrs = new JTextField("Enter starting hour", 12);
		enterHrs.setBackground(Color.yellow);
		buttonPanel.add(enterHrs);

		JTextField enterMins = new JTextField("Enter starting minute", 12);
		enterMins.setBackground(Color.yellow);
		buttonPanel.add(enterMins);


		final int DELAY = 1000; //1 tick per second, or 1000ms between timer ticks
		Timer t = new Timer(DELAY, new ActionListener(){ //define the timer event handler
			public void actionPerformed(ActionEvent event){
				shape.translate(1,0);    //update motion
				label.repaint();    //repaint image
		   }
		});

		// t.addActionListener(listenerHours);
		// t.addActionListener(listenerMinutes);
		// t.addActionListener(listenerSeconds);

		JButton setTime = new JButton("Set");
		buttonPanel.add(setTime);
		setTime.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent event){
					t.stop(); //temporarily stop timer
					String hh = enterHrs.getText();
					String mm = enterMins.getText();
					try {
						int h = Integer.parseInt(hh);
						int m = Integer.parseInt(mm);
						shape.translate(h,m); //rotate hands to inputted minute & hour
						label.repaint(); //repaint clock hands
					} catch (NumberFormatException exception){
					System.out.println("This is not a valid time");
					}
					enterHrs.setText("Enter starting hour");
					enterMins.setText("Enter starting minute");
					t.start();
				}
			}
		);

		JButton resetTime = new JButton("Reset");
		buttonPanel.add(resetTime);
		resetTime.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent event){
					t.stop(); //temporarily stop timer
					shape.setH(0); //reset time to 12:00
					shape.setM(0);
					shape.setC(0);
					label.repaint(); //repaint clock hands
					enterHrs.setText("Enter starting hour");
					enterMins.setText("Enter starting minute");
					t.start(); //start timer
				}
			}
		);

		JFrame frame = new JFrame();
		frame.setTitle("Clock Animation - Part 2");
		frame.setLayout(new BorderLayout());
		frame.add(label,BorderLayout.NORTH); //include clock
		frame.add(buttonPanel,BorderLayout.SOUTH); //include button panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}