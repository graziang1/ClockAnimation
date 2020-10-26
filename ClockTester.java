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

		JTextField enterHrs = new JTextField("Enter hour", 10);
		enterHrs.setBackground(Color.yellow);
		buttonPanel.add(enterHrs);

		JTextField enterMins = new JTextField("Enter minute", 10);
		enterMins.setBackground(Color.yellow);
		buttonPanel.add(enterMins);

		JButton setTime = new JButton("Set");
		buttonPanel.add(setTime);

		JButton resetTime = new JButton("Reset");
		buttonPanel.add(resetTime);


		JFrame frame = new JFrame();
		frame.setTitle("Clock Animation - Part 2");
		frame.setLayout(new BorderLayout());
		frame.add(label,BorderLayout.NORTH); //include clock
		frame.add(buttonPanel,BorderLayout.SOUTH); //include button panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);


		final int DELAY = 1000; //1 tick per second, or 1000ms per second
		Timer t = new Timer(DELAY, new ActionListener(){ //define the event handler
			public void actionPerformed(ActionEvent event){
				shape.translate(1,0);    //update motion
				label.repaint();    //repaint image
		   }
		});
		t.start();    //start timer	
	}
}