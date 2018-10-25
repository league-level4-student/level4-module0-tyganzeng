import java.util.Random;

public class Checkpoint {
	public static void main(String[] args) {
		int[][] ints = new int[5][5];
		for(int i = 0; i < ints.length; i++) {
			for(int j = 0; j < ints[i].length; j++) {
				ints[i][j] = new Random().nextInt(100);
			}
		}
		for(int i = 0; i < ints.length; i++) {
			for(int j = 0; j < ints[i].length; j++) {
				System.out.print(ints[i][j] + " ");
			}
			System.out.println();
		}
	}
}
