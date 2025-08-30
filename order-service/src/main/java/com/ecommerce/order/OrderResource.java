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
    public Response create(OrderDto order){
        OrderEntity entity= new OrderEntity();
        entity.productId= order.productId();
        entity.quantity= order.quantity();
        entity.amount= order.amount();
        entity.customerId= order.customerId();
        entity.persist();
        OrderDto persisted = OrderMapper.toDto(entity);
        //start workflow async fire-and-forget)
        starter.startWorkflow(persisted);
        return Response.status(Response.Status.ACCEPTED).entity(persisted).build();
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