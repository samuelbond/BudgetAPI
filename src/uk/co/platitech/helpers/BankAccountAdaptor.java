package uk.co.platitech.helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import uk.co.platitech.BankAccountEntity;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Created by samuel on 11/11/14.
 */
public class BankAccountAdaptor implements JsonSerializer<BankAccountEntity> {

    @Override
    public JsonElement serialize(BankAccountEntity accountEntity, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account_id", accountEntity.getId());
        jsonObject.addProperty("account_name", accountEntity.getAccountName());
        jsonObject.addProperty("account_number", accountEntity.getAccountNumber());
        jsonObject.addProperty("currency", accountEntity.getCurrenciesEntity().getCode());
        jsonObject.addProperty("country", accountEntity.getCurrenciesEntity().getCountry());
        jsonObject.addProperty("balance", ((accountEntity.getAccountBalance() == null || accountEntity.getAccountBalance().getBalance() == null) ? 0.00 : accountEntity.getAccountBalance().getBalance()));
        jsonObject.addProperty("last_balance", ((accountEntity.getAccountBalance() == null || accountEntity.getAccountBalance().getLastBalance() == null) ? 0.00 : accountEntity.getAccountBalance().getLastBalance()));
        return jsonObject;
    }
}
