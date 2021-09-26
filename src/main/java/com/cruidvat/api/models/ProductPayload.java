package com.cruidvat.api.models;

public class ProductPayload {

    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(String productCode) {
        this.product = new Product(productCode);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Builder newBuilder() {
        return new ProductPayload().new Builder();
    }

    public class Product {

        private String code;

        private Product(String productCode) {
            this.code = productCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String productCode) {
            code = productCode;
        }

    }

    public class Builder {

        private Builder() {

        }

        public Builder setProductCode(String productCode) {
            ProductPayload.this.setProduct(productCode);
            return this;
        }

        public Builder setQuantity(int quantity) {
            ProductPayload.this.setQuantity(quantity);
            return this;
        }

        public ProductPayload build() {
            return ProductPayload.this;
        }
    }

}


