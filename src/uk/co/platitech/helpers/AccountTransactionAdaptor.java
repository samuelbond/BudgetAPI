package uk.co.platitech.helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import uk.co.platitech.AccountTransactionsEntity;


import java.lang.reflect.Type;

/**
 * Created by samuel on 14/11/14.
 */
public class AccountTransactionAdaptor implements JsonSerializer<AccountTransactionsEntity> {
    @Override
    public JsonElement serialize(AccountTransactionsEntity transactionsEntity, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("account_id", transactionsEntity.getBankAccountEntity().getId());
        jsonObject.addProperty("transaction_name", transactionsEntity.getTransactionName());
        jsonObject.addProperty("transaction_amount", transactionsEntity.getTransactionAmount());
        jsonObject.addProperty("transaction_id", transactionsEntity.getTransactionId());
        jsonObject.addProperty("transaction_date", transactionsEntity.getTransactionDate());
        jsonObject.addProperty("", transactionsEntity.getTransactionCategory().getCategoryName());

        return jsonObject;
    }
}
