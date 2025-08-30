package com.ecommerce.inventory;


import com.ecommerce.shared.InventoryDto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @POST
    @Transactional
    public Response addInventory(InventoryDto dto){
        InventoryEntity entity=InventoryMapper.toEntity(dto);
        entity.persist();
        return Response.status(Response.Status.CREATED).entity(InventoryMapper.toDto(entity)).build();
    }

    @GET
    public List<InventoryDto> getAllInventories(){
        return InventoryEntity.listAll().stream().map(e->InventoryMapper.toDto((InventoryEntity)e)).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public InventoryDto getById(@PathParam("id")Long id){
        InventoryEntity entity = InventoryEntity.findById(id);
        if(entity==null)
            throw new NotFoundException("Product not found");
        return InventoryMapper.toDto(entity);
    }

}
