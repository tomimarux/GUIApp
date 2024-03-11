import java.util.Date;

public class MhtmlFile{
    private String fileName;
	private String url;
    private String name;
    private int openCounter;
    private Date date;

	public MhtmlFile (String fileName, String url, String name, int openCounter, Date date){
        this.fileName = fileName;
        this.url = url;
        this.name = name;
        this.openCounter = openCounter;
        this.date = date;
	}

    public String getFileName(){
        return this.fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getOpenCounter(){
        return this.openCounter;
    }

    public void setOpenCounter(int openCounter){
        this.openCounter = openCounter;
    }

    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date){
        this.date = date;
    }
}