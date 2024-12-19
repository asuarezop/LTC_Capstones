package com.pluralsight.easyshop.models;

import java.math.BigDecimal;

public class OrderLineItem {
    private int orderLineItemId;
    private int orderId;
    private int productId;
    private String name;
    private BigDecimal salesPrice;
    private int quantity;
    private BigDecimal discount;
    private BigDecimal orderLineTotal;

    public OrderLineItem() {
    }

    public OrderLineItem(int orderLineItemId, int orderId, int productId, String name, BigDecimal salesPrice, int quantity, BigDecimal discount, BigDecimal orderLineTotal) {
        this.orderLineItemId = orderLineItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.orderLineTotal = orderLineTotal;
    }

    public int getOrderLineItemId() {
        return orderLineItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getOrderLineTotal() {
        BigDecimal lineItemPrice = getSalesPrice();
        int lineItemQuantity = getQuantity();
        BigDecimal lineItemDiscount = getDiscount();

        BigDecimal lineSubTotal = lineItemPrice.multiply(BigDecimal.valueOf(lineItemQuantity));
        BigDecimal lineItemDiscounted = lineItemPrice.multiply(lineItemDiscount);

        return lineSubTotal.subtract(lineItemDiscounted);
    }

}
