package re.ta;
import java.util.Timer;
import java.util.TimerTask;

public class MasterClock{

	private long time;
	MasterClock(){
		init();
	}

	private void init(){
		time = 0;

		TimerTask tt = new TimerTask(){
			@Override
			public void run(){
				System.out.println(time);
				time++;
			}
		};

		Timer timer = new Timer("MasterClock");
		timer.scheduleAtFixedRate(tt,0,100);
	}
}