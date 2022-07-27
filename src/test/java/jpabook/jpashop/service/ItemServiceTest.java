package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품저장() throws IOException {
        //given
        Item item = new Item();
        item.setName("상품1");
        //when
        long itemId = itemService.saveItem(item);
        //then
        Assertions.assertEquals(item, itemRepository.findOne(itemId));
    }
    
    @Test
    public void 상품_한건_조회() throws IOException{
        //given
        Item item1 = new Item();
        item1.setName("item1");
        Item item2 = new Item();
        item2.setName("item2");
        //when
        long item1Id = itemService.saveItem(item1);
        long item2Id = itemService.saveItem(item2);

        //then
        Assertions.assertEquals(1L, itemRepository.findOne(item1Id).getId());
        Assertions.assertEquals(2L, itemRepository.findOne(item2Id).getId());
    }
    @Test
    public void 상품_전체_조회() throws IOException{
        //given
        Item item1 = new Item();
        item1.setName("item1");
        Item item2 = new Item();
        item2.setName("item2");
        //when
        long item1Id = itemService.saveItem(item1);
        long item2Id = itemService.saveItem(item2);
        //then
        Assertions.assertEquals(2, itemRepository.findAll().size());
    }
}