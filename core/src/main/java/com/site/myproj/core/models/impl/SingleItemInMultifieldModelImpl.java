package com.site.myproj.core.models.impl;


import com.site.myproj.core.models.SingleItemInMultifieldModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = SingleItemInMultifieldModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SingleItemInMultifieldModelImpl implements SingleItemInMultifieldModel {

    private static final Logger LOG = LoggerFactory.getLogger(SingleItemInMultifieldModelImpl.class);

    @SlingObject
    Resource componentResource;

    @Override
    public List<String> getItemDetails() {

        List<String> itemsList = new ArrayList<>();
        Resource resource = componentResource.getChild("itemDetails");
        LOG.debug("Resource >> {}", resource);

        try{
            if(resource != null){
                for(Resource item : resource.getChildren()){
                    itemsList.add(item.getValueMap().get("firstName", String.class));
                }
            }
        }catch (Exception e){
            LOG.debug("<< Error occured during SingleItemInMultifieldModelImpl Implementation >> {}", e.getMessage());
        }
        LOG.debug("Item List >> {}", itemsList);
        return itemsList;
    }
}
