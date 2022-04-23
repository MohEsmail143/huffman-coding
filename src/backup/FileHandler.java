package backup;
// https://stackoverflow.com/questions/39399398/java-reading-large-files-into-byte-array-chunk-by-chunk/39399613

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler
{
	public static byte[] readAsBytes(String filename) throws IOException
	{
		try
		{
			File file = new File(filename);
			byte[] fileBytes = Files.readAllBytes(file.toPath());
			System.out.println("Successfully read file as bytes.");
			//System.out.println(Arrays.toString(fileBytes));
			return fileBytes;
		} catch (FileNotFoundException e)
		{
			System.out.println("Error Occurred");
			e.printStackTrace();
			return null;
		}
	}

	public static void writeFile(String filename, byte[] data) throws IOException
	{
		File outputFile = new File(filename);
		if (outputFile.createNewFile())
		{
			System.out.println("Successfully created file.");
		} else
		{
			System.out.println("File already exists");
		}

		try (FileOutputStream outputStream = new FileOutputStream(outputFile))
		{
			outputStream.write(data);
			System.out.println("Successfully wrote to file.");
		} catch (IOException e)
		{
			System.out.println("Error Occurred");
			e.printStackTrace();
		}
	}
}
