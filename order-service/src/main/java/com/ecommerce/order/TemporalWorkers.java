package com.ecommerce.order;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.*;
import io.temporal.worker.*;
import jakarta.annotation.*;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class TemporalWorkers {
    @ConfigProperty(name="temporal.target", defaultValue="temporal:7233")
    String target;
    private WorkerFactory factory;
    private WorkflowClient client;
    @PostConstruct void start(){
        var service=WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setTarget(target).build());
        client=WorkflowClient.newInstance(service);
        factory=WorkerFactory.newInstance(client);
        Worker w=factory.newWorker("ORDER_TASK_QUEUE");
        w.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
        factory.start();
    }
    @PreDestroy void stop(){
        if(factory!=null) factory.shutdown();
    }
    public WorkflowClient client(){
        return client;
    }
}