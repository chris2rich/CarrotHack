package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class OnePopTotemCount extends OnePopPinnable {
   int totems = 0;

   public OnePopTotemCount() {
      super("Totem Count", "TotemCount", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      if (this.mc.field_71439_g != null) {
         if (this.is_on_gui()) {
            this.create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 50);
         }

         GlStateManager.func_179094_E();
         RenderHelper.func_74520_c();
         this.totems = this.mc.field_71439_g.field_71071_by.field_70462_a.stream().filter((stackx) -> {
            return stackx.func_77973_b() == Items.field_190929_cY;
         }).mapToInt(ItemStack::func_190916_E).sum();
         int off = 0;

         for(int i = 0; i < 45; ++i) {
            ItemStack stack = this.mc.field_71439_g.field_71071_by.func_70301_a(i);
            ItemStack off_h = this.mc.field_71439_g.func_184592_cb();
            if (off_h.func_77973_b() == Items.field_190929_cY) {
               off = off_h.func_77976_d();
            } else {
               off = 0;
            }

            if (stack.func_77973_b() == Items.field_190929_cY) {
               this.mc.func_175599_af().func_180450_b(stack, this.get_x(), this.get_y());
               this.create_line(Integer.toString(this.totems + off), 18, 16 - this.get(Integer.toString(this.totems + off), "height"), nl_r, nl_g, nl_b, nl_a);
            }
         }

         this.mc.func_175599_af().field_77023_b = 0.0F;
         RenderHelper.func_74518_a();
         GlStateManager.func_179121_F();
         this.set_width(16 + this.get(Integer.toString(this.totems + off), "width") + 2);
         this.set_height(16);
      }

   }
}
