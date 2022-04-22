package com.common.api.entity.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageRequest implements java.io.Serializable {

    private Integer page_num;

    private Integer page_size=10;

    public PageRequest() {
    }

    public PageRequest(Integer page_num, Integer page_size) {
        this.page_num = page_num;
        this.page_size = page_size;
    }

    public Integer getPage_num() {
        if(page_num==null||page_num<1){
            page_num=1;
        }
        return page_num;
    }


    public Integer getOffset(){
        if(page_num==null||page_num<1){
            page_num=1;
        }
        return (page_num-1)*page_size;
    }

}
