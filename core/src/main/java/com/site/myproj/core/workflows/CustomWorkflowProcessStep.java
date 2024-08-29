package com.site.myproj.core.workflows;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = WorkflowProcess.class,
        property = { "process.label = Demo Custom Workflow Process" }
)
public class CustomWorkflowProcessStep implements WorkflowProcess{

    protected final Logger log = LoggerFactory.getLogger(CustomWorkflowProcessStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap wfMetaData) throws WorkflowException {

//    get the payload type
        String payloadType = workItem.getWorkflowData().getPayloadType();

        if(StringUtils.equals(payloadType, "JCR_PATH")){
            log.debug("The payload type is >> {}", payloadType);

            String payloadPath = workItem.getWorkflowData().getPayload().toString();
            log.debug("Payload Path is >> {}", payloadPath);
        }

        String metaDataArgs = wfMetaData.get("PROCESS_ARGS", String.class);
        log.debug("The metadata args are >> {}", metaDataArgs);

    }
}
