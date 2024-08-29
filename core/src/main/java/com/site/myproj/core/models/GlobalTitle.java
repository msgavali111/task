package com.site.myproj.core.models;

import com.adobe.cq.wcm.core.components.commons.link.Link;
import com.adobe.cq.wcm.core.components.models.Title;
import com.day.cq.wcm.api.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Title.class, resourceType = "mysite/components/clientcomponents/globalTitle")
public class GlobalTitle implements Title {

    @ScriptVariable
    private Page currentPage;

    @Self @Via(type = ResourceSuperType.class)
    private Title title;

    @Inject
    @ValueMapValue(name = "linkTitleAttribute")
    private String linkTitleAttribute;

    @Inject
    @ValueMapValue(name = "linkAccessibilityLabel")
    private String linkAccessibilityLabel;

    @Override
    public String getText() {
        return StringUtils.isEmpty(title.getText()) ? currentPage.getTitle() : title.getText();
    }

    @Override
    public String getType() {
        return title.getType();
    }

    public String getLinkTitleAttribute(){
        return linkTitleAttribute;
    }

    @Override
    public Link getLink() {
        return title.getLink();
    }

    @Override
    public boolean isLinkDisabled() {
        return title.isLinkDisabled();
    }

    public String getLinkAccessibilityLabel(){
        return linkAccessibilityLabel;
    }

}