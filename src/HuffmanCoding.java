import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanCoding
{
	static Node huffman(PriorityQueue<Node> priorityQueue)
	{
		int n = priorityQueue.size();
		for (int i = 1; i < n; i++)
		{
			Node z = new Node();
			z.left = priorityQueue.poll();
			z.right = priorityQueue.poll();
			// System.out.println("i = " + i);
			// System.out.println("z.left = " + z.left.getKey() + ", " + z.left.getFreq());
			// System.out.println("z.right = " + z.right.getKey() + ", " + z.right.getFreq());
			z.setFreq(z.left.getFreq() + z.right.getFreq());
			priorityQueue.add(z);
		}
		return priorityQueue.poll();    // return the root of the tree
	}

	static void extractCode(Node n, StringBuilder s, HashMap<List<Byte>, Byte[]> hashMap)
	{
		if (n.left == null && n.right == null && n.getKey() != null)
		{
			//System.out.println(n.getKey() + "\t\t\t" + s);
			// https://stackoverflow.com/questions/17727310/convert-binary-string-to-byte-array
			byte[] bval = new BigInteger(s.toString(), 2).toByteArray();
			Byte[] byteObjects = new Byte[bval.length];
			int i = 0;
			for (byte b : bval)
				byteObjects[i++] = b;  // Autoboxing.

			hashMap.put(n.getKey(), byteObjects);
			return;
		}
		extractCode(n.left, s.append('0'), hashMap);
		s.deleteCharAt(s.length() - 1);
		extractCode(n.right, s.append('1'), hashMap);
		s.deleteCharAt(s.length() - 1);
	}

	static void printCodes(HashMap<List<Byte>, Byte[]> hashMap)
	{
		System.out.println("Key\t\tCode");
		for (List<Byte> b : hashMap.keySet())
			System.out.println(b.toString() + "        " + Arrays.toString(hashMap.get(b)));
	}
}
