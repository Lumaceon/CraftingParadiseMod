package lumaceon.mods.craftingparadise.inventory;

import lumaceon.mods.craftingparadise.item.modules.IWorldBuilderModule;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWorldModule extends Slot
{
    IWorldBuilderModule.ModuleType moduleType;
    Slot[] prerequisites;

    public SlotWorldModule(IInventory inventory, int id, int x, int y, IWorldBuilderModule.ModuleType moduleType, Slot... prerequisites)
    {
        super(inventory, id, x, y);
        this.moduleType = moduleType;
        this.prerequisites = prerequisites;
    }

    public boolean isItemValid(ItemStack is)
    {
        if(is.getItem() instanceof IWorldBuilderModule)
        {
            if(((IWorldBuilderModule) is.getItem()).getModuleType(is).equals(moduleType))
            {
                for(Slot pre : prerequisites)
                {
                    if(pre == null || !pre.getHasStack())
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
