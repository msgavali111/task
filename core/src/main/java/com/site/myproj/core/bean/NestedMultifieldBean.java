package com.site.myproj.core.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class NestedMultifieldBean {

    public static final Logger LOG = LoggerFactory.getLogger(NestedMultifieldBean.class);
    private String bookName;
    private Date bookPublishDate;
    private int bookCopies;

    public NestedMultifieldBean( Resource resource ){

        try{
            if(StringUtils.isNotBlank(resource.getValueMap().get("bookName", String.class))){
                this.bookName = resource.getValueMap().get("bookName", String.class);
            }
            if(resource.getValueMap().get("bookPublishDate", Date.class) != null){
                this.bookPublishDate = resource.getValueMap().get("bookPublishDate", Date.class);
            }
            if(resource.getValueMap().get("bookCopies", Integer.class) != null){
                this.bookCopies = resource.getValueMap().get("bookCopies", Integer.class);
            }

        }catch ( Exception e){
            LOG.debug("Exception occured in NestedMultifieldBean class {}", e.toString());
        }
    }

    public String getBookName() {
        return bookName;
    }

    public Date getBookPublishDate() {
        return bookPublishDate;
    }

    public int getBookCopies() {
        return bookCopies;
    }
}
