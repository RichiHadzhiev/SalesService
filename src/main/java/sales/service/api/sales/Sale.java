package sales.service.api.sales;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import sales.service.api.customers.Customer;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal price;
	
	private BigDecimal discount;
	
	private BigDecimal discounted_price;
	
	@ManyToOne
	private Customer customer;
	
	
	public Long getId() {
		return this.id;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public BigDecimal getDiscount() {
		return this.discount;
	}
	
	public BigDecimal getDiscountedPrice() {
		return this.discounted_price;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public void setDiscountedPrice(BigDecimal discounted_price) {
		this.discounted_price = discounted_price;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
