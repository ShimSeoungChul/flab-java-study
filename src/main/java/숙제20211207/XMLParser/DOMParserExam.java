package 숙제20211207.XMLParser;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParserExam {
	public static void main(String[] args){
		try {
			// 파일 경로
			File stocks = new File(Paths.get("").toAbsolutePath()+"/src/main/java/숙제20211207/Stocks.xml");
			// 파일 읽기
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			System.out.println("파일출력");

			// 읽어들인 파일 불러오기
			NodeList nodes = doc.getElementsByTagName("stock");
			for (int k = 0; k < nodes.getLength(); k++) {
				Node node = nodes.item(k);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("Stock Symbol: " + getValue("symbol", element));
					System.out.println("Stock Price: " + getValue("price", element));
					System.out.println("Stock Quantity: " + getValue("quantity", element));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}