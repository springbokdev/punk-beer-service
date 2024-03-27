package space.springbok.punkbeerservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import space.springbok.punkbeerservice.model.MaltItem;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MaltItemMapperTest {

    @Autowired
    MaltItemMapper maltItemMapper;

    @Test
    public void testMapping() {
        MaltItem maltItem1 = new MaltItem();
        maltItem1.setName("malt1");
        MaltItem maltItem2 = new MaltItem();
        maltItem1.setName("malt2");
        List<space.springbok.punkbeerservice.entities.MaltItem> mapped =maltItemMapper.maltItemDtoToMaltItem(Arrays.asList(maltItem1, maltItem2));

        assertThat(mapped.size()).isEqualTo(2);

    }

}