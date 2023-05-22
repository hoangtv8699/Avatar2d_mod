import java.util.Vector;

public class InitChat {
	public static final int LEFT = 0;
	public static final int CENTER = 2;
	public static final int RIGHT = 1;
	
	
	public static void init(String chat) {
		if(chat.equalsIgnoreCase("menu")) {
			Vector menu = new Vector();
			menu.addElement(new by("tien ich", 1));
			menu.addElement(new by("menu vip", 2));
			menu.addElement(new by("auto tai xiu", new actionAutoTaiXiu()));
			menu.addElement(new by("auto chat", new AutoChat()));
			menu.addElement(new by("ban quyen", 5));
			le.a().a(menu, RIGHT);
		}else {
			
		}
	}
}
