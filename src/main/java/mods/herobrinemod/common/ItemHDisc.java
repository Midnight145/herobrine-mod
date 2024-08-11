package mods.herobrinemod.common;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHDisc extends ItemRecord {

    public String rname;
    public String screename;

    protected ItemHDisc(String name, String screenname) {
        super(name);

        this.screename = screenname;
        this.rname = name;

    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(getRecordNameLocal());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getRecordNameLocal() {

        return this.screename;
    }

    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation("herobrinemod:" + rname);
    }

}
