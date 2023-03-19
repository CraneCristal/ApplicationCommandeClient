package com.Application.Controllers;

import com.Application.Model.Model;
import com.Application.Model.Product;
import com.Application.ViewModels.ProductViewModel;

public class ProductController extends Controller{

    public ProductController(Model model) {
        super(model);
    }

    public ControllerResult run(Object... args) {
        Product product = this.model.getProduct((String) args[0]);
        ProductViewModel viewModel = new ProductViewModel(product.productId, product.productName, product.category, product.standardCost, product.quantityPerUnit, (String) args[1]);
        return new ControllerResult("ProductView", viewModel);
    }
}
