package com.shanghai.vo;

public class List<S> {
    private Integer listId;

    private String listName;

    private String listInfo;

    private Integer typeId;

    private Integer isValid;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    public String getListInfo() {
        return listInfo;
    }

    public void setListInfo(String listInfo) {
        this.listInfo = listInfo == null ? null : listInfo.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}