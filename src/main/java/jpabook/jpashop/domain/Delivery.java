package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)    //ORDINAL : 숫자 , STRING : 문자 => 숫자를 사용 시 중간에 enum 추가시 에러발생.. 문자만 사용하자!
    private DeliveryStatus status;  //READY, COMP
}
