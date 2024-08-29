package com.site.myproj.core.workflows;


import com.adobe.xfa.Obj;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(

        service = Servlet.class,
        property= {
                "sling.servlet.paths=" + "/bin/frombackendapi",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        }
)

@ServiceDescription("Using this servlet we are going to trigger the workflow from backend API in java")

public class TriggerWorkflowUsingBackendAPI extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    protected final Logger log = LoggerFactory.getLogger(TriggerWorkflowUsingBackendAPI.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        log.debug("<< Calling workflow using servlet >>");
        try {
            // Workflow model on get call as part of workflow api
            final String model = "/var/workflow/models/trigger-by-servlet-workflow-model";

            // Content path on which the workflow will get trigger
            final String payloadContentPath = "/content/mysite/us/en";

            final ResourceResolver resolver = request.getResourceResolver();
            final WorkflowSession wfSession = resolver.adaptTo(WorkflowSession.class);

            // Create a workflow model using model path
            final WorkflowModel workflowModel = wfSession.getModel(model);

            // Create a worklfowData
            final WorkflowData workflowData = wfSession.newWorkflowData("JCR_PATH", payloadContentPath);

            final Map<String, Object> workflowMetaData = new HashMap<>();
            workflowMetaData.put("pathInfo", request.getPathInfo());

            wfSession.startWorkflow(workflowModel, workflowData, workflowMetaData);

        } catch (WorkflowException e) {

            log.debug("Exception is >> {}", e.getMessage());

        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("Response : Workflow started");
    }
}
