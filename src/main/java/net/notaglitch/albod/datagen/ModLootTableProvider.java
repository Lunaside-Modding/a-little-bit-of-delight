package net.notaglitch.albod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.notaglitch.albod.block.ModBlocks;
import net.notaglitch.albod.block.custom.BlueberryBushBlock;
import net.notaglitch.albod.item.AModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        this.addDrop(ModBlocks.BLUEBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.builder()
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(ModBlocks.BLUEBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueberryBushBlock.AGE, 3))
                                                ).with(ItemEntry.builder(AModItems.BLUEBERRIES)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                ).pool(LootPool.builder().conditionally(
                                        BlockStatePropertyLootCondition.builder(ModBlocks.BLUEBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueberryBushBlock.AGE, 2))
                                ).with(ItemEntry.builder(AModItems.BLUEBERRIES))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))).build()
                                )
                ));


    }
}
