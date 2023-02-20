package dev.waterlilly.gateway.network

import net.minecraft.network.ClientConnection
import net.minecraft.network.listener.PacketListener
import net.minecraft.text.Text

class S2SPacketListener : PacketListener {
    override fun onDisconnected(reason: Text?) {
        TODO("Not yet implemented")
    }

    override fun getConnection(): ClientConnection {
        TODO("Not yet implemented")
    }
}
