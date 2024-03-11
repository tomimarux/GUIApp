import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PathUtils{
	public static void main(String[] args){
		String pathName = "MhtmlFile.csv";
		Charset charset = Charset.forName("Windows-31J");
		List<String> lines = readAllLines(Paths.get(pathName),charset);
		for(int i = 0; i < lines.size(); i++){
			String line = lines.get(i);
			System.out.println((i + 1) + ":" + line);
		}
	}
	public static final String CRLF = "\r\n";
	//キャリッジ・リターン(carriage return),ライン・フィード(line feed)
	public static List<String> readAllLines(Path path,Charset charset){
		String all = readAll(path,charset);
		return Arrays.asList(all.split(CRLF));
	}
	
	public static String readAll(Path path,Charset charset){
		try{
			return new String(Files.readAllBytes(path),charset);
		}catch(IOException e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static Path writeString(Path path, String all, Charset charset){
		try{
			return Files.write(path, all.getBytes(charset));
		}catch(IOException e){
			e.printStackTrace();
		}
		return path;
	}
}