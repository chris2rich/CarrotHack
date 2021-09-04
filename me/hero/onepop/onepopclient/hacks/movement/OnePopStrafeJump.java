package me.hero.onepop.onepopclient.hacks.movement;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventMove;
import me.hero.onepop.onepopclient.event.events.OnePopEventPlayerJump;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.util.math.MathHelper;

public class OnePopStrafeJump extends OnePopHack {
   OnePopSetting speed_mode = this.create("Mode", "StrafeMode", "Strafe", this.combobox(new String[]{"Strafe", "On Ground"}));
   OnePopSetting auto_sprint = this.create("Auto Sprint", "StrafeSprint", true);
   OnePopSetting on_water = this.create("On Water", "StrafeOnWater", true);
   OnePopSetting backward = this.create("Backwards", "StrafeBackwards", true);
   OnePopSetting bypass = this.create("Bypass", "StrafeBypass", true);
   @EventHandler
   private Listener<OnePopEventPlayerJump> on_jump = new Listener((event) -> {
      if (this.speed_mode.in("Strafe")) {
         event.cancel();
      }

   }, new Predicate[0]);
   @EventHandler
   private Listener<OnePopEventMove> player_move = new Listener((event) -> {
      if (!this.speed_mode.in("On Ground")) {
         if (!mc.field_71439_g.func_70090_H() && !mc.field_71439_g.func_180799_ab() || this.speed_mode.get_value(true)) {
            if (!mc.field_71439_g.func_70093_af() && !mc.field_71439_g.func_70617_f_() && !mc.field_71439_g.field_70134_J && !mc.field_71439_g.func_180799_ab() && !mc.field_71439_g.func_70090_H() && !mc.field_71439_g.field_71075_bZ.field_75100_b) {
               float player_speed = 0.2873F;
               float move_forward = mc.field_71439_g.field_71158_b.field_192832_b;
               float move_strafe = mc.field_71439_g.field_71158_b.field_78902_a;
               float rotation_yaw = mc.field_71439_g.field_70177_z;
               if (mc.field_71439_g.func_70644_a(MobEffects.field_76424_c)) {
                  int amp = mc.field_71439_g.func_70660_b(MobEffects.field_76424_c).func_76458_c();
                  player_speed *= 1.2F * (float)(amp + 1);
               }

               if (!this.bypass.get_value(true)) {
                  player_speed *= 1.0064F;
               }

               if (move_forward == 0.0F && move_strafe == 0.0F) {
                  event.set_x(0.0D);
                  event.set_z(0.0D);
               } else {
                  if (move_forward != 0.0F) {
                     if (move_strafe > 0.0F) {
                        rotation_yaw += (float)(move_forward > 0.0F ? -45 : 45);
                     } else if (move_strafe < 0.0F) {
                        rotation_yaw += (float)(move_forward > 0.0F ? 45 : -45);
                     }

                     move_strafe = 0.0F;
                     if (move_forward > 0.0F) {
                        move_forward = 1.0F;
                     } else if (move_forward < 0.0F) {
                        move_forward = -1.0F;
                     }
                  }

                  event.set_x((double)(move_forward * player_speed) * Math.cos(Math.toRadians((double)(rotation_yaw + 90.0F))) + (double)(move_strafe * player_speed) * Math.sin(Math.toRadians((double)(rotation_yaw + 90.0F))));
                  event.set_z((double)(move_forward * player_speed) * Math.sin(Math.toRadians((double)(rotation_yaw + 90.0F))) - (double)(move_strafe * player_speed) * Math.cos(Math.toRadians((double)(rotation_yaw + 90.0F))));
               }

               event.cancel();
            }
         }
      }
   }, new Predicate[0]);

   public OnePopStrafeJump() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "StrafeJump";
      this.tag = "StrafeJump";
      this.description = "Strafe que pula";
   }

   public void update() {
      if (!mc.field_71439_g.func_184218_aH()) {
         if (!mc.field_71439_g.func_70093_af()) {
            if (!mc.field_71439_g.func_70090_H() && !mc.field_71439_g.func_180799_ab() || this.on_water.get_value(true)) {
               if (mc.field_71439_g.field_191988_bg != 0.0F || mc.field_71439_g.field_70702_br != 0.0F) {
                  if (mc.field_71439_g.field_191988_bg < 0.0F && !this.backward.get_value(true)) {
                     return;
                  }

                  if (this.auto_sprint.get_value(true)) {
                     mc.field_71439_g.func_70031_b(true);
                  }

                  EntityPlayerSP var10000;
                  float yaw;
                  if (mc.field_71439_g.field_70122_E && this.speed_mode.in("Strafe")) {
                     mc.field_71439_g.field_70181_x = 0.4050000011920929D;
                     yaw = this.get_rotation_yaw() * 0.017453292F;
                     var10000 = mc.field_71439_g;
                     var10000.field_70159_w -= (double)(MathHelper.func_76126_a(yaw) * 0.2F);
                     var10000 = mc.field_71439_g;
                     var10000.field_70179_y += (double)(MathHelper.func_76134_b(yaw) * 0.2F);
                  } else if (mc.field_71439_g.field_70122_E && this.speed_mode.in("On Ground")) {
                     yaw = this.get_rotation_yaw();
                     var10000 = mc.field_71439_g;
                     var10000.field_70159_w -= (double)(MathHelper.func_76126_a(yaw) * 0.2F);
                     var10000 = mc.field_71439_g;
                     var10000.field_70179_y += (double)(MathHelper.func_76134_b(yaw) * 0.2F);
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.4D, mc.field_71439_g.field_70161_v, false));
                  }
               }

               if (mc.field_71474_y.field_74314_A.func_151470_d() && mc.field_71439_g.field_70122_E) {
                  mc.field_71439_g.field_70181_x = 0.4050000011920929D;
               }

            }
         }
      }
   }

   private float get_rotation_yaw() {
      float rotation_yaw = mc.field_71439_g.field_70177_z;
      if (mc.field_71439_g.field_191988_bg < 0.0F) {
         rotation_yaw += 180.0F;
      }

      float n = 1.0F;
      if (mc.field_71439_g.field_191988_bg < 0.0F) {
         n = -0.5F;
      } else if (mc.field_71439_g.field_191988_bg > 0.0F) {
         n = 0.5F;
      }

      if (mc.field_71439_g.field_70702_br > 0.0F) {
         rotation_yaw -= 90.0F * n;
      }

      if (mc.field_71439_g.field_70702_br < 0.0F) {
         rotation_yaw += 90.0F * n;
      }

      return rotation_yaw * 0.017453292F;
   }
}
