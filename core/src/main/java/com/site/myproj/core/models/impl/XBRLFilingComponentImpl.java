package com.site.myproj.core.models.impl;

import com.site.myproj.core.models.XBRLFilingComponentModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = XBRLFilingComponentModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class XBRLFilingComponentImpl implements XBRLFilingComponentModel {

    private static final Logger LOG = LoggerFactory.getLogger(XBRLFilingComponentImpl.class);

    @SlingObject
    private Resource componentResource;

    @Override
    public List<Map<String, String>> getXbrlCardDetails() {

        List<Map<String,String>> xbrlCardDetailsMap = new ArrayList<>();
        try{
            LOG.debug("Component Resource>> {}", componentResource.getPath());
            Resource xbrlCardParentNode = componentResource.getChild("xbrlCardDetails");
            LOG.debug("xbrlCardParentNode >> {}", xbrlCardParentNode);
            if(xbrlCardParentNode != null){
                for( Resource xbrlCardItem : xbrlCardParentNode.getChildren()){
                    Map<String, String> cardMap = new HashMap<>();
                    cardMap.put("cardImagePath",xbrlCardItem.getValueMap().get("cardImagePath", String.class));
                    cardMap.put("cardTitle",xbrlCardItem.getValueMap().get("cardTitle", String.class));
                    xbrlCardDetailsMap.add(cardMap);
                }
            }
        }catch ( Exception e){
            LOG.debug("<< Error occured during XBRLFilingCardImpl Implementation >> {}", e.getMessage());
        }
        return xbrlCardDetailsMap;
    }
}
