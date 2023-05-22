public class AutoTaiXiu implements ii, Runnable {
	public static boolean autoActive = false;
	public long initCuoc = 1000;
	public long tienCuocHienTai;
	public int countLoose = 0;
	public float ratio = (float) 2.1;
	public int maxLose = 20;
	public final long MAXCUOC = 30000000;
	public final long MINCUOC = 1000;
	public int countTai;
	public int countXiu;
	public boolean lastChoose = false;
	public boolean lastWin = true;

	public void a() {
		System.out.println("chọn auto tài xỉu, nhập tham số");
		if (!autoActive) {
			try {
				System.out.println("bật auto tài xỉu");
				String inputString = main.a.u.a();
				System.out.println("input: " + inputString);
				String[] inputWords = split(inputString, " ");

				for (int i = 0; i < 3; i++) {
					System.out.println(inputWords[i]);
				}

				long Cuoc = Long.parseLong(inputWords[0]);
				if (Cuoc > this.MAXCUOC) {
					main.a.b("tiền cược quá lớn, vui lòng nhập lại");
					return;
				} else if (Cuoc < this.MINCUOC) {
					main.a.b("tiền cược quá nhỏ, vui lòng nhập lại");
					return;
				}

				float Nhan = Float.parseFloat(inputWords[1]);
				if (Nhan > 3 || Nhan < 2) {
					main.a.b("lớn hơn 2 và nhỏ hơn 3 đi mày");
					return;
				}

				int ToiDa = Integer.parseInt(inputWords[2]);
				if (ToiDa > 20 || ToiDa < 1) {
					main.a.b("lớn hơn 1 và nhỏ hơn 20 đi mày");
					return;
				}

				this.initCuoc = Cuoc;
				this.ratio = Nhan;
				this.maxLose = ToiDa;
				this.countTai = 0;
				this.countXiu = 0;

				System.out.println(this.initCuoc + " " + this.ratio + " " + this.maxLose);

			} catch (NumberFormatException e) {
				main.a.b("nhập sai r đm");
			}
			main.a.b("bật auto tài xỉu");
		} else {
			main.a.b("tắt auto tài xỉu");
		}
		autoActive = !autoActive;
		new Thread(this).start();
	}

	public void run() {
		while (autoActive) {
			// auto cược tài đầu tiên
			datCuoc();
			sleep(65000);

			checkWin();

//			String thongBao = main.a.s.getD();
//			String[] lines = split(thongBao, "\r\n");
//
//			if (lines.length == 4) {
//				String[] words = split(lines[3], " ");
//				
//				System.out.println(words[3]);
//				if (words[3].equalsIgnoreCase("chiến")) {
//					this.lastWin = true;
//					if (this.lastChoose) {
//						this.countTai += 1;
//					} else {
//						this.countXiu += 1;
//					}
//				} else {
//					this.lastWin = false;
//					if (this.lastChoose) {
//						this.countXiu += 1;
//					} else {
//						this.countTai += 1;
//					}
//				}
//				System.out.println("tỉ lệ tài - xỉu: " + this.countTai + " - " + this.countXiu);
//			} else {
//				System.out.println("có lỗi: " + thongBao);
//			}
			sleep(4000);
		}
	}

	public void checkWin() {
		while (true) {
			cx.a().a(27, (byte) 0, 3);
			sleep(1000);
			String label = jb.b().getJ();
			String text = jb.b().getK();
			System.out.println(label);
			System.out.println(text);

			if (label.equalsIgnoreCase("Lịch sử")) {
				String vanCuoi = split(text, "\r\n")[10];
				String[] wordVanCuoi = split(vanCuoi, " ");
				String ketQua = wordVanCuoi[wordVanCuoi.length - 1];
				System.out.println(ketQua);

				if (ketQua.equalsIgnoreCase("Tài")) {
					this.lastWin = true;
					this.countTai += 1;
				} else {
					this.lastWin = false;
					this.countXiu += 1;
				}
				System.out.println("tỉ lệ tài - xỉu: " + this.countTai + " - " + this.countXiu);
				return;
			}
		}
	}

	public void datCuoc() {
		boolean pick = false;
		// true là tài, false là xỉu
		if (this.lastChoose == false && this.lastWin == true) {
			// reset tiền, đánh ngược lại
			this.countLoose = 0;
			this.tienCuocHienTai = this.initCuoc;
			System.out.println("tiền cược: " + this.tienCuocHienTai);
			cx.a().a(20, (byte) 1, String.valueOf(this.tienCuocHienTai));
			sleep(1000);

//			String chuaDenGio = "Thời gian đặt cược đã hết hoặc chưa đến";
			String thongBao = main.a.s.getD();
			thongBao = split(thongBao, " ")[0];
			System.out.println();
			while (thongBao.equalsIgnoreCase("Thời")) {
				System.out.println("đợi 2s");
				sleep(2000);
				cx.a().a(20, (byte) 1, String.valueOf(this.tienCuocHienTai));
				thongBao = main.a.s.getD();
				thongBao = split(thongBao, " ")[0];
			}
			pick = true;
		} else if (this.lastChoose == false && this.lastWin == false) {
			// gấp đôi đến chết
			this.countLoose += 1;
			this.tienCuocHienTai = (long) (this.tienCuocHienTai * this.ratio);

			if (this.tienCuocHienTai > this.MAXCUOC) {
				this.tienCuocHienTai = this.MAXCUOC;
			}
			System.out.println("tiền cược: " + this.tienCuocHienTai);
			cx.a().a(20, (byte) 2, String.valueOf(this.tienCuocHienTai));
			sleep(1000);

//			String chuaDenGio = "Thời gian đặt cược đã hết hoặc chưa đến";
			String thongBao = main.a.s.getD();
			thongBao = split(thongBao, " ")[0];
			System.out.println();
			while (thongBao.equalsIgnoreCase("Thời")) {
				System.out.println("đợi 2s");
				sleep(2000);
				cx.a().a(20, (byte) 2, String.valueOf(this.tienCuocHienTai));
				thongBao = main.a.s.getD();
				thongBao = split(thongBao, " ")[0];

			}
			pick = false;
		} else if (this.lastChoose == true && this.lastWin == true) {
			// reset tiền, đánh ngược lại
			this.countLoose = 0;
			this.tienCuocHienTai = this.initCuoc;
			System.out.println("tiền cược: " + this.tienCuocHienTai);
			cx.a().a(20, (byte) 2, String.valueOf(this.tienCuocHienTai));
			sleep(1000);

//			String chuaDenGio = "Thời gian đặt cược đã hết hoặc chưa đến";
			String thongBao = main.a.s.getD();
			thongBao = split(thongBao, " ")[0];
			System.out.println();
			while (thongBao.equalsIgnoreCase("Thời")) {
				System.out.println("đợi 2s");
				sleep(2000);
				cx.a().a(20, (byte) 2, String.valueOf(this.tienCuocHienTai));
				thongBao = main.a.s.getD();
				thongBao = split(thongBao, " ")[0];

			}
			pick = false;
		} else if (this.lastChoose == true && this.lastWin == false) {
			// gấp đôi đến chết
			this.countLoose += 1;
			this.tienCuocHienTai = (long) (this.tienCuocHienTai * this.ratio);

			if (this.tienCuocHienTai > this.MAXCUOC) {
				this.tienCuocHienTai = this.MAXCUOC;
			}

			System.out.println("tiền cược: " + this.tienCuocHienTai);
			cx.a().a(20, (byte) 1, String.valueOf(this.tienCuocHienTai));
			sleep(1000);

//			String chuaDenGio = "Thời gian đặt cược đã hết hoặc chưa đến";
			String thongBao = main.a.s.getD();
			thongBao = split(thongBao, " ")[0];
			System.out.println();
			while (thongBao.equalsIgnoreCase("Thời")) {
				System.out.println("đợi 2s");
				sleep(2000);
				cx.a().a(20, (byte) 1, String.valueOf(this.tienCuocHienTai));
				thongBao = main.a.s.getD();
				thongBao = split(thongBao, " ")[0];

			}
			pick = true;
		}
		this.lastChoose = pick;
	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] split(String str, String delimiter) {

		// Iterate through the text to get the count of occurences
		// This is done since we do not know how many times the
		// delimitter occurs in the given string
		// --------------------------------------------------//

		int delimiterCount = 0;
		int index;
		String tmpStr = str;

		String[] splittedList;

		while ((index = tmpStr.indexOf(delimiter)) != -1) {

			tmpStr = tmpStr.substring(index + delimiter.length());
			delimiterCount++;
		}

		splittedList = new String[delimiterCount + 1];

		/*-----------------------------------------------*/

		/*-----------------------------------------------*/

		// -----------------------------------------------//
		index = 0;
		int counter = 0;
		tmpStr = str;

		while ((index = tmpStr.indexOf(delimiter)) != -1) {
			splittedList[counter] = tmpStr.substring(0, index);
			tmpStr = tmpStr.substring(index + delimiter.length());
			counter += 1;
		}
		splittedList[counter] = tmpStr;

		return splittedList;
	}

}
