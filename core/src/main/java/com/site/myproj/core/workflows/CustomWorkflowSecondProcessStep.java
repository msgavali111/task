package com.site.myproj.core.workflows;


import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(

        service = WorkflowProcess.class,
        property = {"process.label = Second Custom Workflow Process Step"}
)
public class CustomWorkflowSecondProcessStep implements WorkflowProcess{

    protected final Logger log = LoggerFactory.getLogger(CustomWorkflowSecondProcessStep.class);

    public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap wfMetaData) throws WorkflowException {

        log.debug("Custom workflow process start with the second step");

        String name = workItem.getWorkflow().getMetaDataMap().get("name", String.class);
        String age = workItem.getWorkflow().getMetaDataMap().get("age", String.class);

        log.debug("Name >> {}", name);
        log.debug("Age >> {}", age);
    }
}
