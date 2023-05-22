
public class jb {
	public static jb a;
	private String j = "";
	private String k = "";

	public static jb b() {
		return a == null ? (a = new jb()) : a;
	}
	
	public String getK() {
		return this.k;
	}

	public String getJ() {
		return this.j;
	}
}
