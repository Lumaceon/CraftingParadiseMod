package lumaceon.mods.craftingparadise.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileWorldMachine extends TileGeneric implements IInventory
{
    @CapabilityInject(IItemHandler.class)
    static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    IItemHandler inventory;
    int stackLimit;

    public TileWorldMachine() {
        this(6, 1);
    }

    private TileWorldMachine(int size, int stackLimit)
    {
        inventory = new ItemStackHandlerTileEntity(size);
        ((ItemStackHandlerTileEntity) inventory).setTileEntity(this);
        this.stackLimit = stackLimit;
    }

    private IItemHandler getItemHandler() {
        return inventory;
    }

    @Override
    public int getSizeInventory() {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler == null ? 0 : itemHandler.getSlots();
    }

    @Override
    public boolean isEmpty()
    {
        if(inventory == null || inventory.getSlots() <= 0)
            return true;

        for(int i = 0; i < inventory.getSlots(); i++)
        {
            ItemStack is = inventory.getStackInSlot(i);
            if(!is.isEmpty())
                return true;
        }

        return false;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler == null ? null : itemHandler.getStackInSlot(index);
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler == null ? null : itemHandler.extractItem(index, count, false);
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler == null ? null : itemHandler.extractItem(index, 64, false);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        IItemHandler itemHandler = getItemHandler();
        if(itemHandler != null)
        {
            itemHandler.extractItem(index, 64, false);
            itemHandler.insertItem(index, stack, false);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return stackLimit;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        IItemHandler itemHandler = getItemHandler();
        if(itemHandler != null)
            for (int i = 0; i < itemHandler.getSlots(); i++)
                itemHandler.extractItem(i, 64, false);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    protected void onInventoryContentsChanged() {
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if(inventory != null && inventory instanceof ItemStackHandler)
        {
            nbt.setTag("craftingparadise_inventory", ((ItemStackHandler) inventory).serializeNBT());
        }

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        if(nbt.hasKey("craftingparadise_inventory") && inventory != null && inventory instanceof ItemStackHandler)
        {
            ((ItemStackHandler) inventory).deserializeNBT((NBTTagCompound) nbt.getTag("craftingparadise_inventory"));
        }
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing) {
        return capability != null && capability == ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if(hasCapability(capability, facing))
            return ITEM_HANDLER_CAPABILITY.cast(inventory);
        return super.getCapability(capability, facing);
    }

    public static class ItemStackHandlerTileEntity extends ItemStackHandler
    {
        protected TileWorldMachine tileEntity;

        public ItemStackHandlerTileEntity(int size) {
            super(size);
        }

        public void setTileEntity(TileWorldMachine tile) {
            this.tileEntity = tile;
        }

        @Override
        protected void onContentsChanged(int slot) {
            tileEntity.onInventoryContentsChanged();
        }
    }
}
