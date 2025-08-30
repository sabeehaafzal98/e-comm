package com.ecommerce.shipping; import io.quarkus.runtime.Startup;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.*; import io.temporal.worker.*; import jakarta.annotation.*; import jakarta.enterprise.context.ApplicationScoped; import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
@Startup
public class ShippingWorker {
    @ConfigProperty(name="temporal.target", defaultValue="temporal:7233")
    String target;
    private WorkerFactory factory;

    @PostConstruct
    void start(){
        var service=WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setTarget(target).build());
        var client=WorkflowClient.newInstance(service, WorkflowClientOptions.newBuilder().setNamespace("default").build());
        factory=WorkerFactory.newInstance(client);
        Worker w=factory.newWorker("SHIPPING_TASK_QUEUE");
        w.registerActivitiesImplementations(new ShippingActivityImpl());
        factory.start();
    }
    @PreDestroy void stop() {
        if(factory!=null) factory.shutdown();
    }
}
