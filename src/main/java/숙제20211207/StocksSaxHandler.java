package 숙제20211207;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Builder;

public class StocksSaxHandler extends DefaultHandler {
	//파싱한 Stock객체를 넣을 리스트
	private List<Stock> stockList;
	//파싱한 Stock 객체
	private Stock stock;
	//character 메소드에서 저장할 문자열 변수
	private String str;

	public StocksSaxHandler() {
		stockList = new ArrayList<>();
	}

	public void startElement(String uri, String localName, String name, Attributes att) {
		//시작 태그를 만났을 때 발생하는 이벤트
		if(name.equals("stock")) {
			stock = new Stock();
			stockList.add(stock);
		}
	}
	public void endElement(String uri, String localName, String name) {
		//끝 태그를 만났을 때,
		if(name.equals("symbol")) {
			stock.setSymbol(str);
		}else if(name.equals("price")) {
			stock.setPrice(Integer.parseInt(str));
		}else if(name.equals("quantity")) {
			stock.setQuantity(Integer.parseInt(str));
		}
	}
	public void characters(char[] ch, int start, int length) {
		//태그와 태그 사이의 내용을 처리
		str = new String(ch,start,length);
	}
	public List<Stock> getStockList(){
		return stockList;
	}
	public void setStockList(List<Stock> stockList) {
		this.stockList=stockList;
	}
}
