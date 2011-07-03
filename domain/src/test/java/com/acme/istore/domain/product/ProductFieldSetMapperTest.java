/**
 *
 */
package com.acme.istore.domain.product;

import static com.acme.istore.domain.product.ProductFieldSetMapper.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * Unit with mock.
 * 
 * @author bazoud
 */
public class ProductFieldSetMapperTest {

    @Test
    public void testMapFieldMapClassic() throws Exception {
        DefaultFieldSet fieldSet = new DefaultFieldSet(//
            new String[] { "id", "name", "desc", "100.25" }, //
            new String[] { FIELD_ID, FIELD_NAME, FIELD_DESCRIPTION, FIELD_PRICE });
        ProductFieldSetMapper mapper = new ProductFieldSetMapper();
        Product p = mapper.mapFieldSet(fieldSet);
        assertEquals("id", p.getId());
        assertEquals("name", p.getName());
        assertEquals("desc", p.getDescription());
        assertEquals(new BigDecimal("100.25"), p.getPrice());
    }

    @Test
    public void testMapFieldSetMock() throws Exception {
        FieldSet fieldSet = mock(FieldSet.class);
        ProductFieldSetMapper mapper = new ProductFieldSetMapper();
        mapper.mapFieldSet(fieldSet);
        verify(fieldSet, times(1)).readString(FIELD_ID);
        verify(fieldSet, times(1)).readString(FIELD_NAME);
        verify(fieldSet, times(1)).readString(FIELD_DESCRIPTION);
        verify(fieldSet, times(1)).readBigDecimal(FIELD_PRICE);
        verifyNoMoreInteractions(fieldSet);
    }
}
