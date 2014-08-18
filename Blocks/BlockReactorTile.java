/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft.Blocks;

import Reika.DragonAPI.Base.BlockTEBase;
import Reika.DragonAPI.Base.TileEntityBase;
import Reika.DragonAPI.Libraries.Java.ReikaRandomHelper;
import Reika.DragonAPI.Libraries.MathSci.ReikaMathLibrary;
import Reika.DragonAPI.Libraries.Registry.ReikaItemHelper;
import Reika.ReactorCraft.ReactorCraft;
import Reika.ReactorCraft.Auxiliary.ReactorStacks;
import Reika.ReactorCraft.Auxiliary.Temperatured;
import Reika.ReactorCraft.Base.TileEntityReactorBase;
import Reika.ReactorCraft.Registry.ReactorBlocks;
import Reika.ReactorCraft.Registry.ReactorItems;
import Reika.ReactorCraft.Registry.ReactorTiles;
import Reika.ReactorCraft.TileEntities.TileEntityHeavyPump;
import Reika.ReactorCraft.TileEntities.Fission.TileEntityReactorBoiler;
import Reika.ReactorCraft.TileEntities.Fission.TileEntityWaterCell;
import Reika.ReactorCraft.TileEntities.Fission.TileEntityWaterCell.LiquidStates;
import Reika.ReactorCraft.TileEntities.Fission.Breeder.TileEntitySodiumHeater;
import Reika.ReactorCraft.TileEntities.Fusion.TileEntitySolenoidMagnet;
import Reika.ReactorCraft.TileEntities.PowerGen.TileEntityTurbineCore;
import Reika.ReactorCraft.TileEntities.Processing.TileEntityCentrifuge;
import Reika.ReactorCraft.TileEntities.Processing.TileEntityElectrolyzer;
import Reika.ReactorCraft.TileEntities.Processing.TileEntitySynthesizer;
import Reika.ReactorCraft.TileEntities.Processing.TileEntityUProcessor;
import Reika.RotaryCraft.API.ThermalMachine;
import Reika.RotaryCraft.Auxiliary.ItemStacks;
import Reika.RotaryCraft.Auxiliary.RotaryAux;
import Reika.RotaryCraft.Auxiliary.Interfaces.TemperatureTE;
import Reika.RotaryCraft.Registry.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

import mcp.mobius.waila.api.IWailaBlock;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class BlockReactorTile extends BlockTEBase implements IWailaBlock {

	protected static final IIcon[][][] icons = new IIcon[ReactorTiles.TEList.length][6][16];

	public BlockReactorTile(Material par2Material) {
		super(par2Material);
		this.setHardness(2F);
		this.setResistance(10F);
	}

	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		TileEntity te = ReactorTiles.createTEFromIDAndMetadata(this, meta);
		return te;
	}

	@Override
	public final boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister ico) {
		if (ReactorCraft.instance.isLocked())
			return;
		for (int i = 0; i < ReactorTiles.TEList.length; i++) {
			ReactorTiles r = ReactorTiles.TEList[i];
			if (!r.hasRender() && !r.isPipe()) {
				if (r.hasTextureStates()) {
					for (int k = 0; k < r.getTextureStates(); k++) {
						if (r.hasSidedTextures()) {
							for (int j = 0; j < 6; j++) {
								icons[i][j][k] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase()+"_"+j+"_#"+k);
							}
						}
						else if (r.isEndTextured()) {
							for (int j = 0; j < 2; j++) {
								icons[i][j][k] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase()+"_top"+"_#"+k);
							}
							for (int j = 2; j < 6; j++) {
								icons[i][j][k] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase()+"_#"+k);
							}
						}
						else {
							for (int j = 0; j < 6; j++) {
								icons[i][j][k] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase()+"_#"+k);
							}
						}
					}
				}
				else {
					if (r.hasSidedTextures()) {
						for (int j = 0; j < 6; j++) {
							icons[i][j][0] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase()+"_"+j);
						}
					}
					else if (r.isEndTextured()) {
						for (int j = 0; j < 2; j++) {
							icons[i][j][0] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase()+"_top");
						}
						for (int j = 2; j < 6; j++) {
							icons[i][j][0] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase());
						}
					}
					else {
						for (int j = 0; j < 6; j++) {
							icons[i][j][0] = ico.registerIcon("ReactorCraft:"+r.name().toLowerCase());
						}
					}
				}
			}
		}
	}

	@Override
	public IIcon getIcon(IBlockAccess iba, int x, int y, int z, int s) {
		ReactorTiles r = ReactorTiles.getTE(iba, x, y, z);
		if (r == null)
			return null;
		if (r.hasTextureStates()) {
			TileEntityReactorBase te = (TileEntityReactorBase)iba.getTileEntity(x, y, z);
			int k = te.getTextureState(ForgeDirection.VALID_DIRECTIONS[s]);
			return icons[r.ordinal()][s][k];
		}
		else {
			return icons[r.ordinal()][s][0];
		}
	}

	@Override
	public IIcon getIcon(int s, int meta) {
		//for drops, needs to be r.ordinal(), not metadata
		ReactorTiles r = ReactorTiles.getMachineFromIDandMetadata(this, meta);
		return r != null ? icons[r.ordinal()][s][0] : icons[meta][s][0];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int par6, float par7, float par8, float par9) {
		super.onBlockActivated(world, x, y, z, ep, par6, par7, par8, par9);
		if (ReactorCraft.instance.isLocked())
			return false;
		ReactorTiles r = ReactorTiles.getTE(world, x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityBase)
			((TileEntityBase)tile).syncAllData(true);

		ItemStack is = ep.getCurrentEquippedItem();
		if (ep.isSneaking())
			return false;
		if (is != null) {
			ItemRegistry i = ItemRegistry.getEntry(is);
			if (i != null) {
				if (i.overridesRightClick(is))
					return false;
			}
			if (is.getItem() == ReactorItems.REMOTE.getItemInstance())
				return false;
		}
		if (r == ReactorTiles.COOLANT && is != null) {
			TileEntityWaterCell te = (TileEntityWaterCell)world.getTileEntity(x, y, z);
			switch(te.getLiquidState()) {
			case EMPTY:
				if (is.getItem() == Items.water_bucket) {
					te.setLiquidState(LiquidStates.WATER);
					if (!ep.capabilities.isCreativeMode)
						ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
					return true;
				}
				else if (is.getItem() == ReactorItems.BUCKET.getItemInstance()) {
					te.setLiquidState(LiquidStates.HEAVY);
					if (!ep.capabilities.isCreativeMode)
						ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
					return true;
				}
				else if (ReikaItemHelper.matchStacks(is, ReactorStacks.nacan)) {
					te.setLiquidState(LiquidStates.SODIUM);
					if (!ep.capabilities.isCreativeMode)
						ep.setCurrentItemOrArmor(0, ReactorStacks.emptycan);
					return true;
				}
				break;
			case WATER:
				if (is.getItem() == Items.bucket) {
					te.setLiquidState(LiquidStates.EMPTY);
					ep.setCurrentItemOrArmor(0, new ItemStack(Items.water_bucket));
					return true;
				}
				break;
			case HEAVY:
				if (is.getItem() == Items.bucket) {
					te.setLiquidState(LiquidStates.EMPTY);
					ep.setCurrentItemOrArmor(0, ReactorItems.BUCKET.getStackOf());
					return true;
				}
				break;
			case SODIUM:
				if (ReikaItemHelper.matchStacks(is, ReactorStacks.emptycan)) {
					te.setLiquidState(LiquidStates.EMPTY);
					ep.setCurrentItemOrArmor(0, ReactorStacks.nacan);
					return true;
				}
			}
		}
		if (r == ReactorTiles.SYNTHESIZER && is != null) {
			TileEntitySynthesizer te = (TileEntitySynthesizer)world.getTileEntity(x, y, z);
			if (is.getItem() == Items.water_bucket) {
				boolean flag = te.addWater(1000);
				if (flag && !ep.capabilities.isCreativeMode)
					ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
				return true;
			}
		}
		if (r == ReactorTiles.ELECTROLYZER && is != null) {
			TileEntityElectrolyzer te = (TileEntityElectrolyzer)world.getTileEntity(x, y, z);
			if (ReikaItemHelper.matchStacks(is, ReactorItems.BUCKET.getStackOf())) {
				boolean flag = te.addHeavyWater(1000);
				if (flag && !ep.capabilities.isCreativeMode)
					ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
				return true;
			}
		}
		if (r == ReactorTiles.HEAVYPUMP && is != null && is.getItem() == Items.bucket) {
			TileEntityHeavyPump te = (TileEntityHeavyPump)world.getTileEntity(x, y, z);
			if (te.hasABucket()) {
				te.subtractBucket();
				ep.setCurrentItemOrArmor(0, ReactorItems.BUCKET.getStackOf());
				return true;
			}
		}
		if (r == ReactorTiles.SODIUMBOILER && is != null) {
			TileEntitySodiumHeater te = (TileEntitySodiumHeater)world.getTileEntity(x, y, z);
			if (te.getLevel()+FluidContainerRegistry.BUCKET_VOLUME <= te.getCapacity()) {
				if (ReikaItemHelper.matchStacks(is, ReactorStacks.nacan)) {
					if (te.getLevel() <= 0 || te.getContainedFluid().equals(FluidRegistry.getFluid("sodium"))) {
						te.addLiquid(FluidContainerRegistry.BUCKET_VOLUME, FluidRegistry.getFluid("sodium"));
						if (!ep.capabilities.isCreativeMode)
							ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
					}
					return true;
				}
			}
		}
		if (r == ReactorTiles.BOILER && is != null) {
			TileEntityReactorBoiler te = (TileEntityReactorBoiler)world.getTileEntity(x, y, z);
			if (te.getLevel()+FluidContainerRegistry.BUCKET_VOLUME <= te.getCapacity()) {
				if (is.getItem() == Items.water_bucket) {
					if (te.getLevel() <= 0 || te.getContainedFluid().equals(FluidRegistry.WATER)) {
						te.addLiquid(FluidContainerRegistry.BUCKET_VOLUME, FluidRegistry.WATER);
						if (!ep.capabilities.isCreativeMode)
							ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
					}
					return true;
				}
				if (ReikaItemHelper.matchStacks(is, ReactorStacks.nh3can)) {
					if (te.getLevel() <= 0 || te.getContainedFluid().equals(FluidRegistry.getFluid("ammonia"))) {
						te.addLiquid(FluidContainerRegistry.BUCKET_VOLUME, FluidRegistry.getFluid("ammonia"));
						if (!ep.capabilities.isCreativeMode)
							ep.setCurrentItemOrArmor(0, ReactorStacks.emptycan);
					}
					return true;
				}
			}
		}
		if (r == ReactorTiles.PROCESSOR && is != null && is.getItem() == ReactorItems.CANISTER.getItemInstance()) {
			TileEntityUProcessor te = (TileEntityUProcessor)world.getTileEntity(x, y, z);
			if (is.getItemDamage() == ReactorStacks.emptycan.getItemDamage() && te.getUF6() >= FluidContainerRegistry.BUCKET_VOLUME) {
				if (!ep.capabilities.isCreativeMode)
					ep.setCurrentItemOrArmor(0, ReactorStacks.uf6can.copy());
				te.drain(null, FluidContainerRegistry.BUCKET_VOLUME, true);
			}
			else if (is.getItemDamage() == ReactorStacks.hfcan.getItemDamage() && te.canAcceptMoreHF(FluidContainerRegistry.BUCKET_VOLUME)) {
				if (!ep.capabilities.isCreativeMode)
					ep.setCurrentItemOrArmor(0, ReactorStacks.emptycan.copy());
				te.addHF(FluidContainerRegistry.BUCKET_VOLUME);
			}
			return true;
		}
		if (r == ReactorTiles.CENTRIFUGE && is != null && is.getItem() == ReactorItems.CANISTER.getItemInstance()) {
			TileEntityCentrifuge te = (TileEntityCentrifuge)world.getTileEntity(x, y, z);
			if (is.getItemDamage() == ReactorStacks.emptycan.getItemDamage() && te.getUF6() >= FluidContainerRegistry.BUCKET_VOLUME) {
				if (!ep.capabilities.isCreativeMode)
					ep.setCurrentItemOrArmor(0, ReactorStacks.uf6can.copy());
				te.removeFluid(FluidContainerRegistry.BUCKET_VOLUME);
			}
			else if (is.getItemDamage() == ReactorStacks.uf6can.getItemDamage() && te.canAcceptMoreUF6(FluidContainerRegistry.BUCKET_VOLUME)) {
				if (!ep.capabilities.isCreativeMode)
					ep.setCurrentItemOrArmor(0, ReactorStacks.emptycan.copy());
				te.addUF6(FluidContainerRegistry.BUCKET_VOLUME);
			}
			return true;
		}
		if (r.isTurbine()) {
			if (is != null && is.stackSize == 1 && ReikaItemHelper.matchStacks(is, ItemStacks.lubebucket)) {
				TileEntityTurbineCore te = (TileEntityTurbineCore)world.getTileEntity(x, y, z);
				int amt = 1000;
				if (te.canAcceptLubricant(amt)) {
					te.addLubricant(amt);
					if (!ep.capabilities.isCreativeMode)
						ep.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
				}
			}
		}

		if (ReactorCraft.hasGui(world, x, y, z, ep)) {
			ep.openGui(ReactorCraft.instance, 0, world, x, y, z);
			return true;
		}

		return false;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition vec, World world, int x, int y, int z) {
		ReactorTiles r = ReactorTiles.getTE(world, vec.blockX, vec.blockY, vec.blockZ);
		return r != null ? r.getCraftedProduct() : null;
	}

	@Override
	public final void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof IInventory)
			ReikaItemHelper.dropInventory(world, x, y, z);
		if (te instanceof TileEntitySolenoidMagnet) {
			((TileEntitySolenoidMagnet) te).removeFromToroids();
			Block id = world.getBlock(x, y-1, z);
			if (id == ReactorBlocks.SOLENOIDMULTI.getBlockInstance()) {
				BlockSolenoidMulti b = (BlockSolenoidMulti)ReactorBlocks.SOLENOIDMULTI.getBlockInstance();
				b.breakMultiBlock(world, x, y-1, z);
			}
		}
		if (te instanceof TileEntityTurbineCore) {
			((TileEntityTurbineCore)te).onBreak();
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public int getRenderType() {
		return 0;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
		ReactorTiles r = ReactorTiles.getTE(world, x, y, z);
		if (r == ReactorTiles.HEATER)
			return 0;
		return 255;
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean harv)
	{
		if (!player.capabilities.isCreativeMode && this.canHarvest(world, player, x, y, z))
			this.harvestBlock(world, player, x, y, z, world.getBlockMetadata(x, y, z));
		return world.setBlockToAir(x, y, z);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer ep, int x, int y, int z, int meta) {
		if (!this.canHarvest(world, ep, x, y, z))
			return;
		if (world.getBlock(x, y, z) == this)
			ReikaItemHelper.dropItems(world, x+0.5, y+0.5, z+0.5, this.getDrops(world, x, y, z, meta, 0));
	}

	private boolean canHarvest(World world, EntityPlayer ep, int x, int y, int z) {
		return RotaryAux.canHarvestSteelMachine(ep);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList li = new ArrayList();
		ReactorTiles r = ReactorTiles.getMachineFromIDandMetadata(this, meta);
		if (r != null) {
			if (r.isTurbine()) {
				TileEntityTurbineCore te = (TileEntityTurbineCore)world.getTileEntity(x, y, z);
				if (te == null)
					return li;
				if (te.getDamage() > 0) {
					li.add(ItemStacks.shaftcore.copy());
					for (int i = 0; i < ReikaRandomHelper.getSafeRandomInt(24-te.getDamage()); i++) {
						li.add(ItemStacks.prop.copy());
					}
				}
				else {
					ItemStack is = r.getCraftedProduct();
					if (te.getLubricant() > 0) {
						is.stackTagCompound = new NBTTagCompound();
						int lube = ReikaMathLibrary.roundDownToX(10, te.getLubricantToDrop()); //to help with stacking
						is.stackTagCompound.setInteger("lube", lube);
					}
					li.add(is);
				}
			}
			else {
				li.add(r.getCraftedProduct());
			}
		}
		return li;
	}

	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return ReactorTiles.getMachineFromIDandMetadata(this, accessor.getMetadata()).getCraftedProduct();
	}

	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor acc, IWailaConfigHandler config) {
		World world = acc.getWorld();
		MovingObjectPosition mov = acc.getPosition();
		if (mov != null) {
			int x = mov.blockX;
			int y = mov.blockY;
			int z = mov.blockZ;
			currenttip.add(EnumChatFormatting.WHITE+this.getPickBlock(mov, world, x, y, z).getDisplayName());
		}
		return currenttip;
	}

	public List<String> getWailaBody(ItemStack itemStack, List<String> tip, IWailaDataAccessor acc, IWailaConfigHandler config) {
		TileEntity te = acc.getTileEntity();
		if (te instanceof TileEntityReactorBase)
			((TileEntityBase)te).syncAllData(false);
		if (te instanceof Temperatured)
			tip.add(String.format("Temperature: %dC", ((Temperatured) te).getTemperature()));
		else if (te instanceof TemperatureTE)
			tip.add(String.format("Temperature: %dC", ((TemperatureTE) te).getTemperature()));
		else if (te instanceof ThermalMachine)
			tip.add(String.format("Temperature: %dC", ((ThermalMachine) te).getTemperature()));
		if (te instanceof IFluidHandler) {
			FluidTankInfo[] tanks = ((IFluidHandler)te).getTankInfo(ForgeDirection.UP);
			if (tanks != null) {
				for (int i = 0; i < tanks.length; i++) {
					FluidTankInfo info = tanks[i];
					FluidStack fs = info.fluid;
					String input = fs != null ? String.format("%d/%d mB of %s", fs.amount, info.capacity, fs.getFluid().getLocalizedName(fs)) : "Empty";
					tip.add("Tank "+i+": "+input);
				}
			}
		}
		return tip;
	}

	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor acc, IWailaConfigHandler config) {
		String s1 = EnumChatFormatting.ITALIC.toString();
		String s2 = EnumChatFormatting.BLUE.toString();
		currenttip.add(s2+s1+"ReactorCraft");
		return currenttip;
	}

}
