package pl.mprm.diet_calendar.model.product_data;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MacroElement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;

	private String name;
	private Double amount;

	@Override
	public String toString() {
		return ElementUtils.generateElementString(name, amount);
	}
}