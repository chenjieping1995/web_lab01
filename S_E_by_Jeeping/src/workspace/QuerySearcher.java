// 查询类

package workspace;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class QuerySearcher {
	public Result search(String q, int maxResults) throws Exception {
		Result result = new Result();
		String indexDir = "D:\\outputIndex";	// 索引文件存放目录
		Directory directory=FSDirectory.open(Paths.get(indexDir)); // 得到索引文件所存储的目录
		IndexReader reader=DirectoryReader.open(directory);
		IndexSearcher is=new IndexSearcher(reader);	// 读索引
		Analyzer analyzer = new IKAnalyzer(); // 中文分词器
		String[] fields = { "title", "content", "keywords", "description"};
		QueryParser parser=new QueryParser(fields[0], analyzer);
		Query myQuery=parser.parse(q);
		TopDocs hits = is.search(myQuery, maxResults);
		// 高亮显示
		QueryScorer scorer = new QueryScorer(myQuery);
		Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");//高亮关键词
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
		highlighter.setTextFragmenter(fragmenter);
		for(ScoreDoc scoreDoc:hits.scoreDocs){
			Document doc=is.doc(scoreDoc.doc);
			String desc=doc.get("content");
			if(desc!=null){
				TokenStream tokenStream=analyzer.tokenStream("content", new StringReader(desc));
				result.contents.add(highlighter.getBestFragment(tokenStream, desc));
			}
			//result.contents.add(doc.get("content"));
			result.urls.add(doc.get("url"));
			result.titles.add(doc.get("title"));
			result.publishTimes.add(getTime(doc.get("url")));
			result.publishids.add(doc.get("publishid"));
			result.subjectids.add(doc.get("subjectid"));
			result.totalDocs++;
		}
		reader.close();
		return result;
	}
	
	/**
	 * 从doc的URL中提取新闻的发布时间
	 * @param url
	 * @return
	 */
	public String getTime(String url){
		String NOT_EXIST = "不存在";
		String patternStr;
		patternStr = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) return matcher.group(0);
		return NOT_EXIST;
	}
	
	/*
	 * Test Demo
	public static void main(String[] args) {
		String indexDir="D:\\outputIndex";
		String q="金正日";
		try {
			search(indexDir,q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
