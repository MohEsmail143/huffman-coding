// adapted from:
// https://stackoverflow.com/questions/29872664/add-key-and-value-into-an-priority-queue-and-sort-by-key-in-java

import java.util.List;

public class Node implements Comparable<Node>
{
	private final List<Byte> key;
	Node left;
	Node right;
	private Integer freq;

	public Node()
	{
		this.key = null;
		this.freq = 0;
		this.left = null;
		this.right = null;
	}

	public Node(List<Byte> key, Integer freq)
	{
		this.key = key;
		this.freq = freq;
		left = null;
		right = null;
	}

	public Integer getFreq()
	{
		return freq;
	}

	public void setFreq(Integer freq)
	{
		this.freq = freq;
	}

	public List<Byte> getKey()
	{
		return key;
	}

	@Override
	public int compareTo(Node other)
	{
		return this.getFreq().compareTo(other.getFreq());
	}
}
