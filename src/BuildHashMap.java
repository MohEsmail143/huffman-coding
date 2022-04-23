// source for counting frequency: https://www.geeksforgeeks.org/counting-frequencies-of-array-elements/
// source for print map

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BuildHashMap
{
	static void printMap(HashMap<List<Byte>, Integer> map)
	{
		System.out.println("Key\t\tFrequency");
		for (List<Byte> b : map.keySet())
		{
			String key = b.toString();
			String freq = map.get(b).toString();
			System.out.println(key + "        " + freq);
		}
	}

	public static HashMap<Byte, Integer> getFrequencies(byte[] byteArray)
	{
		int n = byteArray.length;
		HashMap<Byte, Integer> byteFrequency = new HashMap<>();
		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false);
		for (int i = 0; i < n; i++)
		{
			if (visited[i]) continue;
			int count = 1;
			for (int j = i + 1; j < n; j++)
			{
				if (byteArray[i] == byteArray[j])
				{
					visited[j] = true;
					count++;
				}
			}
			byteFrequency.put(byteArray[i], count);
		}
		return byteFrequency;
	}
}
