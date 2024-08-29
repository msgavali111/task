package com.site.myproj.core.models.impl;

import com.site.myproj.core.bean.NestedMultifieldBean;
import com.site.myproj.core.models.NestedMultifieldUsingMapAndBeanModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = NestedMultifieldUsingMapAndBeanModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class NestedMultifieldUsingMapAndBeanModelImpl implements NestedMultifieldUsingMapAndBeanModel{

    public static final Logger LOG = LoggerFactory.getLogger(NestedMultifieldUsingMapAndBeanModelImpl.class);

    @SlingObject
    private Resource resource;


    @Override
    public List<NestedMultifieldBean> getNestedBookDetailsBean() {
        List<NestedMultifieldBean> bookDetailsBeanList = new ArrayList<>();
        try{
            Resource currentComponent = resource.getChild("nestedBookDetailsBean");
            LOG.debug("Current Component >> {}", currentComponent);
            if(currentComponent != null){
                for(Resource book : currentComponent.getChildren()){
                    bookDetailsBeanList.add(new NestedMultifieldBean(book));
                }
            }

        }catch (Exception ex){
            LOG.debug("Exception occured in  NestedMultifieldUsingMapAndBeanModelImpl >> {}", ex.toString());
        }
        return bookDetailsBeanList;
    }

    @Override
    public List<NestedMultifieldBean> getMultifieldItems() {

        List<NestedMultifieldBean> bookDetailsNested = new ArrayList<>();

        try{
            Resource currentResource = resource.getChild("multifieldItems");
            LOG.debug("Outer Resource name >> {}", currentResource);

            if(currentResource != null){
                for(Resource item : currentResource.getChildren()){
                    LOG.debug("Book Author Name : {}", item.getValueMap().get("authorName", String.class));
                }
            }


        }catch (Exception e){
            LOG.debug("Exception occured in getMultifieldItems () >> {}", e.toString());
        }


        return null;
    }
}
