import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeCsv{

	public static List<MhtmlFile> csvToList(){
		String pathName = "MhtmlFile.csv";
		List<String> list = CsvUtils.readAllLines(Paths.get(pathName));
		List<MhtmlFile> mhtmlFileList = toMhtmlFileList(list.subList(1, list.size()));
		return mhtmlFileList;
	}

	public static void listToCsv(List<MhtmlFile> mhtmlFileList){
		String pathName = "MhtmlFile.csv";
		String all = "filename," + "url," + "name," + "openCounter," + "date";
		Charset charset = Charset.forName("Windows-31J");
		for(MhtmlFile mhtmlFile : mhtmlFileList){
			String strDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(mhtmlFile.getDate());
			all = all + PathUtils.CRLF + mhtmlFile.getFileName() + "," + mhtmlFile.getUrl() + "," + mhtmlFile.getName() + "," + mhtmlFile.getOpenCounter() + "," + strDate;
		}
		//System.out.println(all);
		PathUtils.writeString(Paths.get(pathName), all, charset);
	}

	public static Map<String, MhtmlFile> listToMap(List<MhtmlFile> mhtmlFileList){
		Map<String, MhtmlFile> mhtmlFileMap = new HashMap<>(); 
		for(MhtmlFile mhtmlFile : mhtmlFileList){
			mhtmlFileMap.put(mhtmlFile.getFileName(), mhtmlFile);
		}
		return mhtmlFileMap;
	}

	public static List<MhtmlFile> mapToList(Map<String, MhtmlFile> mhtmlMap){
		List<MhtmlFile> mhtmlFileList = new ArrayList<>(mhtmlMap.values());
		return mhtmlFileList;
	}

	public static void mapToCsv(Map<String, MhtmlFile> fileMap){
		List<MhtmlFile> fileList = mapToList(fileMap);
		listToCsv(fileList);
	}

	public static Map<String, MhtmlFile> csvToMap(){
		List<MhtmlFile> fileList = csvToList();
		Map<String, MhtmlFile> fileMap = listToMap(fileList);
		return fileMap;
	}

	public static List<MhtmlFile> sortCount(List<MhtmlFile> mhtmlFileList){
		Map<String, MhtmlFile> mhtmlFileMap = listToMap(mhtmlFileList);
		List<Entry<String, MhtmlFile>> list_entries = new ArrayList<Entry<String, MhtmlFile>>(mhtmlFileMap.entrySet());
		Collections.sort(list_entries, new Comparator<Entry<String, MhtmlFile>>(){
			public int compare(Entry<String, MhtmlFile> obj1, Entry<String, MhtmlFile> obj2){
				return Integer.compare(obj2.getValue().getOpenCounter(), obj1.getValue().getOpenCounter());
			}
		});
		List<MhtmlFile> sortList = new ArrayList<MhtmlFile>();
		for(Entry<String, MhtmlFile> entry : list_entries){
			sortList.add(entry.getValue());
		}
		return sortList;
	}

	public static List<MhtmlFile> toMhtmlFileList(List<String> list){
		List<MhtmlFile> mhtmlFileList = new ArrayList<MhtmlFile>();
		for(String line : list){
			mhtmlFileList.add(newMhtmlFile(line));
		}
		return mhtmlFileList;
	}

	public static MhtmlFile newMhtmlFile(String line){
		String[] fields = line.split(",", -1);
		Date date = null;
		try{
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			date = sdFormat.parse(fields[4]);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return new MhtmlFile(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), date);
	}
}