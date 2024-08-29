package com.site.myproj.core.workflows;


import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.ParticipantStepChooser;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.Workflow;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service= ParticipantStepChooser.class,
        property= {"chooser.label=" + "Custom Dynamic Participant Step"}
)
public class DynamicParticipantStep implements ParticipantStepChooser{

    protected final Logger log = LoggerFactory.getLogger(DynamicParticipantStep.class);

    @Override
    public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        log.debug("Dynamic Participant Worklflow Started");
        Workflow workflowModel = workItem.getWorkflow();
        log.debug("Workflow Model >> {}", workflowModel);
        return "Executed dynamic participant step";
    }
}
