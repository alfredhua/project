package com.test.es;

import com.common.es.anno.Document;
import com.common.es.entity.EsBaseEntity;


@Document(indexName = "esDemo",type = "esType")
public class EsDemoEntity  extends EsBaseEntity {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String unique() {
        return id;
    }
}
