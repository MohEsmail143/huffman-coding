import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class BuildPriorityQueue
{
	static PriorityQueue<Node> getHuffmanTree(HashMap<List<Byte>, Integer> map)
	{
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		for (List<Byte> b : map.keySet())
		{
			Node n = new Node(b, map.get(b));
			priorityQueue.add(n);
		}

		return priorityQueue;
	}
}
