
public class AutoChat implements ii, Runnable {
	public static boolean chatActive;
	
	public void a() {
		chatActive = !chatActive;
		new Thread(this).start();
	}
	
	public void run() {
		while(chatActive) {
			bf.a().a("hiddenpants");
			sleep(5000);
		}
	}
	
	private void sleep (long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
