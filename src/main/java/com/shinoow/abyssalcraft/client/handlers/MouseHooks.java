package com.shinoow.abyssalcraft.client.handlers;

import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.client.ClientProxy;
import com.shinoow.abyssalcraft.common.items.ItemConfigurator;
import com.shinoow.abyssalcraft.common.network.PacketDispatcher;
import com.shinoow.abyssalcraft.common.network.server.ConfiguratorMessage;
import com.shinoow.abyssalcraft.common.network.server.InterdimensionalCageMessage;
import com.shinoow.abyssalcraft.common.network.server.StaffModeMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

import java.util.List;

import static com.shinoow.abyssalcraft.client.lib.WorldUtil.extinguishFire;

public class MouseHooks {

    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        int button = event.getButton() - 100;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        World world = mc.world;
        int key = mc.gameSettings.keyBindAttack.getKeyCode();

        if (button == key && Mouse.isButtonDown(button + 100))
            if(mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK){
                BlockPos pos = mc.objectMouseOver.getBlockPos();
                EnumFacing face = mc.objectMouseOver.sideHit;
                if(pos != null && face != null)
                    if (world.getBlockState(pos).getBlock() != null)
                        extinguishFire(player, pos, face, world, event);
            }
    }

    @SideOnly(Side.CLIENT)
    public static RayTraceResult getMouseOverExtended(float dist)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Entity theRenderViewEntity = mc.getRenderViewEntity();
        AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
                theRenderViewEntity.posX-0.5D,
                theRenderViewEntity.posY-0.0D,
                theRenderViewEntity.posZ-0.5D,
                theRenderViewEntity.posX+0.5D,
                theRenderViewEntity.posY+1.5D,
                theRenderViewEntity.posZ+0.5D
        );
        RayTraceResult returnMOP = null;
        if (mc.world != null)
        {
            double var2 = dist;
            returnMOP = theRenderViewEntity.rayTrace(var2, 0);
            double calcdist = var2;
            Vec3d pos = theRenderViewEntity.getPositionEyes(0);
            var2 = calcdist;
            if (returnMOP != null)
                calcdist = returnMOP.hitVec.distanceTo(pos);

            Vec3d lookvec = theRenderViewEntity.getLook(0);
            Vec3d var8 = pos.add(lookvec.x * var2,
                    lookvec.y * var2,
                    lookvec.z * var2);
            Entity pointedEntity = null;
            float var9 = 1.0F;
            List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(
                    theRenderViewEntity,
                    theViewBoundingBox.expand(
                            lookvec.x * var2,
                            lookvec.y * var2,
                            lookvec.z * var2).grow(var9, var9, var9));
            double d = calcdist;

            for (Entity entity : list)
                if (entity.canBeCollidedWith())
                {
                    float bordersize = entity.getCollisionBorderSize();
                    AxisAlignedBB aabb = new AxisAlignedBB(
                            entity.posX-entity.width/2,
                            entity.posY,
                            entity.posZ-entity.width/2,
                            entity.posX+entity.width/2,
                            entity.posY+entity.height,
                            entity.posZ+entity.width/2);
                    aabb.expand(bordersize, bordersize, bordersize);
                    RayTraceResult mop0 = aabb.calculateIntercept(pos, var8);

                    if (aabb.contains(pos))
                    {
                        if (0.0D < d || d == 0.0D)
                        {
                            pointedEntity = entity;
                            d = 0.0D;
                        }
                    } else if (mop0 != null)
                    {
                        double d1 = pos.distanceTo(mop0.hitVec);

                        if (d1 < d || d == 0.0D)
                        {
                            pointedEntity = entity;
                            d = d1;
                        }
                    }
                }

            if (pointedEntity != null && (d < calcdist || returnMOP == null))
                returnMOP = new RayTraceResult(pointedEntity);
        }
        return returnMOP;
    }

    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        ACItems items = ACItems.getInstance();

        if(ClientProxy.staff_mode.isPressed()){
            ItemStack mainStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack offStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.OFF_HAND);
            int mode1 = -1, mode2 = -1;

            if(!mainStack.isEmpty() && mainStack.getItem() == items.staff_of_the_gatekeeper){
                if(!mainStack.hasTagCompound())
                    mainStack.setTagCompound(new NBTTagCompound());
                mode1 = mainStack.getTagCompound().getInteger("Mode");
                if(mode1 > -1){
                    if(mode1 == 0)
                        mode1 = 1;
                    else mode1 = 0;
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(
                            I18n.format("tooltip.staff.mode.1")+": "+
                                    TextFormatting.GOLD + I18n.format(mode1 == 1 ? "item.drainstaff.normal.name" : "item.gatewaykey.name")));
                }
            }
            if(!offStack.isEmpty() && offStack.getItem() == items.staff_of_the_gatekeeper){
                if(!offStack.hasTagCompound())
                    offStack.setTagCompound(new NBTTagCompound());
                mode2 = offStack.getTagCompound().getInteger("Mode");
                if(mode2 > -1){
                    if(mode2 == 0)
                        mode2 = 1;
                    else mode2 = 0;
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("tooltip.staff.mode.1")+": "+TextFormatting.GOLD + I18n.format(mode2 == 1 ? "item.drainstaff.normal.name" : "item.gatewaykey.name")));
                }
            }

            if(mode1 > -1 || mode2 > -1)
                PacketDispatcher.sendToServer(new StaffModeMessage());
        }
        if(ClientProxy.use_cage.isPressed()) {
            ItemStack mainStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack offStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.OFF_HAND);

            if(!mainStack.isEmpty() && mainStack.getItem() == items.interdimensional_cage) {
                if(!mainStack.hasTagCompound())
                    mainStack.setTagCompound(new NBTTagCompound());
                if(!mainStack.getTagCompound().hasKey("Entity")) {
                    RayTraceResult mov = getMouseOverExtended(3);

                    if (mov != null)
                        if (mov.entityHit != null && !mov.entityHit.isDead)
                            if (mov.entityHit != Minecraft.getMinecraft().player )
                                PacketDispatcher.sendToServer(new InterdimensionalCageMessage(mov.entityHit.getEntityId(), EnumHand.MAIN_HAND));
                }
            }
            if (!offStack.isEmpty() && offStack.getItem() == items.interdimensional_cage) {
                if(!offStack.hasTagCompound())
                    offStack.setTagCompound(new NBTTagCompound());
                if(!offStack.getTagCompound().hasKey("Entity")) {
                    RayTraceResult mov = getMouseOverExtended(3);

                    if (mov != null)
                        if (mov.entityHit != null && !mov.entityHit.isDead)
                            if (mov.entityHit != Minecraft.getMinecraft().player )
                                PacketDispatcher.sendToServer(new InterdimensionalCageMessage(mov.entityHit.getEntityId(), EnumHand.OFF_HAND));
                }
            }
        }
        if(ClientProxy.configurator_mode.isPressed()) {
            ItemStack mainStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack offStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.OFF_HAND);
            int mode1 = -1, mode2 = -1;

            if(!mainStack.isEmpty() && mainStack.getItem() == items.configurator){
                if(!mainStack.hasTagCompound())
                    mainStack.setTagCompound(new NBTTagCompound());
                mode1 = mainStack.getTagCompound().getInteger("Mode");
                if(mode1 > -1){
                    mode1 = mode1 == 0 ? 1 : mode1 == 1 ? 2 : 0;
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("tooltip.staff.mode.1")+": "+TextFormatting.GOLD + ItemConfigurator.getMode(mode1)));
                }
            }
            if(!offStack.isEmpty() && offStack.getItem() == items.configurator){
                if(!offStack.hasTagCompound())
                    offStack.setTagCompound(new NBTTagCompound());
                mode2 = offStack.getTagCompound().getInteger("Mode");
                if(mode2 > -1){
                    mode2 = mode2 == 0 ? 1 : mode2 == 1 ? 2 : 0;
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("tooltip.staff.mode.1")+": "+TextFormatting.GOLD + ItemConfigurator.getMode(mode2)));
                }
            }

            if(mode1 > -1 || mode2 > -1)
                PacketDispatcher.sendToServer(new ConfiguratorMessage(mode1, mode2));
        }
        if(ClientProxy.configurator_filter.isPressed()) {
            ItemStack mainStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack offStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.OFF_HAND);
            if(!mainStack.isEmpty() && mainStack.getItem() == items.configurator ||
                    !offStack.isEmpty() && offStack.getItem() == items.configurator)
                PacketDispatcher.sendToServer(new ConfiguratorMessage(true));
        }
        if(ClientProxy.configurator_path.isPressed()) {
            ItemStack mainStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
            ItemStack offStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.OFF_HAND);
            if(!mainStack.isEmpty() && mainStack.getItem() == items.configurator ||
                    !offStack.isEmpty() && offStack.getItem() == items.configurator) {
                PacketDispatcher.sendToServer(new ConfiguratorMessage(true, 0));
                Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("message.configurator.5"));
            }
        }
    }

}
