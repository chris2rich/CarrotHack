package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import net.minecraft.client.Minecraft;

public class OnePopFPS extends OnePopPinnable {
   public OnePopFPS() {
      super("Fps", "Fps", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String line = "FPS: " + this.get_fps();
      this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(line, "width") + 2);
      this.set_height(this.get(line, "height") + 2);
   }

   public String get_fps() {
      Minecraft var10000 = this.mc;
      int fps = Minecraft.func_175610_ah();
      if (fps >= 60) {
         return "§a" + Integer.toString(fps);
      } else {
         return fps >= 30 ? "§3" + Integer.toString(fps) : "§4" + Integer.toString(fps);
      }
   }
}
