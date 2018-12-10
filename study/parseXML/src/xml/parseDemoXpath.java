package xml;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class parseDemoXpath {
	private static Element rootElement;
	private static Document document;
	private static List<Node> elements;
	public static void main(String[] args) {
		//1 创建sax读取对象
	    SAXReader reader = new SAXReader();
	    try {
	    	//2 指定解析的xml源
			document = reader.read("src/xml/stus.xml");
			//3 得到根元素
			rootElement = document.getRootElement();
			//4 得到根元素的所有子元素，返回的是一个list
			elements = rootElement.selectNodes("//stu/age");
			for (Node element : elements) {
				System.out.println(element.getText());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
