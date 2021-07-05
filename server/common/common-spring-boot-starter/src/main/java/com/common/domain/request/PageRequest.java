package com.common.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageRequest implements java.io.Serializable {

    private List<String> auth_code_list;

    private Integer page_num;

    private Integer page_size=10;

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
