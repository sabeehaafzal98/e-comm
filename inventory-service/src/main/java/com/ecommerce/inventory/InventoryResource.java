package com.ecommerce.inventory;


import com.ecommerce.shared.ProductDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @Inject
    ProductRepository productRepository;

    @POST
    @Transactional
    public ProductEntity addProduct(ProductDto productDto){
        ProductEntity productEntity = new ProductEntity();
        productEntity.id = productDto.id();
        productEntity.name = productDto.name();
        productEntity.quantity = productDto.quantity();
        productEntity.price = productDto.price();
        productRepository.persist(productEntity);
        return productEntity;
    }

    @GET
    public List<ProductEntity> listProducts(){
        return productRepository.listAll();
    }

    public boolean reserveProduct(Long productId, int qty){
        ProductEntity productEntity = productRepository.findById(productId);
        if(productEntity!=null && productEntity.quantity>=qty){
            productEntity.quantity-=qty;
            return true;
        }
        return false;
    }
}
