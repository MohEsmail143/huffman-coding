// https://stackoverflow.com/questions/39399398/java-reading-large-files-into-byte-array-chunk-by-chunk/39399613

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHandler
{
	static double readAsBytes(int n, String filename, HashMap<List<Byte>, Integer> hashMap) throws IOException
	{
		FileInputStream fis = new FileInputStream(filename);
		double fileSize = Files.size(Paths.get(filename));
		//System.out.println("fileSize = " + fileSize + " B");
		byte[] fileBytes;
		if (fileSize < 8192) fileBytes = new byte[(int) fileSize];
		else fileBytes = new byte[8192];

		int read = fis.read(fileBytes);
		while (read > 0)
		{
			for (int i = 0; i < fileBytes.length && i < read; )
			{
				List<Byte> byteBatch = new ArrayList<>();
				for (int j = 0; j < n && i < read; j++)
					byteBatch.add(fileBytes[i++]);

				if (hashMap.containsKey(byteBatch)) hashMap.put(byteBatch, hashMap.get(byteBatch) + 1);
				else hashMap.put(byteBatch, 1);
			}
			read = fis.read(fileBytes);
		}
		fis.close();
		return fileSize;
	}
}
