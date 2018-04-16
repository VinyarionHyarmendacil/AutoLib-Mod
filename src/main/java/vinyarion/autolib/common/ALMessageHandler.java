package vinyarion.autolib.common;

import java.io.IOException;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;

public class ALMessageHandler implements IMessageHandler<ALMessageHandler.ALMessage, ALMessageHandler.ALMessage> {
	
	public ALMessage onMessage(ALMessage message, MessageContext ctx) {
		if(message.getIsAL()) {
			return ALMod.instance.proxy.onMessage(message, ctx);
		} else {
			ALMessageRecievedEvent event = new ALMessageRecievedEvent(message, ctx);
			MinecraftForge.EVENT_BUS.post(event);
			if(event.isCanceled()) {
				return null;
			} else {
				return event.returnMessage;
			}
		}
	}
	
	public static class ALMessage implements IMessage {
		
		public ALMessage() {
			this.nbt = new NBTTagCompound();
			this.isAL = false;
		}
		
		public ALMessage(NBTTagCompound nbt) {
			this(nbt, false);
		}
		
		public ALMessage(NBTTagCompound nbt, boolean isAL) {
			this.nbt = nbt;
			this.isAL = isAL;
		}
		
		private NBTTagCompound nbt;
		
		private boolean isAL;
		
		public NBTTagCompound getTag() {
			return this.nbt;
		}
		
		public boolean getIsAL() {
			return this.isAL;
		}
		
		public void fromBytes(ByteBuf buf) {
			PacketBuffer pb = new PacketBuffer(buf);
			pb.writeBoolean(isAL);
			int length = pb.readInt();
			if(length == -1) {
				nbt = new NBTTagCompound();
				return;
			}
			byte[] compressed = pb.readBytes(length).array();
			try {
				nbt = CompressedStreamTools.func_152457_a(compressed, NBTSizeTracker.field_152451_a);
			} catch (IOException e) {
				nbt = new NBTTagCompound();
			}
		}
		
		public void toBytes(ByteBuf buf) {
			PacketBuffer pb = new PacketBuffer(buf);
			isAL = pb.readBoolean();
			byte[] compressed;
			try {
				compressed = CompressedStreamTools.compress(nbt);
			} catch (IOException e) {
				compressed = new byte[0];
				pb.writeInt(-1);
				return;
			}
			pb.writeInt(compressed.length);
			pb.writeBytes(compressed);
		}
		
	}
	
}
