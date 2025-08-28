package com.ecommerce.inventory;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.*;
import io.temporal.worker.*;
import jakarta.annotation.*;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped public class InventoryWorker {
    @ConfigProperty(name="temporal.target", defaultValue="temporal:7233")
    String target;
    private WorkerFactory factory;
    @PostConstruct void start(){
        var service=WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setTarget(target).build());
        var client=WorkflowClient.newInstance(service);
        factory=WorkerFactory.newInstance(client);
        Worker w=factory.newWorker("INVENTORY_TASK_QUEUE");
        w.registerActivitiesImplementations(new InventoryActivityImpl());
        factory.start();
    }
    @PreDestroy
    void stop(){
        if(factory!=null)
            factory.shutdown();
    }
}