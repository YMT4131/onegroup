package com.shanghai.query;

import com.shanghai.base.BaseQuery;

public class ListQuery extends BaseQuery {

    private String listName;

    private String listInfo;

    private String typeName;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListInfo() {
        return listInfo;
    }

    public void setListInfo(String listInfo) {
        this.listInfo = listInfo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
