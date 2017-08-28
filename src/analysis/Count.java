package analysis;


public class Count {
	private final String token;
	private int count;
	
	public Count(String token) {
		this.token = token;
		this.count = 0;
	}
	
	public Count(String token, int count) {
		this.token = token;
		this.count = count;
	}
	
	public String getToken() {
		return token;
	}
	
	public int getCount() {
		return count;
	}
	
	public void countplus1() {
		count++;
	}
	
	public void print() {
		String output= token + ":" + count;
		System.out.println(output);

	}
}
