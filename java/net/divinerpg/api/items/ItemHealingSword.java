package net.divinerpg.api.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHealingSword extends ItemModSword {
	
	private int healAmount;

	public ItemHealingSword(String name, ToolMaterial mat, int healAmount) {
		super(mat, name);
		this.healAmount = healAmount;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		float current = player.getHealth();
        if ((current >= 0.0F) && (current < 20.0F)) {
            player.setHealth(current + this.healAmount);
            stack.damageItem(1, player);
            world.playSoundAtEntity(player, Sounds.heal.getPrefixedName(), 1, 1);
        }
		return super.onItemRightClick(stack, world, player);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		 list.add("Heals " + this.healAmount + " health on use");
		 list.add(stack.getMaxDamage() - stack.getItemDamage() + " Uses Remaining");
	}
}
