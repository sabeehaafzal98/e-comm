package com.ecommerce.order;
import com.ecommerce.shared.OrderDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.stream.Collectors;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject OrderWorkflowStarter starter;

    @POST
    @Transactional
    public Response placeOrder(OrderDto order){
        OrderEntity e=OrderMapper.toEntity(order);
        e.persist();
        starter.startWorkflow(order);
        return Response.ok("Order placed").build();
    }

    @GET
    public List<OrderDto> getOrder(){
        return OrderEntity.listAll().stream().map(e-> OrderMapper.toDto((OrderEntity)e)).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public OrderDto getById(@PathParam("id") Long id){
        OrderEntity orderEntity = OrderEntity.findById(id);
        if(orderEntity==null){
            throw new NotFoundException("Order not found");
        }
        return OrderMapper.toDto(orderEntity);
    }
}