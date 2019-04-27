package top.theillusivec4.combustivefishing;

import net.minecraft.entity.EntityType;
import net.minecraft.init.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.combustivefishing.common.init.CombustiveFishingEntities;
import top.theillusivec4.combustivefishing.common.init.CombustiveFishingLoot;
import top.theillusivec4.combustivefishing.common.item.*;

@Mod(CombustiveFishing.MODID)
public class CombustiveFishing {

    public static final String MODID = "combustivefishing";

    public CombustiveFishing() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
    }

    private void setup(final FMLCommonSetupEvent evt) {
        CombustiveFishingLoot.registerLootTables();
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        CombustiveFishingEntities.registerEntityRenders();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> evt) {
            evt.getRegistry().registerAll(
                    new ItemBucketLavaFish(CombustiveFishingEntities.COMBUSTIVE_COD, Fluids.LAVA),
                    new ItemBlazingFishingRod(),
                    new ItemCombustiveCod(),
                    new ItemSpawnEgg(CombustiveFishingEntities.COMBUSTIVE_COD, 16699430, 8804608, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(MODID, "combustive_cod_spawn_egg"),
                    new ItemSpawnEgg(CombustiveFishingEntities.SEARING_SWORDFISH, 13045262, 16757683, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(MODID, "searing_swordfish_spawn_egg"),
                    new ItemCooledCod(),
                    new ItemBoneFish(),
                    new ItemSwordfishBill(),
                    new ItemCooledBill(),
                    new ItemSearingSword());
        }

        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> evt) {
            evt.getRegistry().registerAll(
                    CombustiveFishingEntities.COMBUSTIVE_COD,
                    CombustiveFishingEntities.BLAZING_BOBBER,
                    CombustiveFishingEntities.THROWN_COMBUSTIVE_COD,
                    CombustiveFishingEntities.SEARING_SWORDFISH);
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Events {

        @SubscribeEvent
        public static void onPigmanLootTableLoad(final LootTableLoadEvent evt) {

            if (evt.getName().equals(LootTableList.ENTITIES_ZOMBIE_PIGMAN)) {
                LootTable lootTable = evt.getTable();
                LootTable inject = evt.getLootTableManager().getLootTableFromLocation(CombustiveFishingLoot.PIGMAN_INJECT);
                lootTable.addPool(inject.getPool("blazing_fishing_rod"));
                lootTable.addPool(inject.getPool("nether_fish"));
            }
        }
    }
}
