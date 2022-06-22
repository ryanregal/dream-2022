package application;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class TextController {

    @FXML
    private RadioButton differentCountButton;
    @FXML
    private RadioButton showMostButton;
    @FXML
    private RadioButton allCountButton;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea showTextArea;
    @FXML
    private RadioButton showAllButton;
    @FXML
    private Button saveButton;
    @FXML private ToggleGroup tg;

	//初始化
	public void initialize() 
	{
	    tg = new ToggleGroup();
	    //单选按钮
	    differentCountButton.setToggleGroup(tg);
	    showMostButton.setToggleGroup(tg);
	    allCountButton.setToggleGroup(tg);
	    showAllButton.setToggleGroup(tg);
	}
    

    //统计不同单词的数量（不区分大小写）
    @FXML
    void differentCountButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	int differTimes = 0;
    	String line=inputTextArea.getText().toUpperCase();//不区分大小写
    	//建立一个String和Integer键值的哈希表
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//使用Pattern.compile函数来实现对指定字符串的截取
		//生成Pattern对象p并且编译正则表达式
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}']");//

		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //用Pattern类的matcher()方法生成一个Matcher对象
			Matcher m= p.matcher(lineWords[i]);
			//将这些特殊字符都删掉
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				//如果不为空，且没有重复，则不同单词数量加一。
				if(!hashMap.containsKey(lineWords[i])){
					hashMap.put(lineWords[i], 1);
					differTimes++;
				}
			}	
		}
		showTextArea.setText("不同单词数量为："+differTimes);
    }
    
    //统计所有单词的数量
    @FXML
    void allCountButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	String line=inputTextArea.getText();
    	int times=0;
		//使用Pattern.compile函数来实现对指定字符串的截取
		//生成Pattern对象p并且编译正则表达式
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}']");//
		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //用Pattern类的matcher()方法生成一个Matcher对象
			Matcher m= p.matcher(lineWords[i]);
			//将这些特殊字符都删掉
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				times++;
			}	
		}
		showTextArea.setText("所有单词数量为："+times);
    }
    
    //按照首字母分组显示所有单词及其出现次数
    @FXML
    void showAllButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	String line=inputTextArea.getText();
    	//建立一个String和Integer键值的哈希表
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//使用Pattern.compile函数来实现对指定字符串的截取
		//生成Pattern对象p并且编译正则表达式
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}']");//

		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //用Pattern类的matcher()方法生成一个Matcher对象
			Matcher m= p.matcher(lineWords[i]);
			//将这些特殊字符都删掉
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				//如果不为空，则将该单词加入到哈希表的key中，对应为1
				if(!hashMap.containsKey(lineWords[i])){
					hashMap.put(lineWords[i], 1);
				}
				else{
					int times = hashMap.get(lineWords[i]);
					times++;//记录单词出现了几次
					hashMap.put(lineWords[i], times);
				}
			}	
		}
		//按照字母排序排序输出
		//entrySet返回映射中包含的映射的集合视图。集合由映射支持，因此对映射的更改会反映在集合中，反之亦然。
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
			{
				return o1.getKey().toUpperCase().compareTo(o2.getKey().toUpperCase());//重写，哈希表是比较字母
			}
		});
		
		int[] a= new int[26];
		String output = "";
		for(Map.Entry<String, Integer> entry : list)
		{
			//使用keySet()方法获取所有的key值
			char letter=Character.toUpperCase(entry.getKey().charAt(0));//不区分大小写
			if(a[letter-'A']==0) {
				output=String.join("", output,"\n",String.valueOf(letter),":\n");
				a[letter-'A']=1;
			}
			output=String.join("", output,entry.getKey()+":"+entry.getValue()+"\n" );
		}	
		showTextArea.setText(output);
	}
    	
    //频率最高20个单词及其次数
    @FXML
    void showMostButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	String line=inputTextArea.getText();
    	//建立一个String和Integer键值的哈希表
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//使用Pattern.compile函数来实现对指定字符串的截取
		//生成Pattern对象p并且编译正则表达式
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}']");//

		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //用Pattern类的matcher()方法生成一个Matcher对象
			Matcher m= p.matcher(lineWords[i]);
			//将这些特殊字符都删掉
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				//如果不为空，则将该单词加入到哈希表的key中，对应为1
				if(!hashMap.containsKey(lineWords[i])){
					hashMap.put(lineWords[i], 1);
				}
				else
				{
					int times = hashMap.get(lineWords[i]);
					times++;//记录单词出现了几次
					hashMap.put(lineWords[i], times);
				}
			}	
		}
		//按照频率排序输出
		//entrySet返回映射中包含的映射的集合视图。集合由映射支持，因此对映射的更改会反映在集合中，反之亦然。
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
			{
				return o2.getValue().compareTo(o1.getValue());//重写，哈希表是比较频率
			}
		});
		int count=0;
		String output="";
		for(Map.Entry<String, Integer> entry : list)
		{
			output=String.join("", output,entry.getKey()+":"+entry.getValue()+"\n");
			count++;
			if(count>=20) break;
		}	
		showTextArea.setText(output);
    }
    
    //保存分析统计结果
    @FXML
    void saveButtonClick(ActionEvent event) {
		FileChooser chooser = new FileChooser();
        chooser.setTitle("保存");
        chooser.setInitialDirectory(new File("C:\\"));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("文本文件(*.txt)", "*.txt");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("所有文件(*.*)", "*.*");
        chooser.getExtensionFilters().add(filter);
        chooser.getExtensionFilters().add(filter2);
 
        if(showTextArea.getText().isEmpty()) {
        	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("警告");
             alert.setHeaderText("空文件无法保存");
             alert.setContentText("");
	         alert.showAndWait();
	         return;
        }
        else {
	        File file = chooser.showSaveDialog(null);
	        if (file == null) {
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("警告");
	            alert.setHeaderText("当前文件未保存");
	            alert.setContentText("");
	            alert.showAndWait();
	        }
	        else{
	        	FileWriter fw = null;
	        	try
	        	{
	        		fw = new FileWriter(file);
	        		fw.write(showTextArea.getText());
	        		fw.flush();
	        	}
	        	catch(Exception e){}
	        	finally{
	        		try{
	        			if(fw != null) fw.close();
	        		}
	        		catch(Exception e){}
	        	}
	        }
        }
    }
}
