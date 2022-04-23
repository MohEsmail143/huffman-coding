package backup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HuffDecompress
{
	static void decompressFile(String compressedFile) throws IOException, ClassNotFoundException
	{
		long startTime, endTime;
		String extractedFile = "extracted." + compressedFile;
		startTime = System.currentTimeMillis();
		// System.out.println("decompressing...");
		FileInputStream fis = new FileInputStream(compressedFile);
		FileOutputStream fos = new FileOutputStream(extractedFile);
		ObjectInputStream ois = new ObjectInputStream(fis);

		int n = (int) ois.readObject();
		HashMap<List<Byte>, Byte[]> transMap_infile = (HashMap<List<Byte>, Byte[]>) ois.readObject();
		HashMap<String, List<Byte>> reversedMap = new HashMap<>();
		for (List<Byte> b : transMap_infile.keySet())
			reversedMap.put(Arrays.toString(transMap_infile.get(b)), b);
		//for (String s : reversedMap.keySet())
		//System.out.println(s + "\t" + reversedMap.get(s));

		//System.out.println("n = " + n);
		//System.out.println("Reading from " + compressedFile);
		//HuffmanCoding.printCodes(transMap_infile);

		byte[] fileBytes = new byte[8192];
		int read = fis.read(fileBytes);
		while (read > 0)
		{
			for (int i = 0; i < fileBytes.length && i < read; i++)
			{
				String s = "[" + fileBytes[i] + "]";
				byte[] charArray = new byte[reversedMap.get(s).size()];
				for (int j = 0; j < reversedMap.size(); j++)
				{
					if (reversedMap.containsKey(s))
					{
						//System.out.println("i = " + i + ", " + reversedMap.get(s));
						for (int k = 0; k < reversedMap.get(s).size(); k++)
						{
							charArray[k] = reversedMap.get(s).get(k);
							//System.out.println("charArray[" + k + "] = " + charArray[k]);
						}
					}
				}
				fos.write(charArray);
			}
			read = fis.read(fileBytes);
		}
		ois.close();
		fis.close();
		endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Decompression time = " + ((endTime - startTime) / 1000) + " s");
	}
}
