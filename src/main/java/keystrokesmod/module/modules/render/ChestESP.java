package keystrokesmod.module.modules.render;

import keystrokesmod.module.Module;
import keystrokesmod.module.ModuleSettingSlider;
import keystrokesmod.module.ModuleSettingTick;
import keystrokesmod.module.modules.client.SelfDestruct;
import keystrokesmod.utils.Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.Iterator;

public class ChestESP extends Module {
   public static ModuleSettingSlider a;
   public static ModuleSettingSlider b;
   public static ModuleSettingSlider c;
   public static ModuleSettingTick d;

   public ChestESP() {
      super("ChestESP", Module.category.render, 0);
      a = new ModuleSettingSlider("Red", 0.0D, 0.0D, 255.0D, 1.0D);
      b = new ModuleSettingSlider("Green", 0.0D, 0.0D, 255.0D, 1.0D);
      c = new ModuleSettingSlider("Blue", 255.0D, 0.0D, 255.0D, 1.0D);
      d = new ModuleSettingTick("Rainbow", false);
      this.registerSetting(a);
      this.registerSetting(b);
      this.registerSetting(c);
      this.registerSetting(d);
   }

   @SubscribeEvent
   public void onRenderWorldLast(RenderWorldLastEvent ev) {
      if (Utils.Player.isPlayerInGame()) {
         int rgb = d.isToggled() ? Utils.Client.rainbowDraw(2L, 0L) : (new Color((int)a.getInput(), (int)b.getInput(), (int)c.getInput())).getRGB();
         Iterator var3 = mc.theWorld.loadedTileEntityList.iterator();

         while(true) {
            TileEntity te;
            do {
               if (!var3.hasNext()) {
                  return;
               }

               te = (TileEntity)var3.next();
            } while(!(te instanceof TileEntityChest) && !(te instanceof TileEntityEnderChest));

            Utils.HUD.re(te.getPos(), rgb, true);
         }
      }
   }
}
