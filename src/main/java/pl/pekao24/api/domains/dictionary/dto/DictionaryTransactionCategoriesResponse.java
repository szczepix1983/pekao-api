package pl.pekao24.api.domains.dictionary.dto;

import java.io.Serializable;
import java.util.List;

public class DictionaryTransactionCategoriesResponse implements Serializable {
    public List<DictionaryTransactionCategory> expenseCategories;
    public List<DictionaryTransactionCategory> incomeCategories;
}
