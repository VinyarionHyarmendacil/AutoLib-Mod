package vinyarion.autolib.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vinyarion.autolib.api.ALItem;

public class ALDelagatingItem extends Item {

	private static final Item def;
	
	private final Map<String, Item> itemMap = new HashMap<String, Item>();
	
	public ALDelagatingItem() {
		this.setUnlocalizedName("theAutoLibItem");
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		this.get(stack).addInformation(stack, player, list, b);
	}
	
	public boolean canHarvestBlock(Block block, ItemStack stack) {
		return this.get(stack).canHarvestBlock(block, stack);
	}
	
	public Entity createEntity(World world, Entity location, ItemStack stack) {
		return this.get(stack).createEntity(world, location, stack);
	}
	
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return this.get(stack).doesContainerItemLeaveCraftingGrid(stack);
	}
	
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
		return this.get(player.getHeldItem()).doesSneakBypassUse(world, x, y, z, player);
	}
	
	public float func_150893_a(ItemStack stack, Block block) {
		return this.get(stack).func_150893_a(stack, block);
	}
	
	public Item get(ItemStack stack) {
		return itemMap.getOrDefault(stack.hasTagCompound() ? stack.getTagCompound().hasKey("ALItem", 8) : "", def);
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		return this.get(stack).getArmorModel(entityLiving, stack, armorSlot);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return this.get(stack).getArmorTexture(stack, entity, slot, type);
	}
	
	public Multimap getAttributeModifiers(ItemStack stack) {
		return this.get(stack).getAttributeModifiers(stack);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int i) {
		return this.get(stack).getColorFromItemStack(stack, i);
	}
	
	public ItemStack getContainerItem(ItemStack stack) {
		return this.get(stack).getContainerItem(stack);
	}
	
	public int getDamage(ItemStack stack) {
		return this.get(stack).getDamage(stack);
	}
	
	public float getDigSpeed(ItemStack stack, Block block, int metadata) {
		return this.get(stack).getDigSpeed(stack, block, metadata);
	}
	
	public int getDisplayDamage(ItemStack stack) {
		return this.get(stack).getDisplayDamage(stack);
	}
	
	public double getDurabilityForDisplay(ItemStack stack) {
		return this.get(stack).getDurabilityForDisplay(stack);
	}
	
	public int getEntityLifespan(ItemStack stack, World world) {
		return this.get(stack).getEntityLifespan(stack, world);
	}

	@SideOnly(Side.CLIENT)
	public FontRenderer getFontRenderer(ItemStack stack) {
		return this.get(stack).getFontRenderer(stack);
	}
	
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		return this.get(stack).getHarvestLevel(stack, toolClass);
	}
	
	public IIcon getIcon(ItemStack stack, int pass) {
		return this.get(stack).getIcon(stack, pass);
	}
	
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		return this.get(stack).getIcon(stack, renderPass, player, usingItem, useRemaining);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack stack) {
		return this.get(stack).getIconIndex(stack);
	}
	
	public boolean getIsRepairable(ItemStack stack, ItemStack otherstack) {
		return this.get(stack).getIsRepairable(stack, otherstack);
	}
	
	public int getItemEnchantability(ItemStack stack) {
		return this.get(stack).getItemEnchantability(stack);
	}
	
	public String getItemStackDisplayName(ItemStack stack) {
		return this.get(stack).getItemStackDisplayName(stack);
	}
	
	public int getItemStackLimit(ItemStack stack) {
		return this.get(stack).getItemStackLimit(stack);
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
		return this.get(stack).getItemUseAction(stack);
	}
	
	public int getMaxDamage(ItemStack stack) {
		return this.get(stack).getMaxDamage(stack);
	}
	
	public int getMaxItemUseDuration(ItemStack stack) {
		return this.get(stack).getMaxItemUseDuration(stack);
	}
	
	public String getPotionEffect(ItemStack stack) {
		return this.get(stack).getPotionEffect(stack);
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		return this.get(stack).getRarity(stack);
	}
	
	public float getSmeltingExperience(ItemStack stack) {
		return this.get(stack).getSmeltingExperience(stack);
	}
	
	public Set<String> getToolClasses(ItemStack stack) {
		return this.get(stack).getToolClasses(stack);
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return this.get(stack).getUnlocalizedName(stack);
	}
	
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return this.get(stack).getUnlocalizedNameInefficiently(stack);
	}
	
	public boolean hasContainerItem(ItemStack stack) {
		return this.get(stack).hasContainerItem(stack);
	}
	
	public boolean hasCustomEntity(ItemStack stack) {
		return this.get(stack).hasCustomEntity(stack);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return this.get(stack).hasEffect(stack);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass) {
		return this.get(stack).hasEffect(stack, pass);
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return this.get(stack).hitEntity(stack, target, attacker);
	}
	
	public boolean isBeaconPayment(ItemStack stack) {
		return this.get(stack).isBeaconPayment(stack);
	}
	
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return this.get(stack).isBookEnchantable(stack, book);
	}
	
	public boolean isDamaged(ItemStack stack) {
		return this.get(stack).isDamaged(stack);
	}
	
	public boolean isItemTool(ItemStack stack) {
		return this.get(stack).isItemTool(stack);
	}
	
	public boolean isPotionIngredient(ItemStack stack) {
		return this.get(stack).isPotionIngredient(stack);
	}
	
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
		return this.get(stack).isValidArmor(stack, armorType, entity);
	}
	
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
		return this.get(stack).itemInteractionForEntity(stack, player, target);
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		this.get(stack).onArmorTick(world, player, stack);
	}
	
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase breaker) {
		return this.get(stack).onBlockDestroyed(stack, world, block, x, y, z, breaker);
	}
	
	public boolean onBlockStartBreak(ItemStack stack, int X, int Y, int Z, EntityPlayer player) {
		return this.get(stack).onBlockStartBreak(stack, X, Y, Z, player);
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		this.get(stack).onCreated(stack, world, player);
	}
	
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		return this.get(stack).onDroppedByPlayer(stack, player);
	}
	
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		return this.get(stack).onEaten(stack, world, player);
	}
	
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		return this.get(entityItem.getEntityItem()).onEntityItemUpdate(entityItem);
	}
	
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return this.get(stack).onEntitySwing(entityLiving, stack);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		return this.get(stack).onItemRightClick(stack, world, player);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		return this.get(stack).onItemUse(stack, player, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return this.get(stack).onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return this.get(stack).onLeftClickEntity(stack, player, entity);
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int i) {
		this.get(stack).onPlayerStoppedUsing(stack, world, player, i);
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
		this.get(stack).onUpdate(stack, world, entity, i, b);
	}
	
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		this.get(stack).onUsingTick(stack, player, count);
	}
	
	public void registerItems(List<ALItem> items) {
		itemMap.clear();
		for(ALItem item : items) {
			itemMap.put(item.getName(), item.getItem());
		}
		itemMap.put("", def);
	}

	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY) {
		this.get(stack).renderHelmetOverlay(stack, player, resolution, partialTicks, hasScreen, mouseX, mouseY);
	}
	
	public void setDamage(ItemStack stack, int damage) {
		this.get(stack).setDamage(stack, damage);
	}
	
	public boolean showDurabilityBar(ItemStack stack) {
		return this.get(stack).showDurabilityBar(stack);
	}
	
}
