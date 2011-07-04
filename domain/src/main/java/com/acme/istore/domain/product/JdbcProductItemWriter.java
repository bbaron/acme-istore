/**
 *
 */
package com.acme.istore.domain.product;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author bazoud
 *
 */
public class JdbcProductItemWriter implements ItemWriter<Product> {
    private static final String INSERT_PRODUCT = "INSERT INTO PRODUCT (ID,NAME,DESCRIPTION,PRICE) VALUES(?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET NAME=?, DESCRIPTION=?, PRICE=? WHERE ID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void write(List<? extends Product> items) throws Exception {
        for (Product item : items) {
            int updated = jdbcTemplate.update(UPDATE_PRODUCT, item.getName(), item.getDescription(), item.getPrice(), item.getId());
            if (updated == 0) {
                jdbcTemplate.update(INSERT_PRODUCT, item.getId(), item.getName(), item.getDescription(), item.getPrice());
            }
        }
    }

}
