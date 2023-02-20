package dev.waterlilly.gateway.init

import dev.waterlilly.gateway.network.packet.l2r.EstablishConnectionL2RPacket
import net.minecraft.network.ClientConnection
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.PacketSender
import org.quiltmc.qsl.networking.api.ServerPlayNetworking
import java.net.ConnectException
import java.net.InetSocketAddress

class GatewayInit : ModInitializer {
    override fun onInitialize(mod: ModContainer?) {
        ServerPlayNetworking.registerGlobalReceiver(Identifier("gateway:ping")) { minecraftServer: MinecraftServer, serverPlayerEntity: ServerPlayerEntity, serverPlayNetworkHandler: ServerPlayNetworkHandler, packetByteBuf: PacketByteBuf, packetSender: PacketSender ->
            println("Received ping packet!")
        }
        ServerLifecycleEvents.READY.register() {
            try {
                println("Sending packet...")
                val conn = ClientConnection.connect(InetSocketAddress("localhost", Integer.parseInt(System.getenv("S2S_PORT"))), false)
                val buf = PacketByteBufs.create()
                EstablishConnectionL2RPacket().write(buf)
                conn.send(ServerPlayNetworking.createS2CPacket(Identifier("gateway:ping"), buf))
            } catch(e: ConnectException) {
                e.printStackTrace()
            }
            println("Sent packet!")
        }
    }
}
