package ru.bgbrakhi.ood.store;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class DispatchDiapason {
    public static final int TYPE_WAREHOUSE     = 1;
    public static final int TYPE_SHOP          = 2;
    public static final int TYPE_SHOP_DISCOUNT = 3;

    private final Map<Function<Food, Boolean>, Integer> dispatch = new LinkedHashMap<>();


    public DispatchDiapason init(Date onDate) {
        dispatch.put(
                food -> food.getExpirePercent(onDate) <= 0.25,
                TYPE_WAREHOUSE
        );
        dispatch.put(
                food -> food.getExpirePercent(onDate) > 0.25 && food.getExpirePercent(onDate) <= 0.75,
                TYPE_SHOP
        );
        dispatch.put(
                food -> food.getExpirePercent(onDate) > 0.75 && food.getExpirePercent(onDate) <= 1,
                TYPE_SHOP_DISCOUNT
        );
        return this;
    }

    public Integer getType(Food food) {
        for (Function<Food, Boolean> function : dispatch.keySet()) {
            if (function.apply(food)) {
                return dispatch.get(function);
            }
        }
        throw new IllegalStateException("Could not found food type");
    }
}
