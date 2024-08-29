package com.site.myproj.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {Resource.class},  defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PdfListModel {

    private static final Logger log = LoggerFactory.getLogger(PdfListModel.class);

    @Self
    private Resource resource;

    @Inject
    private String damFolderPath;

    private List<Document> documents;

    @PostConstruct
    protected void init() {
        documents = new ArrayList<>();
        fetchDocuments();
    }

    private void fetchDocuments() {
        try {
            ResourceResolver resolver = resource.getResourceResolver();
            Resource folderResource = resolver.getResource(damFolderPath);

            if (folderResource != null) {
                for (Resource child : folderResource.getChildren()) {
                    if (child.isResourceType("dam:Asset")) {
                        ValueMap properties = child.getValueMap();
                        String title = properties.get("jcr:title", String.class);
                        if (title == null) {
                            title = child.getName(); // Fallback to the resource name if title is not available
                        }
                        String path = child.getPath();
                        documents.add(new Document(title, path));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error fetching documents from folder: {}", damFolderPath, e);
        }
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public static class Document {
        private String title;
        private String path;

        public Document(String title, String path) {
            this.title = title;
            this.path = path;
        }

        public String getTitle() {
            return title;
        }

        public String getPath() {
            return path;
        }
    }
}
