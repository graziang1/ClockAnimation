/*
	Gianna Graziano
	Clock Assignment Part 2
*/
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
//Describes the clock
public class ClockIcon implements Moveable{
	//private class variables
	private int xx;
	private int yy;
	private int width;
	private int h; //hours
	private int m; //minutes
	private int count; //count for seconds
	private double theta = -(Math.PI)/2; //angle of rotation for second hand
	private double alpha = -(Math.PI)/2; //angle of rotation for minute hand
	private double gamma = -(Math.PI)/2; //angle of rotation for hour hand

	//constructor
	public ClockIcon(int x, int y, int w, int hr, int min, int c){
		xx = x;
		yy = w;
		width = w;
		h = hr;
		m = min;
		count = c;
	}

	//set methods
	public void setH(int hour){
		h = hour;
	}
	public void setM(int min){
		m = min;
	}
	public void setC(int c){
		count = c;
	}

	/*implementation of Moveable interface*/
	//rotation of the clock hands
	public void translate(int dx, int dy){
		int delx = dx;    //horizontal increment or decrement
		int dely = dy;    //vertical displacement

		count = count + 1; //increment seconds count
		theta = theta + (Math.PI/30); //rotate seconds to the next mark

		if(count%60 == 0){
			alpha = alpha + (Math.PI/30); //rotate minutes to the next mark
		}
		if(count%720 == 0){
			gamma = gamma + (Math.PI/30); //rotate hours to the next mark
		}
	}

	public void draw(Graphics2D g2){
		//center point	
		Point2D.Double origin = new Point2D.Double(300, 300);
		//end of the second hand
		Point2D.Double endSecond = new Point2D.Double(300+118*Math.cos(theta), 300+118*Math.sin(theta));
		//end of the minute hand
		Point2D.Double endMinute = new Point2D.Double(300+158*Math.cos(alpha), 300+158*Math.sin(alpha));
		//end of the hour hand
		Point2D.Double endHour = new Point2D.Double(300+138*Math.cos(gamma), 300+138*Math.sin(gamma));
		
        //random color generator for second hand
        Random rand = new Random();
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        Color randC = new Color(red, green, blue);

		//paint hour hand, which is the 2nd longest
		g2.setColor(Color.pink); 
		g2.setStroke(new BasicStroke(7));
		Shape hours = new Line2D.Double(origin, endHour);
		g2.fill(hours);
		g2.draw(hours);

		//paint minute hand, which is the longest
		g2.setColor(Color.pink); 
		g2.setStroke(new BasicStroke(5));
		Shape minutes = new Line2D.Double(origin, endMinute);
		g2.fill(minutes);
		g2.draw(minutes);

		//paint second hand, which is the shortest
		g2.setColor(randC); 
		g2.setStroke(new BasicStroke(3));
		Shape seconds = new Line2D.Double(origin, endSecond);
		g2.fill(seconds);
		g2.draw(seconds);

		//paint small circle in center of clock to cover where hands meet (drawn from top left)
		//grid points: 300-(10width)/ 2 = 295
		g2.setColor(Color.black);		
		Ellipse2D.Double smallCirc = new Ellipse2D.Double(295, 295, 10, 10);    //1x1 dimensions for a circle
		g2.fill(smallCirc);
		g2.draw(smallCirc);

		//draw second/minute and hour marks around the circle		
		int x;
		for (int i=0; i<60; i++){
			if(i%5 == 0){ //every 5th second/min mark is an hour mark
				x = 4;
				Ellipse2D.Double hr = new Ellipse2D.Double(300+170*Math.cos(i*(Math.PI/30))-x, 300+170*Math.sin(i*(Math.PI / 30))-x, 8, 8);
				g2.setColor(Color.red);
				g2.fill(hr);
				g2.draw(hr);
			}
			else { //second/min marks
				x = 2;
				Ellipse2D.Double sec = new Ellipse2D.Double(300+170*Math.cos(i*(Math.PI/30))-x, 300+170*Math.sin(i*(Math.PI / 30))-x, 4, 4);
				g2.setColor(randC);
				g2.fill(sec);
				g2.draw(sec);
			}
		}
	}
}
