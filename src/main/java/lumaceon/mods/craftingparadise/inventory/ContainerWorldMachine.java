package lumaceon.mods.craftingparadise.inventory;

import lumaceon.mods.craftingparadise.item.modules.IWorldBuilderModule;
import lumaceon.mods.craftingparadise.tile.TileWorldMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWorldMachine extends Container
{
    TileWorldMachine tile;

    public ContainerWorldMachine(EntityPlayer player, TileWorldMachine tile)
    {
        this.tile = tile;

        InventoryPlayer ip = player.inventory;
        int playerInventoryX = 35;
        int playerInventoryY = 147;

        for(int x = 0; x < 9; x++)
            this.addSlotToContainer(new Slot(ip, x, playerInventoryX + x * 18 , playerInventoryY+58));

        for(int x = 0; x < 9; x++)
            for(int y = 0; y < 3; y++)
                this.addSlotToContainer(new Slot(ip, 9 + y * 9 + x, playerInventoryX + x * 18, playerInventoryY + y * 18));

        SlotWorldModule core = new SlotWorldModule(tile, 0, 21, 21, IWorldBuilderModule.ModuleType.CORE);
        SlotWorldModule mantle = new SlotWorldModule(tile, 1, 39, 21, IWorldBuilderModule.ModuleType.MANTLE, core);
        SlotWorldModule crust = new SlotWorldModule(tile, 2, 57, 21, IWorldBuilderModule.ModuleType.CRUST, core, mantle);
        SlotWorldModule land = new SlotWorldModule(tile, 3, 21, 39, IWorldBuilderModule.ModuleType.LANDSCAPE, core, mantle, crust);
        SlotWorldModule world = new SlotWorldModule(tile, 4, 39, 39, IWorldBuilderModule.ModuleType.WORLD, core, mantle, crust, land);
        SlotWorldModule atmosphere = new SlotWorldModule(tile, 5, 57, 39, IWorldBuilderModule.ModuleType.ATMOSPHERE, core, mantle, crust, land, world);

        this.addSlotToContainer(core);
        this.addSlotToContainer(mantle);
        this.addSlotToContainer(crust);
        this.addSlotToContainer(land);
        this.addSlotToContainer(world);
        this.addSlotToContainer(atmosphere);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        Slot slot = this.inventorySlots.get(index);
        if(slot == null || !slot.getHasStack())
            return ItemStack.EMPTY;

        ItemStack originalItem = slot.getStack();
        ItemStack copyItem = originalItem.copy();

        if(index >= 36) //Item is in our container, try placing in player's inventory.
        {
            if(!this.mergeItemStack(originalItem, 0, 36, true))
                return ItemStack.EMPTY;
        }
        else
        {
            if(!this.mergeItemStack(originalItem, 36, this.inventorySlots.size(), false))
                return ItemStack.EMPTY;
        }

        if(copyItem.getCount() == 0)
            slot.putStack(ItemStack.EMPTY);
        else
            slot.onSlotChanged();

        if(originalItem.getCount() == 0)
            slot.putStack(ItemStack.EMPTY);
        else
            slot.onSlotChanged();

        if(copyItem.getCount() == originalItem.getCount())
            return ItemStack.EMPTY;
        slot.onTake(player, copyItem);
        return originalItem;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
