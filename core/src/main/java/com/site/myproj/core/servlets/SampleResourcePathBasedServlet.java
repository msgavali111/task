//package com.site.myproj.core.servlets;
//
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.servlets.HttpConstants;
//import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.propertytypes.ServiceDescription;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//@Component( service = Servlet.class)
//@SlingServletResourceTypes(
//        resourceTypes = "mysite/components/content/nestedMultifield",
//        methods = HttpConstants.METHOD_GET,
//        extensions = "html"
//)
//
//@ServiceDescription("This is my first sling resource based servlet using Declarative Service 1.4 R7 annotations")
//
//public class SampleResourcePathBasedServlet extends SlingSafeMethodsServlet {
//
//    @Override
//    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("text/plain");
//        response.getWriter().write("This is my first sling resource based servlet using Declarative Service 1.4 R7 annotations\nThe script written in this Servlet will execute when the request URL get the sling:resrourceType value as mysite/components/content/nestedMultifield");
//    }
//}
