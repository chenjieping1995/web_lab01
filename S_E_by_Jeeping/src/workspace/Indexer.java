package workspace;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * 写索引的方法类
 * @author jeeping
 *
 */
public class Indexer {
	IndexWriter writer;
	/**
	 * 构造方法 实例化IndexWriter
	 * @param indexDir
	 * @throws Exception
	 */
	public Indexer(String indexDir)throws Exception{
		Directory directory = FSDirectory.open(Paths.get(indexDir));
		/**
		 * 采用重写的中文分词器
		 * 备注：由于ICTCLAS分词包没有提供jar文件，
		 * 		 导入eclipse工程中使用并不方便，
		 * 		 故采用了性能相对一般的IKAnalyer分词工具。
		 */
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		writer= new IndexWriter(directory, config);
	}

	/**
	 * 关闭写索引
	 * @throws Exception
	 */
	public void close()throws Exception{
		writer.close();
	}
}
