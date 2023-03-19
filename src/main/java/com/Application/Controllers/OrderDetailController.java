package com.Application.Controllers;

import com.Application.Model.Model;
import com.Application.Model.Order;
import com.Application.Model.OrderDetail;
import com.Application.ViewModels.OrderDetailViewModel;

public class OrderDetailController extends Controller{
    public OrderDetailController(Model model) {
        super(model);
    }

    public ControllerResult run(Object... args) {
        OrderDetail[] orderDetails = this.model.getOrderDetail((String)args[0]);
        String[][] stringOrderDetails = new String[orderDetails.length][];
        for(int i = 0; i<orderDetails.length; i++) {
            stringOrderDetails[i] = new String[]{orderDetails[i].productId, orderDetails[i].quantity, orderDetails[i].unitPrice, orderDetails[i].discount};
        }
        OrderDetailViewModel viewModel = new OrderDetailViewModel(stringOrderDetails);
        return new ControllerResult("OrderDetailView", viewModel);
    }
}
