/**
 * 
 */
package com.acme.istore.domain.product;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author acogoluegnes
 */
public class ProductFieldSetMapper implements FieldSetMapper<Product> {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_PRICE = "price";

    public Product mapFieldSet(FieldSet fieldSet) throws BindException {
        Product product = new Product();
        product.setId(fieldSet.readString(FIELD_ID));
        product.setName(fieldSet.readString(FIELD_NAME));
        product.setDescription(fieldSet.readString(FIELD_DESCRIPTION));
        product.setPrice(fieldSet.readBigDecimal(FIELD_PRICE));
        return product;
    }

}
