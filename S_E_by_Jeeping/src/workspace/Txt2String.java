// 网页预处理

package workspace;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.StringBuffer;

/**
 * 直接调用readFile方法，可以把text文本的数据转变为String返回
 * @author jeeping
 *
 */
public class Txt2String{
	/**
	 * 从text中读数据到buffer
	 * @param buffer
	 * @param filePath
	 */
	public static void read2Buffer(StringBuffer buffer, String filePath) throws IOException{
		InputStream in = new FileInputStream(filePath);
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		line = reader.readLine();
		while (line != null){
			buffer.append(line);
			buffer.append("\n");
			line = reader.readLine();
		}
		reader.close();
		in.close();
	}
	
	/**
	 * 读取文本文件内容
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String filePath) throws IOException{
		StringBuffer sb = new StringBuffer();
		Txt2String.read2Buffer(sb, filePath);
		return sb.toString();
	}
	
}
