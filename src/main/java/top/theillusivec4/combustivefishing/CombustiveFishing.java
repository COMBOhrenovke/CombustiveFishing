package top.theillusivec4.combustivefishing;

import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.EntityType;
import net.minecraft.init.Fluids;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.combustivefishing.client.RenderBlazingHook;
import top.theillusivec4.combustivefishing.common.entity.EntityBlazingHook;
import top.theillusivec4.combustivefishing.common.entity.EntityCombustiveCod;
import top.theillusivec4.combustivefishing.common.init.CombustiveFishingEntities;
import top.theillusivec4.combustivefishing.common.item.ItemBlazingFishingRod;
import top.theillusivec4.combustivefishing.common.item.ItemBucketLavaFish;

@Mod(CombustiveFishing.MODID)
public class CombustiveFishing {

    public static final String MODID = "combustivefishing";

    public CombustiveFishing() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        RenderingRegistry.registerEntityRenderingHandler(EntityBlazingHook.class, RenderBlazingHook::new);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> evt) {
            evt.getRegistry().registerAll(
                    new ItemBucketLavaFish(CombustiveFishingEntities.COMBUSTIVE_COD, Fluids.LAVA),
                    new ItemBlazingFishingRod());
        }

        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> evt) {
            evt.getRegistry().registerAll(CombustiveFishingEntities.COMBUSTIVE_COD, CombustiveFishingEntities.BLAZING_BOBBER);
        }
    }
}
