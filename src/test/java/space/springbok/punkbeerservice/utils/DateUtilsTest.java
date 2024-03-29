package space.springbok.punkbeerservice.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilsTest {

    @Test
    void stringToDate() throws Exception{
        Date actualDate = DateUtils.stringToDate("06/2025");
        DateUtils.dateToString(actualDate);
        assertThat(actualDate).isNotNull();
        assertThat(DateUtils.dateToString(actualDate)).isEqualTo("06/2025");
    }

}