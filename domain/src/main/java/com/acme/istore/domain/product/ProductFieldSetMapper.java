/**
 * 
 */
package com.acme.istore.domain.product;

import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

/**
 * @author acogoluegnes
 */
public class ProductFieldSetMapper implements FieldSetMapper<Product> {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_PRICE = "price";
    
    public static final Map<String, String> FIELD = ImmutableMap.of("id", "id", "name", "name", "description", "description", "price", "price");
    public static final String FIELD_LIST = Joiner.on(',').join(FIELD.values());

    public Product mapFieldSet(FieldSet fieldSet) throws BindException {
        Product product = new Product();
        product.setId(fieldSet.readString(FIELD.get("id")));
        product.setName(fieldSet.readString(FIELD.get("name")));
        product.setDescription(fieldSet.readString(FIELD.get("description")));
        product.setPrice(fieldSet.readBigDecimal(FIELD.get("price")));
        return product;
    }

}
