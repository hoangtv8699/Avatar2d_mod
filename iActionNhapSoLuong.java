
public class iActionNhapSoLuong implements ii {
	private int idNPC;
	private int index;
	private int pos;

	public iActionNhapSoLuong(int idNPC, int index, int pos) {
		super();
		this.idNPC = idNPC;
		this.index = index;
		this.pos = pos;
	}

	public void a() {
		String text = main.a.u.a();

		if (text == null || text.length() == 0) {
			main.a.b("vui long nhap so luong");
			return;
		}

		try {
			int number = Integer.parseInt(text);

			for (int i = 0; i < number; i++) {
				bf.a().c(this.idNPC, this.index, this.pos);
				sleep(1);
			}
			main.a.b("da xong");
		} catch (NumberFormatException e) {
			main.a.b("so luong khong hop le");
		}
	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
