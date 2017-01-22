// 网页预处理

package workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html2Docs {
	/**
	 * 在文本文件中分离每篇文档
	 */
	public List<String> matchDoc(String source){
		List<String> result = new ArrayList<String>();
		String reg = "<doc[ ^>]*?>([\\s\\S]*?)</doc>";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(source);
		while (m.find()){
			String r = m.group(1);
			result.add(r);
		}
		return result;
	}
	
	
	/**
	 * 在buffer中匹配网址信息 以及title
	 */
	public String matchLabel(String buffer, String label){
		String NOT_EXIST = "不存在";
		String patternStr;
		if (label == "url"){
			/**
			 * 在doc中匹配网址信息
			 */
			patternStr = "url=(.*)</url>";
		}
		else if(label == "title"){
			/**
			 * 在doc中匹配title信息
			 */
			patternStr = "<title>(.*)</title>";
		}
		else return NOT_EXIST;

		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.find()) return matcher.group(1);
		return NOT_EXIST;
	}
	
	
	/**
	 * 在buffer中匹配新闻内容
	 */
	public String getcontent(String buffer) {
		/**
		 * 在doc中匹配content
		 */
		String NOT_EXIST = "不存在";
		String patternStr = "\\n([^<]+)";
		
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.find()) return matcher.group(1);
		return NOT_EXIST;
	}
	
	
	/**
	 * 在buffer中匹配description keywords publish id 以及subject id
	 */
	public String match(String buffer, String label) {
		String NOT_EXIST = "不存在";
		String[] labelsWithQuote = {"publishid", "subjectid"};
		String[] labelsWithoutQuote = {"description", "keywords"};
		String patternStr;

		for (String item : labelsWithQuote) {
			if (label.equals(item)) {
				patternStr = "<meta\\s*name=\"" + item + "\"\\s+content=\"(.*?)\"\\s*/?>";
				Pattern pattern = Pattern.compile(patternStr);
				Matcher matcher = pattern.matcher(buffer);
				if (matcher.find()) return matcher.group(1);
				else return NOT_EXIST;
			}
		}
		for (String item : labelsWithoutQuote) {
			if (label.equals(item)) {
				patternStr = "<meta\\s*name=" + item + "\\s+content=\"(.*?)\"\\s*/?>";
				Pattern pattern = Pattern.compile(patternStr);
				Matcher matcher = pattern.matcher(buffer);
				if (matcher.find()) return matcher.group(1);
				else return NOT_EXIST;
			}
		}
		return NOT_EXIST;
	}
}
