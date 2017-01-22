// 网页预处理 & 建立索引

package workspace;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class CreateIndex {
	
	/**
	 * 将文本读入，并且转化为以每个doc为单位的字符串数组输出
	 * @return
	 * @throws Exception
	 */
	public static List<String> txt2String () throws IOException {
		String buffer1, buffer2, buffer3;
		buffer1 = Txt2String.readFile("d:\\SinaNews\\2012.q1.txt") + Txt2String.readFile("d:\\SinaNews\\2012.q2.txt");
		buffer2 = Txt2String.readFile("d:\\SinaNews\\2012.q3.txt") + Txt2String.readFile("d:\\SinaNews\\2012.q4.txt");
		buffer3 = Txt2String.readFile("d:\\SinaNews\\2013.q1.txt");
		
		Html2Docs retyper = new Html2Docs();
		// List<String> 是由一个个doc的内容文本组成的String集合
		List<String> temp = retyper.matchDoc(buffer1);
		temp.addAll(retyper.matchDoc(buffer2));
		temp.addAll(retyper.matchDoc(buffer3));
		
		return temp;
	}
	
	
	/**
	 * 对传入的doc字符串数组处理，转化为Doc类，并完成网页数据预处理
	 * @param temp
	 * @return
	 */
	public static Doc[] string2Docs (List<String> temp) {
		int docsNumber1 = temp.size();
		Doc []docs = new Doc[docsNumber1];
		Html2Docs retyper = new Html2Docs();
		for (int i = 0; i < docsNumber1; i++) {
			docs[i] = new Doc();
		    docs[i].buffer = temp.get(i);
		    docs[i].url = retyper.matchLabel(docs[i].buffer, "url");
		    docs[i].description = retyper.match(docs[i].buffer, "description");
		    docs[i].keyword = retyper.match(docs[i].buffer, "keywords");
		    docs[i].title = retyper.matchLabel(docs[i].buffer, "title");
		    docs[i].publishid = retyper.match(docs[i].buffer, "publishid");
		    docs[i].subjectid = retyper.match(docs[i].buffer, "subjectid");
		    docs[i].content = retyper.getcontent(docs[i].buffer);
		}
		return docs;
	}
	
	/**
	 * 由传入的doc对象组成的数组建立索引
	 * @param docs
	 * @throws Exception
	 */
	public static void docs2Index (Doc[] docs) throws Exception {
		int docsNumber1 = docs.length;
		String indexDir = "D:\\outputIndex";
		Indexer index = new Indexer(indexDir);
		try{
			// 每个document对应一篇文档
			Document []document = new Document[docsNumber1];
			// 写索引
			for (int i = 0; i < docsNumber1; i++) {
				document[i] = new Document();
				document[i].add(new Field("url", docs[i].url, StringField.TYPE_STORED));
				document[i].add(new Field("description", docs[i].description, TextField.TYPE_STORED));
				document[i].add(new Field("keywords", docs[i].keyword, TextField.TYPE_STORED));
				document[i].add(new Field("title", docs[i].title, TextField.TYPE_STORED));
				document[i].add(new Field("publishid", docs[i].publishid, StringField.TYPE_STORED));
				document[i].add(new Field("subjectid", docs[i].subjectid, StringField.TYPE_STORED));
				document[i].add(new Field("content", docs[i].content, TextField.TYPE_STORED));
				index.writer.addDocument(document[i]);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				index.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 主方法，调用以上的方法，完成由读取文件内容到建立索引的过程
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 需要改以上方法为静态方法，然而改成静态后则爆栈
		docs2Index(string2Docs(txt2String()));
	}
}