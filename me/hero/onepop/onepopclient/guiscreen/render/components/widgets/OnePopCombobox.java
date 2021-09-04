package me.hero.onepop.onepopclient.guiscreen.render.components.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopAbstractWidget;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopFrame;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopModuleButton;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;

public class OnePopCombobox extends OnePopAbstractWidget {
   private ArrayList<String> values = new ArrayList();
   private OnePopFrame frame;
   private OnePopModuleButton master;
   private OnePopSetting setting;
   private String combobox_name;
   private int x;
   private int y;
   private int width;
   private int height;
   private int combobox_actual_value;
   private int save_y;
   private boolean can;
   private OnePopDraw font = new OnePopDraw(1.0F);
   private int border_size = 0;

   public OnePopCombobox(OnePopFrame frame, OnePopModuleButton master, String tag, int update_postion) {
      this.frame = frame;
      this.master = master;
      this.setting = OnePop.get_setting_manager().get_setting_with_tag(master.get_module(), tag);
      this.x = master.get_x();
      this.y = update_postion;
      this.save_y = this.y;
      this.width = master.get_width();
      this.height = this.font.get_string_height();
      this.combobox_name = this.setting.get_name();
      this.can = true;
      int count = 0;

      for(Iterator var6 = this.setting.get_values().iterator(); var6.hasNext(); ++count) {
         String values = (String)var6.next();
         this.values.add(values);
      }

      for(int i = 0; i >= this.values.size(); ++i) {
         if (((String)this.values.get(i)).equals(this.setting.get_current_value())) {
            this.combobox_actual_value = i;
            break;
         }
      }

   }

   public OnePopSetting get_setting() {
      return this.setting;
   }

   public void does_can(boolean value) {
      this.can = value;
   }

   public void set_x(int x) {
      this.x = x;
   }

   public void set_y(int y) {
      this.y = y;
   }

   public void set_width(int width) {
      this.width = width;
   }

   public void set_height(int height) {
      this.height = height;
   }

   public int get_x() {
      return this.x;
   }

   public int get_y() {
      return this.y;
   }

   public int get_width() {
      return this.width;
   }

   public int get_height() {
      return this.height;
   }

   public int get_save_y() {
      return this.save_y;
   }

   public boolean motion_pass(int mx, int my) {
      return this.motion(mx, my);
   }

   public boolean motion(int mx, int my) {
      return mx >= this.get_x() && my >= this.get_save_y() && mx <= this.get_x() + this.get_width() && my <= this.get_save_y() + this.get_height();
   }

   public boolean can() {
      return this.can;
   }

   public void mouse(int mx, int my, int mouse) {
      if (mouse == 0 && this.motion(mx, my) && this.master.is_open() && this.can()) {
         this.frame.does_can(false);
         this.setting.set_current_value((String)this.values.get(this.combobox_actual_value));
         ++this.combobox_actual_value;
      }

   }

   public void render(int master_y, int separe, int absolute_x, int absolute_y) {
      this.set_width(this.master.get_width() - separe);
      String zbob = "me";
      this.save_y = this.y + master_y;
      int ns_r = OnePop.click_gui.theme_widget_name_r;
      int ns_g = OnePop.click_gui.theme_widget_name_g;
      int ns_b = OnePop.click_gui.theme_widget_name_b;
      int ns_a = OnePop.click_gui.theme_widget_name_b;
      int bg_r = OnePop.click_gui.theme_widget_background_r;
      int bg_g = OnePop.click_gui.theme_widget_background_g;
      int bg_b = OnePop.click_gui.theme_widget_background_b;
      int bg_a = OnePop.click_gui.theme_widget_background_a;
      int bd_r = OnePop.click_gui.theme_widget_border_r;
      int bd_g = OnePop.click_gui.theme_widget_border_g;
      int bd_b = OnePop.click_gui.theme_widget_border_b;
      int bd_a = true;
      OnePopDraw.draw_string(this.combobox_name + " " + this.setting.get_current_value(), this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
      if (this.combobox_actual_value >= this.values.size()) {
         this.combobox_actual_value = 0;
      }

   }
}
