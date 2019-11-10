package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.lib.network.packet.IPacketServer;
import cofh.lib.network.packet.PacketBase;
import cofh.lib.util.control.IRedstoneControllable.ControlMode;
import cofh.lib.util.control.IRedstoneControllableTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.lib.util.constants.Constants.PACKET_REDSTONE_CONTROL;

public class PacketRedstoneControl extends PacketBase implements IPacketServer {

    protected BlockPos pos;
    protected int threshold;
    protected byte mode;

    public PacketRedstoneControl() {

        super(PACKET_REDSTONE_CONTROL, CoFHCore.packetHandler);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        World world = player.world;
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IRedstoneControllableTile) {
            ((IRedstoneControllableTile) tile).setControl(threshold, ControlMode.VALUES[mode]);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeBlockPos(pos);
        buf.writeInt(threshold);
        buf.writeByte(mode);
    }

    @Override
    public void read(PacketBuffer buf) {

        pos = buf.readBlockPos();
        threshold = buf.readInt();
        mode = buf.readByte();
    }

    public static void sendToServer(IRedstoneControllableTile tile) {

        PacketRedstoneControl packet = new PacketRedstoneControl();
        packet.pos = tile.pos();
        packet.threshold = tile.redstoneControl().getThreshold();
        packet.mode = (byte) tile.redstoneControl().getMode().ordinal();
        packet.sendToServer();
    }

}
