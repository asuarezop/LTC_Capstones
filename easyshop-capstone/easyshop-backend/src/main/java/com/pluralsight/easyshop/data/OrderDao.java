package com.pluralsight.easyshop.data;

import com.pluralsight.easyshop.models.Order;

public interface OrderDao {
    Order create(int userId);
}
