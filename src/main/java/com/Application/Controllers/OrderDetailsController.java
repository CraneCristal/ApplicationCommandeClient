package com.Application.Controllers;

import com.Application.Model.Model;
import com.Application.Model.OrderDetails;
import com.Application.ViewModels.OrderDetailsViewModel;

public class OrderDetailsController extends Controller{
    public OrderDetailsController(Model model) {
        super(model);
    }

    public ControllerResult run(Object... args) {
        OrderDetails[] orderDetails = this.model.getOrderDetail((String)args[0]);
        String[][] stringOrderDetails = new String[orderDetails.length][];
        for(int i = 0; i<orderDetails.length; i++) {
            stringOrderDetails[i] = new String[]{orderDetails[i].productId, orderDetails[i].quantity, orderDetails[i].unitPrice, orderDetails[i].discount};
        }
        OrderDetailsViewModel viewModel = new OrderDetailsViewModel(stringOrderDetails, (String)args[0]);
        return new ControllerResult("OrderDetailView", viewModel);
    }
}
