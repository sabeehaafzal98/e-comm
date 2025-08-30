package com.ecommerce.order; import com.ecommerce.shared.OrderDto; import io.temporal.client.*; import jakarta.enterprise.context.ApplicationScoped; import jakarta.inject.Inject;
@ApplicationScoped
public class OrderWorkflowStarter {
    @Inject
    TemporalWorkers workers;

    public void startWorkflow(OrderDto order){
        WorkflowClient client=workers.client();
        OrderWorkflow wf=client.newWorkflowStub(OrderWorkflow.class, WorkflowOptions.newBuilder().setTaskQueue("ORDER_TASK_QUEUE").build());
        WorkflowClient.start(wf::fulfillOrder, order); } }