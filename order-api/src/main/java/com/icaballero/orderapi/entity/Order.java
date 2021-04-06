package com.icaballero.orderapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.Data;


@Entity
@Table(name = "ORDERS")
@Data
public class Order {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "REG_DATE",nullable = false, updatable = false)
	private LocalDateTime regDate;
	
	@OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderLine> lines;
	
	@Column(name = "TOTAL" , nullable = false)
	private Double total;
	
	@ManyToOne
	@JoinColumn(name="FK_USER", updatable = false)
	private User user;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
	
	

}
