package dev.waterlilly.gateway.network.packet.l2r

import dev.waterlilly.gateway.network.S2SPacketListener
import net.minecraft.network.Packet
import net.minecraft.network.PacketByteBuf

class EstablishConnectionL2RPacket : Packet<S2SPacketListener> {
    override fun write(buf: PacketByteBuf?) {
    }

    override fun apply(listener: S2SPacketListener?) {
    }
}
