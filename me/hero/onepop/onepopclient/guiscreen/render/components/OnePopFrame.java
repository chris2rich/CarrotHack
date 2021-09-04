package me.hero.onepop.onepopclient.guiscreen.render.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.client.Minecraft;

public class OnePopFrame {
   private OnePopCategory category;
   private ArrayList<OnePopModuleButton> module_button;
   private int x = 10;
   private int y = 10;
   private int width = 100;
   private int height;
   private int width_name;
   private int width_abs;
   private String frame_name;
   private String frame_tag;
   private OnePopDraw font = new OnePopDraw(1.0F);
   private boolean first = false;
   private boolean move;
   private int move_x;
   private int move_y;
   private boolean can;
   private final Minecraft mc = Minecraft.func_71410_x();

   public OnePopFrame(OnePopCategory category) {
      this.height = 2 + this.font.get_string_height() + 2 + 1;
      this.category = category;
      this.module_button = new ArrayList();
      this.width_name = this.font.get_string_width(this.category.get_name());
      this.width_abs = this.width_name;
      this.frame_name = category.get_name();
      this.frame_tag = category.get_tag();
      this.move_x = 0;
      this.move_y = 0;
      OnePop.get_hack_manager().get_modules_with_category(category).size();
      int count = false;

      OnePopModuleButton buttons;
      for(Iterator var4 = OnePop.get_hack_manager().get_modules_with_category(category).iterator(); var4.hasNext(); this.height += buttons.get_height() + 1) {
         OnePopHack modules = (OnePopHack)var4.next();
         buttons = new OnePopModuleButton(modules, this);
         buttons.set_y(this.height);
         this.module_button.add(buttons);
      }

      this.move = false;
      this.can = true;
   }

   public void refresh_frame(OnePopModuleButton button, int combo_height) {
      this.height = 2 + this.font.get_string_height() + 2 + 1;
      OnePop.get_hack_manager().get_modules_with_category(this.category).size();
      int count = false;
      Iterator var5 = this.module_button.iterator();

      while(var5.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var5.next();
         buttons.set_y(this.height);
         if (buttons.is_open()) {
            this.height += buttons.get_settings_height() + 1;
         } else {
            this.height += buttons.get_height() + 1;
         }
      }

   }

   public void does_can(boolean value) {
      this.can = value;
   }

   public void set_move(boolean value) {
      this.move = value;
   }

   public void set_move_x(int x) {
      this.move_x = x;
   }

   public void set_move_y(int y) {
      this.move_y = y;
   }

   public void set_width(int width) {
      this.width = width;
   }

   public void set_height(int height) {
      this.height = height;
   }

   public void set_x(int x) {
      this.x = x;
   }

   public void set_y(int y) {
      this.y = y;
   }

   public String get_name() {
      return this.frame_name;
   }

   public String get_tag() {
      return this.frame_tag;
   }

   public boolean is_moving() {
      return this.move;
   }

   public int get_width() {
      return this.width;
   }

   public int get_height() {
      return this.height;
   }

   public int get_x() {
      return this.x;
   }

   public int get_y() {
      return this.y;
   }

   public boolean can() {
      return this.can;
   }

   public boolean motion(int mx, int my) {
      return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_x() + this.get_width() && my <= this.get_y() + this.get_height();
   }

   public boolean motion(String tag, int mx, int my) {
      return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_x() + this.get_width() && my <= this.get_y() + this.font.get_string_height();
   }

   public void crush(int mx, int my) {
      int screen_x = this.mc.field_71443_c / 2;
      int screen_y = this.mc.field_71440_d / 2;
      this.set_x(mx - this.move_x);
      this.set_y(my - this.move_y);
      if (this.x + this.width >= screen_x) {
         this.x = screen_x - this.width - 1;
      }

      if (this.x <= 0) {
         this.x = 1;
      }

      if (this.y + this.height >= screen_y) {
         this.y = screen_y - this.height - 1;
      }

      if (this.y <= 0) {
         this.y = 1;
      }

      if (this.x % 2 != 0) {
         this.x += this.x % 2;
      }

      if (this.y % 2 != 0) {
         this.y += this.y % 2;
      }

   }

   public boolean is_binding() {
      boolean value_requested = false;
      Iterator var2 = this.module_button.iterator();

      while(var2.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var2.next();
         if (buttons.is_binding()) {
            value_requested = true;
         }
      }

      return value_requested;
   }

   public void does_button_for_do_widgets_can(boolean can) {
      Iterator var2 = this.module_button.iterator();

      while(var2.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var2.next();
         buttons.does_widgets_can(can);
      }

   }

   public void bind(char char_, int key) {
      Iterator var3 = this.module_button.iterator();

      while(var3.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var3.next();
         buttons.bind(char_, key);
      }

   }

   public void mouse(int mx, int my, int mouse) {
      Iterator var4 = this.module_button.iterator();

      while(var4.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var4.next();
         buttons.mouse(mx, my, mouse);
      }

   }

   public void mouse_release(int mx, int my, int mouse) {
      Iterator var4 = this.module_button.iterator();

      while(var4.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var4.next();
         buttons.button_release(mx, my, mouse);
      }

   }

   public void render(int mx, int my) {
      float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int color_a = Color.HSBtoRGB(tick_color[0], 1.0F, 1.0F);
      int border_a;
      if (color_a <= 50) {
         border_a = 50;
      } else {
         border_a = Math.min(color_a, 120);
      }

      int nc_r = OnePop.click_gui.theme_frame_name_r;
      int nc_g = OnePop.click_gui.theme_frame_name_g;
      int nc_b = OnePop.click_gui.theme_frame_name_b;
      int nc_a = OnePop.click_gui.theme_frame_name_a;
      int bg_r = OnePop.click_gui.theme_frame_background_r;
      int bg_g = OnePop.click_gui.theme_frame_background_g;
      int bg_b = OnePop.click_gui.theme_frame_background_b;
      int bg_a = OnePop.click_gui.theme_frame_background_a;
      int bd_r = OnePop.click_gui.theme_frame_border_r;
      int bd_g = OnePop.click_gui.theme_frame_border_g;
      int bd_b = OnePop.click_gui.theme_frame_border_b;
      this.frame_name = this.category.get_name();
      this.width_name = this.font.get_string_width(this.category.get_name());
      OnePopDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, bg_r, bg_g, bg_b, bg_a);
      int border_size = 1;
      OnePopDraw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, bd_r, bd_g, bd_b, border_a, border_size, "left-right");
      OnePopDraw.draw_string(this.frame_name, this.x + 5, this.y + 2, nc_r, nc_g, nc_b, nc_a);
      if (this.is_moving()) {
         this.crush(mx, my);
      }

      Iterator var19 = this.module_button.iterator();

      while(var19.hasNext()) {
         OnePopModuleButton buttons = (OnePopModuleButton)var19.next();
         buttons.set_x(this.x + 2);
         buttons.render(mx, my, 2);
      }

      int var10002 = tick_color[0]++;
   }
}
