package me.hero.onepop.onepopclient.hacks.combat;

import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopBlockUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopOldBlockInteractHelper;
import me.hero.onepop.onepopclient.util.OnePopPlayerUtil;
import me.hero.onepop.onepopclient.util.OnePopOldBlockInteractHelper.ValidResult;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class OnePopHoleFill extends OnePopHack {
   OnePopSetting hole_toggle = this.create("Toggle", "HoleFillToggle", true);
   OnePopSetting hole_rotate = this.create("Rotate", "HoleFillRotate", true);
   OnePopSetting hole_range = this.create("Range", "HoleFillRange", 4, 1, 6);
   OnePopSetting swing = this.create("Swing", "HoleFillSwing", "Mainhand", this.combobox(new String[]{"Mainhand", "Offhand", "Both", "None"}));
   private final ArrayList<BlockPos> holes = new ArrayList();

   public OnePopHoleFill() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Cheroso Fill";
      this.tag = "HoleFill";
      this.description = "Turn holes into floors";
   }

   public void enable() {
      if (this.find_in_hotbar() == -1) {
         this.set_disable();
      }

      this.find_new_holes();
   }

   public void disable() {
      this.holes.clear();
   }

   public void update() {
      if (this.find_in_hotbar() == -1) {
         this.disable();
      } else {
         if (this.holes.isEmpty()) {
            if (!this.hole_toggle.get_value(true)) {
               this.set_disable();
               OnePopMessageUtil.toggle_message(this);
               return;
            }

            this.find_new_holes();
         }

         BlockPos pos_to_fill = null;
         Iterator var2 = (new ArrayList(this.holes)).iterator();

         while(var2.hasNext()) {
            BlockPos pos = (BlockPos)var2.next();
            if (pos != null) {
               ValidResult result = OnePopOldBlockInteractHelper.valid(pos);
               if (result == ValidResult.Ok) {
                  pos_to_fill = pos;
                  break;
               }

               this.holes.remove(pos);
            }
         }

         if (this.find_in_hotbar() == -1) {
            this.disable();
         } else {
            if (pos_to_fill != null && OnePopBlockUtil.placeBlock(pos_to_fill, this.find_in_hotbar(), this.hole_rotate.get_value(true), this.hole_rotate.get_value(true), this.swing)) {
               this.holes.remove(pos_to_fill);
            }

         }
      }
   }

   public void find_new_holes() {
      this.holes.clear();
      Iterator var1 = OnePopOldBlockInteractHelper.getSphere(OnePopPlayerUtil.GetLocalPlayerPosFloored(), (float)this.hole_range.get_value(1), this.hole_range.get_value(1), false, true, 0).iterator();

      while(true) {
         BlockPos pos;
         do {
            do {
               do {
                  if (!var1.hasNext()) {
                     return;
                  }

                  pos = (BlockPos)var1.next();
               } while(!mc.field_71441_e.func_180495_p(pos).func_177230_c().equals(Blocks.field_150350_a));
            } while(!mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 0)).func_177230_c().equals(Blocks.field_150350_a));
         } while(!mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150350_a));

         boolean possible = true;
         BlockPos[] var4 = new BlockPos[]{new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0)};
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            BlockPos seems_blocks = var4[var6];
            Block block = mc.field_71441_e.func_180495_p(pos.func_177971_a(seems_blocks)).func_177230_c();
            if (block != Blocks.field_150357_h && block != Blocks.field_150343_Z && block != Blocks.field_150477_bB && block != Blocks.field_150467_bQ) {
               possible = false;
               break;
            }
         }

         if (possible) {
            this.holes.add(pos);
         }
      }
   }

   private int find_in_hotbar() {
      for(int i = 0; i < 9; ++i) {
         ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
         if (stack != ItemStack.field_190927_a && stack.func_77973_b() instanceof ItemBlock) {
            Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
            if (block instanceof BlockEnderChest) {
               return i;
            }

            if (block instanceof BlockObsidian) {
               return i;
            }
         }
      }

      return -1;
   }
}
