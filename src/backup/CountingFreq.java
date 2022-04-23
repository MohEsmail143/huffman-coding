package backup;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CountingFreq
{
	void makeMap(String[] args) throws IOException
	{
		System.out.print("Read file: ");
		Scanner in = new Scanner(System.in);
		String filename = in.nextLine();
		HashMap<List<Byte>, Integer> map = new HashMap<>();
		// byte[] byteStream = FileHandler.readAsBytes(filename, map);
		// HashMap<Byte, Integer> map = new HashMap<>();
		// for (int i = 0; i < byteStream.length; i++)
		// {
		// 	if (map.containsKey(byteStream[i])) map.put(byteStream[i], map.get(byteStream[i]) + 1);
		// 	else map.put(byteStream[i], 1);
		// }
		System.out.println("Printing frequencies:");
		//BuildHashMap.printMap(map);
	}
}
