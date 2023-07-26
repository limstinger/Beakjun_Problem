import java.io.*;
import java.util.*;
 
public class Main {

	static String up, down;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		up = br.readLine();
		down = br.readLine();
		System.out.println(find());
	}

	private static int find() {
		int min = up.length() + down.length();
		for (int i = -down.length(); i <= up.length(); i++) {
			boolean isPossible = true;
			for (int j = 0; j < up.length(); j++) {
				isPossible &= ((j - i < 0 || j - i >= down.length()) ? 0 : down.charAt(j - i)) + up.charAt(j) - '0'
						- '0' <= 3;
			}
			if (isPossible) {
				min = Math.min(min, Math.max(i + down.length(), up.length()) - Math.min(i, 0));
			}
		}
		return min;
	}
}