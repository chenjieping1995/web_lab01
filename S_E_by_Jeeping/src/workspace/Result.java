package workspace;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义Result类，用于返回到jsp文件中的结果表示
 * @author jeeping
 */
public class Result {
	public List<String> contents = new ArrayList<String>();
    public List<String> urls = new ArrayList<String>();
    public List<String> titles = new ArrayList<String>();
    public List<String> publishTimes = new ArrayList<String>();
    public List<String> publishids = new ArrayList<String>(); // 新闻发布id
    public List<String> subjectids = new ArrayList<String>();  // 新闻主题id
    // 后期添加相似内容推荐
    public int totalDocs; //返回结果总数
}