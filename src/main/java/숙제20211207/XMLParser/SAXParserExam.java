package 숙제20211207.XMLParser;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserExam {
	public static void main(String[] args) {
		File stocks = new File(Paths.get("").toAbsolutePath()+"/src/main/java/숙제20211207/Stocks.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			StocksSaxHandler handler = new StocksSaxHandler();
			parser.parse(stocks, handler);

			List<Stock> list = handler.getStockList();

			for(Stock stock:list) {
				System.out.println(stock);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
