package uk.co.platitech.helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import uk.co.platitech.AccountBalanceEntity;
import uk.co.platitech.BankAccountEntity;
import uk.co.platitech.components.accountmanager.v1.AccountManagerImp;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Created by samuel on 11/11/14.
 */
public class BankAccountAdaptor implements JsonSerializer<BankAccountEntity> {

    @Override
    public JsonElement serialize(BankAccountEntity accountEntity, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        AccountManagerImp acM = new AccountManagerImp();
        AccountBalanceEntity abe = acM.getUserAccountBalance(accountEntity.getId());
        jsonObject.addProperty("account_id", accountEntity.getId());
        jsonObject.addProperty("account_name", accountEntity.getAccountName());
        jsonObject.addProperty("account_number", accountEntity.getAccountNumber());
        jsonObject.addProperty("currency", accountEntity.getCurrencies().getCode());
        jsonObject.addProperty("country", accountEntity.getCurrencies().getCountry());
        jsonObject.addProperty("balance", ((abe == null) ? 0.00 : abe.getBalance()));
        jsonObject.addProperty("last_balance", ((abe == null) ? 0.00 : abe.getLastBalance()));
        return jsonObject;
    }
}
