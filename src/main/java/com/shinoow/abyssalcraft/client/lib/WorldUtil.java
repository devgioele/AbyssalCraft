package com.shinoow.abyssalcraft.client.lib;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.network.PacketDispatcher;
import com.shinoow.abyssalcraft.common.network.server.FireMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class WorldUtil {

    public static void extinguishFire(EntityPlayer player, BlockPos posIn, EnumFacing face, World world, Event event) {
        BlockPos pos = posIn.offset(face);

        if (world.getBlockState(pos).getBlock() == ACBlocks.mimic_fire)
            if (event instanceof MouseEvent) {
                PacketDispatcher.sendToServer(new FireMessage(pos));
                player.swingArm(EnumHand.MAIN_HAND);
                event.setCanceled(true);
            }
    }

}
