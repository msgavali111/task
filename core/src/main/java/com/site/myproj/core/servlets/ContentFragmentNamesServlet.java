package com.site.myproj.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = { Servlet.class },
        property = {
                Constants.SERVICE_DESCRIPTION + "=Content Fragment Names Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/getContentFragments"
        })
public class ContentFragmentNamesServlet extends SlingSafeMethodsServlet {

    private final Logger logger = LoggerFactory.getLogger(ContentFragmentNamesServlet.class);

    @SlingObject
    private ResourceResolver resourceResolver;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");


        String folderPath = request.getParameter("folderPath");

        logger.debug("Folder path is >> {}", folderPath);

        if (folderPath == null || folderPath.isEmpty()) {
            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"folderPath parameter is missing\"}");
            return;
        }

        Resource folderResource = resourceResolver.getResource(folderPath);
        logger.debug("Folder Resourceis >> {}", folderResource);
        if (folderResource == null) {
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"Folder not found\"}");
            return;
        }

        List<String> fragmentNames = new ArrayList<>();
        Iterator<Resource> children = folderResource.listChildren();
        while (children.hasNext()) {
            Resource child = children.next();
            logger.debug("Child is >> {}", child);
            if (child != null && "dam:Asset".equals(child.getResourceType())) {
                logger.debug("Child name is >> {}", child.getName());
                fragmentNames.add(child.getName());
            }
        }

        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse.put("fragmentNames", new JSONArray(fragmentNames));
            logger.debug("JSON Response is >> {}", jsonResponse);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(jsonResponse.toString());
    }
}

