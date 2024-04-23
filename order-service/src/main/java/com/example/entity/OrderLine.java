package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Order order;

    @Column(length = 10, nullable = false)
    private Long productId;

    private Integer quantity;
    private Double price;

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
