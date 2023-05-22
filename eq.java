
public class eq implements ii{
	private ae mapScr;
	private int idNPC;
	private int index;
	private int pos;
	public eq(ae mapScr, int idNPC, int index, int pos) {
		super();
		System.out.println("idNPC: " + idNPC);
		this.mapScr = mapScr;
		this.idNPC = idNPC;
		this.index = index;
		this.pos = pos;
	}
	
	public void a() {
		main.a.u.a("nhap so luong", new iActionNhapSoLuong(idNPC, index, pos), 1);
	}
	
}
