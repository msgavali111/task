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
        property = {"process.label = First Custom Workflow Process Step"}
)
public class CustomWorkflowFirstProcessStep implements WorkflowProcess{

    protected final Logger log = LoggerFactory.getLogger(CustomWorkflowFirstProcessStep.class);

    public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap wfMetaData) throws WorkflowException {

      log.debug("Custom workflow process start with the first step");

      workItem.getWorkflow().getMetaDataMap().put("name", "Monish");
      workItem.getWorkflow().getMetaDataMap().put("age", "25");

    }
}
