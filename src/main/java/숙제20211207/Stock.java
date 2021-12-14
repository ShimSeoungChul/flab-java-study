package 숙제20211207;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Stock {
	private String symbol;
	private int price;
	private int quantity;
}
