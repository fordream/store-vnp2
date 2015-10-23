package vnp.mr.mintmedical.s6;

public class S6BMedicationConfig {
	public static final int PASTMEDICATION = 1;
	public static final int MEDICATIONLIST = 2;
	public static final int SENDREQUEST = 0;
	public int type = PASTMEDICATION;
	public int size = 0;
	public String text;
	public S6BMedicationConfig(int type, int size) {
		this.type = type;
		this.size = size;
		text = "No past medications";
		if(type == PASTMEDICATION) text = "No past medications";
	}
}