import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main
{
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		System.out.print("c or d: ");
		String mode = in.nextLine();
		if (mode.equals("c")) compress();
		else if (mode.equals("d")) decompress();
	}

	static void compress() throws IOException
	{
		System.out.print("File: ");

		String fileName = in.nextLine();
		System.out.print("n = ");
		int n = in.nextInt();
		long startTime, endTime;

		startTime = System.currentTimeMillis();
		HashMap<List<Byte>, Integer> freqMap = new HashMap<>();
		double originalFileSize = FileHandler.readAsBytes(n, fileName, freqMap);

		// HashMap<Byte, Integer> map = BuildHashMap.getFrequencies(byteArray);
		BuildHashMap.printMap(freqMap);
		PriorityQueue<Node> priorityQueue = BuildPriorityQueue.getHuffmanTree(freqMap);
		Node root = HuffmanCoding.huffman(priorityQueue);

		StringBuilder stringBuilder = new StringBuilder();
		HashMap<List<Byte>, Byte[]> transMap = new HashMap<>();
		HuffmanCoding.extractCode(root, stringBuilder, transMap);

		HuffmanCoding.printCodes(transMap);
		String compressedFile = "6525." + n + "." + fileName + ".hc";
		double compressedFileSize = HuffCompress.compressFile(n, fileName, compressedFile, transMap);
		endTime = System.currentTimeMillis();

		System.out.println("Compression ratio = " + (compressedFileSize / originalFileSize));
		System.out.println("Compression time = " + ((endTime - startTime) / 1000) + " s");
	}

	static void decompress() throws IOException, ClassNotFoundException
	{
		System.out.print("File: ");
		String fileName = in.nextLine();
		HuffDecompress.decompressFile(fileName);
	}
}
