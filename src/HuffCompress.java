import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuffCompress
{
	static double compressFile(int n, String originalFile, String newFile, HashMap<List<Byte>, Byte[]> hashMap) throws IOException
	{
		// System.out.println("compressing...");
		File outputFile = new File(newFile);
		outputFile.createNewFile();

		FileInputStream fis = new FileInputStream(originalFile);
		FileOutputStream fos = new FileOutputStream(newFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		double originalFileSize = Files.size(Paths.get(originalFile));

		oos.writeObject(n);
		oos.writeObject(hashMap);

		byte[] fileBytes;
		if (originalFileSize < 8192) fileBytes = new byte[(int) originalFileSize];
		else fileBytes = new byte[8192];
		int read = fis.read(fileBytes);
		while (read > 0)
		{
			for (int i = 0; i < fileBytes.length && i < read; )
			{
				List<Byte> byteBatch = new ArrayList<>();
				for (int j = 0; j < n && i < read; j++)
				{
					byteBatch.add(fileBytes[i]);
					i++;
				}
				// https://stackoverflow.com/questions/12944377/how-to-convert-byte-to-byte-and-the-other-way-around
				// byte[] bval = new BigInteger(hashMap.get(byteBatch), 2).toByteArray();
				Byte[] mapBytes = hashMap.get(byteBatch);
				byte[] bytes = new byte[mapBytes.length];
				int j = 0;
				// Unboxing Byte values. (Byte[] to byte[])
				for (Byte b : mapBytes)
					bytes[j++] = b;

				//System.out.println(bytes[0] + " , size = " + bytes.length);
				fos.write(bytes);
			}
			read = fis.read(fileBytes);
		}
		fis.close();
		fos.close();

		return Files.size(Paths.get(newFile));
	}
}
