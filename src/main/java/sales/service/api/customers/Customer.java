package sales.service.api.customers;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private BigDecimal discount;
	
	private BigDecimal turnOver;

	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BigDecimal getDiscount() {
		return this.discount;
	}
	
	public BigDecimal getTurnOver() {
		return turnOver;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public void setTurnOver(BigDecimal turnOver) {
		this.turnOver = turnOver;
	}
}
