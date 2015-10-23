package vnp.mr.mintmedical.s4;

public class S4ItemDataConfig {
	public static final int TYPEHISTORY = 2;
	public static final int TYPEUPCOMMING = 1;

	public S4ItemDataConfig(int i, int size) {
		type = i;
		this.size = size;
	}

	/**
	 * type == 0, upcomint type =1, history
	 */
	public int type = TYPEUPCOMMING;
	public int size = 0;

}