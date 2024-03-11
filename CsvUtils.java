import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CsvUtils{
	public static final Charset CHARSET = Charset.forName("Windows-31J");
	public static List<String> readAllLines(Path path){
		String all = readAll(path);
		return Arrays.asList(all.split(PathUtils.CRLF));
	}
	public static String readAll(Path path){
		return PathUtils.readAll(path, CHARSET);
	}
}