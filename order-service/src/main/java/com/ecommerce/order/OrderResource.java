package com.ecommerce.order;
import com.ecommerce.shared.OrderDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
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
}