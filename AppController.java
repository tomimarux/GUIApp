import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AppController implements ChildController{
	@FXML
	private Pane identity;

	@FXML
	private Label explainLabel;

	@FXML
	private TextField urlTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField fileNameTextField;

	@FXML
	private Button resetButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button errorSaveButton;
	@FXML
	private Button updateSaveButton;
	@FXML
	private Button newSaveButton;
	@FXML
    private Button openButton;
	@FXML
	private Button removeButton;
	@FXML
	private Button checkRemoveButton;
	@FXML
	private Button deleteDataButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button errorUpdateButton;
	@FXML
	private Button updateUpdateButton;
	@FXML
	private Button newUpdateButton;
	@FXML
	private Button webButton;

	@FXML
	private ListView<String> fileListView;

	private StageController parent;

	private Clipboard cb = Clipboard.getSystemClipboard();

	private List<MhtmlFile> fileList = new ArrayList<>();

	private Map<String, MhtmlFile> fileMap = new HashMap<>();

	private File folder = new File("save");

	private File selectedFile;

	private int errorCode;

	private int selectedIndex;
	
	@Override
	public Pane getIdentity(){
		return this.identity;
	}

	@Override
	public void init(){
		this.explainLabel.setText("保存したいURLを貼り付けてください");
		this.nameTextField.setText("");
		this.urlTextField.setText("");
		this.fileNameTextField.setText("");
		this.nameTextField.setVisible(true);
		this.urlTextField.setVisible(true);
		this.fileNameTextField.setVisible(false);
		this.saveButton.setVisible(true);
		this.errorSaveButton.setVisible(false);
		this.updateSaveButton.setVisible(false);
		this.newSaveButton.setVisible(false);
		this.openButton.setVisible(true);
		this.removeButton.setVisible(true);
		this.checkRemoveButton.setVisible(false);
		this.deleteDataButton.setVisible(false);
		this.updateButton.setVisible(true);
		this.errorUpdateButton.setVisible(false);
		this.updateUpdateButton.setVisible(false);
		this.newUpdateButton.setVisible(false);
		this.webButton.setVisible(true);
		this.fileList = ChangeCsv.sortCount(ChangeCsv.csvToList());
		this.fileMap = ChangeCsv.csvToMap();
		this.fileListView.getItems().clear();
		for(int i = 0; i < this.fileList.size(); i++){
            String strDate = new SimpleDateFormat("yyyy/MM/dd").format(this.fileList.get(i).getDate());
            this.fileListView.getItems().add(this.fileList.get(i).getName() + "　登録日 " + strDate + "　開いた回数 " + this.fileList.get(i).getOpenCounter());
        }
	}

	@Override
	public void setParent(StageController parent){
		this.parent = parent;
	}

	private int getResponseCode(String urlString) throws MalformedURLException, IOException{
        URL u = new URL(urlString);
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
        huc.setRequestMethod("GET");
        huc.connect(); 
        return huc.getResponseCode();
    }

	private void openURL(String url) throws URISyntaxException, IOException{
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(url));
    }

	private void saveFile() throws Exception{
        Robot robot = new Robot();
        robot.delay(3250);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        robot.delay(1000); //600 or 650
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_C);
		robot.delay(100);
        //保存先が決まっている場合
		/*robot.delay(400);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_W);*/
    }

	//
	private void openFile(File file) throws Exception{
		Desktop desktop = Desktop.getDesktop();
        if(file.exists()){
            desktop.open(file);
        }
    }

	private void changeErrorButton(){
		this.saveButton.setVisible(false);
		this.errorSaveButton.setVisible(true);
		this.updateSaveButton.setVisible(false);
		this.newSaveButton.setVisible(false);
		this.openButton.setVisible(false);
		this.removeButton.setVisible(false);
		this.checkRemoveButton.setVisible(false);
		this.deleteDataButton.setVisible(false);
		this.updateButton.setVisible(false);
		this.errorUpdateButton.setVisible(false);
		this.updateUpdateButton.setVisible(false);
		this.newUpdateButton.setVisible(false);
		this.webButton.setVisible(false);
	}

	private void changeSaveButton(){
		this.saveButton.setVisible(false);
		this.errorSaveButton.setVisible(false);
		this.updateSaveButton.setVisible(true);
		this.newSaveButton.setVisible(true);
		this.openButton.setVisible(false);
		this.removeButton.setVisible(false);
		this.checkRemoveButton.setVisible(false);
		this.deleteDataButton.setVisible(false);
		this.updateButton.setVisible(false);
		this.errorUpdateButton.setVisible(false);
		this.updateUpdateButton.setVisible(false);
		this.newUpdateButton.setVisible(false);
		this.webButton.setVisible(false);
		this.fileNameTextField.setVisible(true);
	}

	private void changeDeleteButton(){
		this.saveButton.setVisible(false);
		this.errorSaveButton.setVisible(false);
		this.updateSaveButton.setVisible(false);
		this.newSaveButton.setVisible(false);
		this.openButton.setVisible(false);
		this.removeButton.setVisible(false);
		this.checkRemoveButton.setVisible(false);
		this.deleteDataButton.setVisible(true);
		this.updateButton.setVisible(false);
		this.errorUpdateButton.setVisible(false);
		this.updateUpdateButton.setVisible(false);
		this.newUpdateButton.setVisible(false);
		this.webButton.setVisible(false);
	}

	private void changeCheckButton(){
		this.saveButton.setVisible(false);
		this.errorSaveButton.setVisible(false);
		this.updateSaveButton.setVisible(false);
		this.newSaveButton.setVisible(false);
		this.openButton.setVisible(false);
		this.removeButton.setVisible(false);
		this.checkRemoveButton.setVisible(true);
		this.deleteDataButton.setVisible(false);
		this.updateButton.setVisible(false);
		this.errorUpdateButton.setVisible(false);
		this.updateUpdateButton.setVisible(false);
		this.newUpdateButton.setVisible(false);
		this.webButton.setVisible(false);
	}

	private void changeErrorUpdateButton(){
		this.saveButton.setVisible(false);
		this.errorSaveButton.setVisible(false);
		this.updateSaveButton.setVisible(false);
		this.newSaveButton.setVisible(false);
		this.openButton.setVisible(false);
		this.removeButton.setVisible(false);
		this.checkRemoveButton.setVisible(false);
		this.deleteDataButton.setVisible(false);
		this.updateButton.setVisible(false);
		this.errorUpdateButton.setVisible(true);
		this.updateUpdateButton.setVisible(false);
		this.newUpdateButton.setVisible(false);
		this.webButton.setVisible(false);
	}

	private void changeNextUpdateButton(){
		this.saveButton.setVisible(false);
		this.errorSaveButton.setVisible(false);
		this.updateSaveButton.setVisible(false);
		this.newSaveButton.setVisible(false);
		this.openButton.setVisible(false);
		this.removeButton.setVisible(false);
		this.checkRemoveButton.setVisible(false);
		this.deleteDataButton.setVisible(false);
		this.updateButton.setVisible(false);
		this.errorUpdateButton.setVisible(false);
		this.updateUpdateButton.setVisible(true);
		this.newUpdateButton.setVisible(true);
		this.webButton.setVisible(false);
		this.nameTextField.setVisible(true);
		this.urlTextField.setVisible(false);
		this.fileNameTextField.setVisible(true);
	}

	@FXML
	public void resetAction(ActionEvent event){
		this.init();
	}

	@FXML
	public void saveAction(ActionEvent event){
		if(this.nameTextField.getText().equals("") && this.urlTextField.getText().equals("")){
			this.explainLabel.setText("名前とURLを入力してください");
			return;
		}else if(this.nameTextField.getText().equals("")){
			this.explainLabel.setText("名前を入力してください");
			return;
		}else if(this.urlTextField.getText().equals("")){
			this.explainLabel.setText("URLを入力してください");
			return;
		}

		try{
            this.errorCode = this.getResponseCode(this.urlTextField.getText());
        }catch(MalformedURLException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		if(!(this.errorCode == 200)){
			this.changeErrorButton();
			this.explainLabel.setText("エラーコード" + errorCode + "です。" + "保存する場合はもう一度ボタンを押してください。");
			return;
		}

		try{
			this.openURL(this.urlTextField.getText());
		}catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(URISyntaxException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		try{
			this.saveFile();
		}catch(Exception e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		if(this.fileMap == null || this.fileMap.isEmpty()){
			MhtmlFile addFile = new MhtmlFile(this.cb.getString(), this.urlTextField.getText(), this.nameTextField.getText(), 0, new Date());
			this.fileMap.put(addFile.getFileName(), addFile);
			ChangeCsv.mapToCsv(this.fileMap);
			this.init();
			return;
		}

		if(this.fileMap.containsKey(this.cb.getString())){
			this.changeSaveButton();
			this.explainLabel.setText("同じ名前のファイルが存在します。" + "重複保存する場合は変更後のファイル名を入力してください");
		}else{
			MhtmlFile addFile = new MhtmlFile(this.cb.getString(), this.urlTextField.getText(), this.nameTextField.getText(), 0, new Date());
			this.fileMap.put(addFile.getFileName(), addFile);
			ChangeCsv.mapToCsv(this.fileMap);
			this.init();
		}
	}

	@FXML
	public void errorSaveAction(ActionEvent event){
		try{
			this.openURL(this.urlTextField.getText());
		}catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(URISyntaxException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		try{
			this.saveFile();
		}catch(Exception e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		if(this.fileMap == null || this.fileMap.isEmpty()){
			MhtmlFile addFile = new MhtmlFile(this.cb.getString(), this.urlTextField.getText(), this.nameTextField.getText(), 0, new Date());
			this.fileMap.put(addFile.getFileName(), addFile);
			ChangeCsv.mapToCsv(this.fileMap);
			this.init();
			return;
		}

		if(this.fileMap.containsKey(this.cb.getString())){
			this.changeSaveButton();
			this.explainLabel.setText("同じ名前のファイルが存在します。" + "重複保存する場合は変更後のファイル名を入力してください");
		}else{
			MhtmlFile addFile = new MhtmlFile(this.cb.getString(), this.urlTextField.getText(), this.nameTextField.getText(), 0, new Date());
			this.fileMap.put(addFile.getFileName(), addFile);
			ChangeCsv.mapToCsv(this.fileMap);
			this.init();
		}
	}

	@FXML
	public void updateSaveAction(ActionEvent event){
		MhtmlFile addFile = new MhtmlFile(this.cb.getString(), this.urlTextField.getText(), this.nameTextField.getText(), 0, new Date());
		this.fileMap.put(addFile.getFileName(), addFile);
		ChangeCsv.mapToCsv(this.fileMap);
		this.init();
	}

	@FXML
	public void newSaveAction(ActionEvent event){
		if(this.fileNameTextField.getText().equals("")){
			this.explainLabel.setText("ファイル名を入力してください");
			return;
		}

		if(!(this.fileMap == null || this.fileMap.isEmpty())){
			if(this.fileMap.containsKey(this.fileNameTextField.getText())){
				this.explainLabel.setText("同じ名前のファイルが存在します");
				return;
			}
		}

		MhtmlFile addFile = new MhtmlFile(this.fileNameTextField.getText(), this.urlTextField.getText(), this.nameTextField.getText(), 0, new Date());
		this.fileMap.put(addFile.getFileName(), addFile);
		ChangeCsv.mapToCsv(this.fileMap);
		this.init();
	}

	@FXML
	public void openAction(ActionEvent event){
		if(this.fileListView.getSelectionModel().getSelectedIndex() == -1){
			this.explainLabel.setText("開きたいファイルを選択してください");
			return;
		}

		this.selectedIndex = this.fileListView.getSelectionModel().getSelectedIndex();
		this.selectedFile = new File("save/" + this.fileList.get(this.selectedIndex).getFileName());

		if(!(this.folder.exists())){
			if(this.folder.mkdir()){
				this.explainLabel.setText("セーブフォルダが存在しません。" + "セーブフォルダを作成しました");
			}else{
				this.explainLabel.setText("セーブフォルダが存在しません。" + "セーブフォルダの作成に失敗しました");
			}
			return;
        }else if(!(this.selectedFile.exists())){
			this.changeDeleteButton();
            this.explainLabel.setText("選択したファイルが存在しません。" + "データを削除する場合はボタンを押してください");
			return;
        }

		if(!(Desktop.isDesktopSupported())){
            this.explainLabel.setText("not supported");
            return;
        }

		try{
        	this.openFile(this.selectedFile);
		}catch(Exception e){
			e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
		}

        int openCount = this.fileList.get(this.selectedIndex).getOpenCounter() + 1;
        this.fileList.get(this.selectedIndex).setOpenCounter(openCount);
        ChangeCsv.listToCsv(this.fileList);
        this.init();
	}

	@FXML
	public void removeAction(ActionEvent event){
		if(this.fileListView.getSelectionModel().getSelectedIndex() == -1){
			this.explainLabel.setText("削除したいファイルを選択してください");
			return;
		}

		this.selectedIndex = this.fileListView.getSelectionModel().getSelectedIndex();
		this.selectedFile = new File("save/" + this.fileList.get(this.selectedIndex).getFileName());

		if(!(this.folder.exists())){
			if(this.folder.mkdir()){
				this.explainLabel.setText("セーブフォルダが存在しません。" + "セーブフォルダを作成しました");
			}else{
				this.explainLabel.setText("セーブフォルダが存在しません。" + "セーブフォルダの作成に失敗しました");
			}
			return;
        }else if(!(this.selectedFile.exists())){
			this.changeDeleteButton();
            this.explainLabel.setText("選択したファイルが存在しません。" + "データを削除する場合はボタンを押してください");
			return;
        }

		this.changeCheckButton();
		this.explainLabel.setText("削除する場合は確認ボタンを押してください");
	}

	@FXML
	public void checkRemoveAction(ActionEvent event){
		if(!(this.selectedFile.delete())){
			this.explainLabel.setText("ファイルの削除に失敗しました。ファイルが開かれていないか確認して下さい");
			return;
		}
		this.fileMap.remove(this.fileList.get(this.selectedIndex).getFileName());
		ChangeCsv.mapToCsv(this.fileMap);
		this.init();
	}

	@FXML
	public void deleteDataAction(ActionEvent event){
		this.fileMap.remove(this.fileList.get(this.selectedIndex).getFileName());
		ChangeCsv.mapToCsv(this.fileMap);
		this.init();
	}

	@FXML
	public void updateAction(ActionEvent event){
		if(this.fileListView.getSelectionModel().getSelectedIndex() == -1){
			this.explainLabel.setText("更新したいファイルを選択してください");
			return;
		}

		this.selectedIndex = this.fileListView.getSelectionModel().getSelectedIndex();
		this.selectedFile = new File("save/" + this.fileList.get(this.selectedIndex).getFileName());

		if(!(this.folder.exists())){
			if(this.folder.mkdir()){
				this.explainLabel.setText("セーブフォルダが存在しません。" + "セーブフォルダを作成しました");
			}else{
				this.explainLabel.setText("セーブフォルダが存在しません。" + "セーブフォルダの作成に失敗しました");
			}
			return;
        }else if(!(this.selectedFile.exists())){
			this.changeDeleteButton();
            this.explainLabel.setText("選択したファイルが存在しません。" + "データを削除する場合はボタンを押してください");
			return;
        }

		try{
            this.errorCode = this.getResponseCode(this.fileList.get(this.selectedIndex).getUrl());
        }catch(MalformedURLException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		if(!(this.errorCode == 200)){
			this.changeErrorUpdateButton();
			this.explainLabel.setText("エラーコード" + errorCode + "です。" + "更新する場合はもう一度ボタンを押してください");
			return;
		}

		try{
			this.openURL(this.fileList.get(this.selectedIndex).getUrl());
		}catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(URISyntaxException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		try{
			this.saveFile();
		}catch(Exception e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		this.changeNextUpdateButton();
		this.explainLabel.setText("重複保存する場合は変更後のファイル名を入力してください");
	}

	@FXML
	public void errorUpdateAction(ActionEvent event){
		try{
			this.openURL(this.fileList.get(this.selectedIndex).getUrl());
		}catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(URISyntaxException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		try{
			this.saveFile();
		}catch(Exception e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }

		this.changeNextUpdateButton();
		this.explainLabel.setText("重複保存する場合は変更後のファイル名を入力してください");
	}

	@FXML
	public void updateUpdateAction(ActionEvent event){
		MhtmlFile updateFile = this.fileList.get(this.selectedIndex);
		MhtmlFile addFile = new MhtmlFile(updateFile.getFileName(), updateFile.getUrl(), updateFile.getName(), 0, new Date());
		this.fileMap.put(addFile.getFileName(), addFile);
		ChangeCsv.mapToCsv(this.fileMap);
		this.init();
	}

	@FXML
	public void newUpdateAction(ActionEvent event){
		if(this.nameTextField.getText().equals("") && this.fileNameTextField.getText().equals("")){
			this.explainLabel.setText("名前とファイル名を入力してください");
			return;
		}else if(this.nameTextField.getText().equals("")){
			this.explainLabel.setText("名前を入力してください");
			return;
		}else if(this.fileNameTextField.getText().equals("")){
			this.explainLabel.setText("ファイル名を入力してください");
			return;
		}

		if(this.fileMap.containsKey(this.fileNameTextField.getText())){
			this.explainLabel.setText("同じ名前のファイルが存在します");
			return;
		}

		MhtmlFile updateFile = this.fileList.get(this.selectedIndex);
		MhtmlFile addFile = new MhtmlFile(this.fileNameTextField.getText(), updateFile.getUrl(), this.nameTextField.getText(), 0, new Date());
		this.fileMap.put(addFile.getFileName(), addFile);
		ChangeCsv.mapToCsv(this.fileMap);
		this.init();
	}

	@FXML
	public void webAction(ActionEvent event){
		if(this.fileListView.getSelectionModel().getSelectedIndex() == -1){
			this.explainLabel.setText("開きたいページを選択してください");
			return;
		}

		this.selectedIndex = this.fileListView.getSelectionModel().getSelectedIndex();

		try{
			this.openURL(this.fileList.get(this.selectedIndex).getUrl());
		}catch(IOException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }catch(URISyntaxException e){
            e.printStackTrace();
			this.explainLabel.setText("エラーが発生しました");
			return;
        }
	}

	@FXML
	public void getInformation(MouseEvent event){
		boolean doubleClicked = event.getButton().equals(MouseButton.PRIMARY) && (event.getClickCount() == 2);
        if(doubleClicked){
            this.selectedIndex = this.fileListView.getSelectionModel().getSelectedIndex();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(this.fileList.get(this.selectedIndex).getFileName());
            alert.setContentText("URL : " + this.fileList.get(this.selectedIndex).getUrl());
            alert.show();
            //クリップボードにURLコピー
            Map<DataFormat, Object> url = new HashMap<>();
            url.put(DataFormat.PLAIN_TEXT, this.fileList.get(this.selectedIndex).getUrl());
            cb.setContent(url);
        }
	}
}