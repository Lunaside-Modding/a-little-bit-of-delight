package net.notaglitch.albod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent BLUEBERRY = new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.15f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40), 0.10f).build();


    public static final FoodComponent BLUEBERRY_CHEESECAKE_SLICE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .build();
}
