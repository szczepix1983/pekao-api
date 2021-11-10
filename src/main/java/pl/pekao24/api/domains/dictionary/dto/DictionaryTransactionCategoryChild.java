package pl.pekao24.api.domains.dictionary.dto;

import java.io.Serializable;

public class DictionaryTransactionCategoryChild implements Serializable {
    public String id;
    public String parentId;
    public String name;
    public String labelColor;
    public String color;
    public String iconName;
    public String type;
    public String group;
}
