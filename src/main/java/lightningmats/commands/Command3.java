package lightningmats.commands;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lightningmats.LightningMaterials;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;

public class Command3 extends CommandBase
{
	
	private static final String __OBFID = "CL_00000570";
	public boolean used = false;


	public String getCommandName()
	{
		return "hdtextureson";
	}

	/**
	 * Return the required permission level for this command.
	 */
	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return "/hdtextureson turns HD textures for Lightning Materials on";
	}

	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_)
	{
		LightningMaterials.config.load();
		LightningMaterials.HDTextures = true;
		LightningMaterials.config.get("Client Side", "HD Textures", false).set(true);
		LightningMaterials.config.save();
		p_71515_1_.addChatMessage(new ChatComponentTranslation("§3For §3Textures §3To §3Take §3Effect §3Go §3Into §3Resource §3Packs §3And §3Click §3Done §3This §3Reloads §3The §3Textures", new Object[0]));
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		// TODO Auto-generated method stub

	}
	

}