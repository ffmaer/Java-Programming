import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/******************************************************************
 * * * The FileStringReader Class * * *
 ******************************************************************/

public class FileStringReader {
	FileInputStream in;
	BufferedReader reader;

	public FileStringReader(String s) {
		try {
			File f1 = new File(s);
			in = new FileInputStream(f1);
			InputStreamReader inStream = new InputStreamReader(in);
			reader = new BufferedReader(inStream);
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}

	public String readLine() {
		String inputLine = "";
		try {
			inputLine = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		return inputLine;
	}

	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
}