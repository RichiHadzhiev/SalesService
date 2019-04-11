package sales.service.api.dto;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import sales.service.api.sales.Sale;

public class SaleDTO {

	private BigDecimal price;
	
	private BigDecimal discount;
	
	private BigDecimal discounted_price;
	
	private String customer;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscountedPrice() {
		return discounted_price;
	}

	public void setDiscountedPrice(BigDecimal discounted_price) {
		this.discounted_price = discounted_price;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public static SaleDTO entityToDTO(Sale sale) {
		ModelMapper mp = new ModelMapper();
		mp.addMappings(new PropertyMap<Sale,SaleDTO>() {
			@Override
			protected void configure() {
				map().setCustomer(source.getCustomer().getName());
			}
		});
		SaleDTO dto = mp.map(sale, SaleDTO.class);
		return dto;
	}
}

