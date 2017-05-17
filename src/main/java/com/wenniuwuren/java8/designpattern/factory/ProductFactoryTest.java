
package com.wenniuwuren.java8.designpattern.factory;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by hzzhuyibin on 2017/5/17.
 */
public class ProductFactoryTest {

    public static Product createProduct(String name) {

        switch (name) {
            case "loan": return new Loan();
            case "stock": return new Stock();
            default: throw new RuntimeException("no such product " + name);
        }
    }

    final static Map<String, Supplier<Product>> map = new HashedMap();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
    }

    public static Product createProductByLambda(String name) {
        Supplier<Product> productSupplier = map.get(name);
        if (productSupplier != null) {
            return productSupplier.get();
        } else {
            throw new RuntimeException("no such product " + name);
        }

    }

    public static void main(String[] args) {
        Product p = ProductFactoryTest.createProduct("loan");

        Product p1 = ProductFactoryTest.createProductByLambda("loan");
    }
}

