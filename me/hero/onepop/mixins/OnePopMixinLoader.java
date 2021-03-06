package me.hero.onepop.mixins;

import java.util.Map;
import javax.annotation.Nullable;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class OnePopMixinLoader implements IFMLLoadingPlugin {
   public OnePopMixinLoader() {
      MixinBootstrap.init();
      Mixins.addConfiguration("mixins.onepop.json");
      MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
   }

   public String[] getASMTransformerClass() {
      return new String[0];
   }

   public String getModContainerClass() {
      return null;
   }

   @Nullable
   public String getSetupClass() {
      return null;
   }

   public void injectData(Map<String, Object> data) {
   }

   public String getAccessTransformerClass() {
      return null;
   }
}
