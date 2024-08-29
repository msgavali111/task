package com.site.myproj.core.models;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Model(
        adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RSNIPLegalDisclosureModel {

    private static final Logger LOG = LoggerFactory.getLogger(RSNIPLegalDisclosureModel.class);

    @Self
    private Resource resource;

    @Inject
    @Default(values = "Something like a path")
    private String contentFragmentPath;

    private List<String> disclosureList;

    @PostConstruct
    protected void init() {
        try {
            LOG.debug("Content Fragment Path >> {}", contentFragmentPath);

            if (!StringUtils.isEmpty(contentFragmentPath) && resource != null) {
                ResourceResolver resourceResolver = resource.getResourceResolver();
                Resource contentFragmentResource = resourceResolver.getResource(contentFragmentPath);
                if (contentFragmentResource != null) {
                    Resource masterNode = contentFragmentResource.getChild("jcr:content/data/master");
                    if (masterNode != null) {
                        String[] disclosureArray = masterNode.getValueMap().get("disclosure", String[].class);
                        if (disclosureArray != null) {
                            disclosureList = Arrays.asList(disclosureArray);
                            LOG.debug("Disclosure list >> {}", disclosureList);
                        } else {
                            disclosureList = Arrays.asList("Disclosure", "Disclosure", "Disclosure");
                        }
                    }
                }
            } else {
                LOG.debug("Content Fragment Path or Resource is null.");
            }
        } catch (Exception e) {
            LOG.error("An error occurred while initializing RSNIPLegalDisclosureModel.", e);
            disclosureList = Collections.emptyList(); // Set an empty list if an error occurs
        }
    }

    public String getContentFragment() {
        return contentFragmentPath;
    }

    public List<String> getDisclosureList() {
        if (disclosureList == null || disclosureList.isEmpty()) {
            disclosureList = Arrays.asList("Disclosure", "Disclosure", "Disclosure");
        }
        return disclosureList;
    }
}
