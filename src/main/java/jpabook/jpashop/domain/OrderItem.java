package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")   //@JoinColumn => 연관관계 주인
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")  //@JoinColumn => 연관관계 주인
    private Order order;

    private int orderPrice; //주문가격
    
    private int count;  //주문수량
}
