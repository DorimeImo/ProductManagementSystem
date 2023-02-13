package product;

import lombok.Data;
import product.details.ProductType;
import product.details.Status;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;

    private String productId;

    private ProductType productType;

    private Status status;
}
