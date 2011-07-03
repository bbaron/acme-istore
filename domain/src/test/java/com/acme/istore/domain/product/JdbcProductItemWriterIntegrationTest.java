package com.acme.istore.domain.product;
import static com.acme.istore.domain.util.JdbcTestHelpers.*;
import static com.google.common.collect.Lists.*;
import static java.util.Collections.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcProductItemWriterIntegrationTest {

    @Autowired
    private JdbcProductItemWriter itemWriter;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Before
    public void cleanTable() throws Exception {
        deleteFromTables(jdbcTemplate, "product");
    }
    
    @Test
    public void writeOneRow() throws Exception {
        int countBefore = productCount();
        Product product1 = product("1");
        itemWriter.write(singletonList(product1));
        int countAfter = productCount();
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    public void updateOneRow() throws Exception {
        Product product2 = product("2");
        Product product3 = product("3");
        product3.setId(product2.getId());
        int countBefore = productCount();
        itemWriter.write(newArrayList(product2, product3));
        int countAfter = productCount();
        assertEquals(countBefore + 1, countAfter);
    }
    
    private Product product(String id) {
        Product product = new Product();
        product.setId(id);
        product.setDescription("descr" + id);
        product.setName("name" + id);
        product.setPrice(BigDecimal.ONE);
        return product;        
    }

    private int productCount() {
        return countRowsInTable(jdbcTemplate, "product");
    }
    
}
