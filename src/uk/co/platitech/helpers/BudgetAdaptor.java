package uk.co.platitech.helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import uk.co.platitech.BudgetsEntity;

import java.lang.reflect.Type;

/**
 * Created by samuel on 09/12/14.
 */
public class BudgetAdaptor implements JsonSerializer<BudgetsEntity> {
    @Override
    public JsonElement serialize(BudgetsEntity budgetsEntity, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("account_id", budgetsEntity.getBankAccount().getId());
        jsonObject.addProperty("budget_name", budgetsEntity.getBudgetName());
        jsonObject.addProperty("budget_description", budgetsEntity.getBudgetDescription());
        jsonObject.addProperty("budget_id", budgetsEntity.getBudgetId());
        jsonObject.addProperty("budget_max", budgetsEntity.getBudgetMaxAmount());
         return jsonObject;
    }
}
