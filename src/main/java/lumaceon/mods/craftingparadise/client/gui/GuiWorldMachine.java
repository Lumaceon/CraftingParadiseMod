package lumaceon.mods.craftingparadise.client.gui;

import lumaceon.mods.craftingparadise.inventory.ContainerWorldMachine;
import lumaceon.mods.craftingparadise.item.modules.IWorldBuilderModule;
import lumaceon.mods.craftingparadise.lib.Reference;
import lumaceon.mods.craftingparadise.network.PacketHandler;
import lumaceon.mods.craftingparadise.network.message.MessageWorldCraft;
import lumaceon.mods.craftingparadise.planet.PlanetModule;
import lumaceon.mods.craftingparadise.planet.PlanetModuleAtmosphere;
import lumaceon.mods.craftingparadise.tile.TileWorldMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiWorldMachine extends GuiContainer
{
    protected static ResourceLocation WORLD_MACHINE_BG = new ResourceLocation(Reference.MOD_ID, "textures/gui/world_machine.png");

    TileWorldMachine te;

    public GuiWorldMachine(EntityPlayer player, TileWorldMachine te) {
        super(new ContainerWorldMachine(player, te));
        this.xSize = 230;
        this.ySize = 230;
        this.te = te;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, guiLeft + 12, guiTop + 71, 72, 20, "Craft Planet"));
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
            PacketHandler.INSTANCE.sendToServer(new MessageWorldCraft(te.getPos()));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY); //Renders item name/tooltip on hover. You almost always want this.
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(WORLD_MACHINE_BG);
        drawTexturedModalRectStretched(this.guiLeft, this.guiTop, this.zLevel, this.xSize, this.ySize);

        ItemStack core = ItemStack.EMPTY;
        ItemStack mantle = ItemStack.EMPTY;
        ItemStack crust = ItemStack.EMPTY;
        ItemStack land = ItemStack.EMPTY;
        ItemStack world = ItemStack.EMPTY;
        ItemStack atmosphere = ItemStack.EMPTY;

        Slot one = this.inventorySlots.getSlotFromInventory(te, 0);
        if(one != null)
            core = one.getStack();

        Slot two = this.inventorySlots.getSlotFromInventory(te, 1);
        if(two != null)
            mantle = two.getStack();

        Slot three = this.inventorySlots.getSlotFromInventory(te, 2);
        if(three != null)
            crust = three.getStack();

        Slot four = this.inventorySlots.getSlotFromInventory(te, 3);
        if(four != null)
            land = four.getStack();

        Slot five = this.inventorySlots.getSlotFromInventory(te, 4);
        if(five != null)
            world = five.getStack();

        Slot six = this.inventorySlots.getSlotFromInventory(te, 5);
        if(six != null)
            atmosphere = six.getStack();

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        float sizeUnit = (120.0F / 90.0F);
        int finalSize;

        if(core.isEmpty() || !(core.getItem() instanceof IWorldBuilderModule))
        {
            //NOOP
        }
        else if(mantle.isEmpty() || !(mantle.getItem() instanceof IWorldBuilderModule))
        {
            //Render core.
            PlanetModule planetModule = ((IWorldBuilderModule) core.getItem()).getModule(mantle);
            finalSize = (int) (sizeUnit * planetModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(planetModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);
        }
        else if(crust.isEmpty() || !(crust.getItem() instanceof IWorldBuilderModule))
        {
            //Render mantle.
            PlanetModule planetModule = ((IWorldBuilderModule) mantle.getItem()).getModule(mantle);
            finalSize = (int) (sizeUnit * planetModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(planetModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);
        }
        else if(land.isEmpty() || !(land.getItem() instanceof IWorldBuilderModule))
        {
            //Render crust.
            PlanetModule planetModule = ((IWorldBuilderModule) crust.getItem()).getModule(crust);
            finalSize = (int) (sizeUnit * planetModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(planetModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);
        }
        else if(world.isEmpty() || !(world.getItem() instanceof IWorldBuilderModule))
        {
            //Render land.
            PlanetModule planetModule = ((IWorldBuilderModule) land.getItem()).getModule(land);
            finalSize = (int) (sizeUnit * planetModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(planetModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);
        }
        else if(atmosphere.isEmpty() || !(atmosphere.getItem() instanceof IWorldBuilderModule) || ((IWorldBuilderModule) atmosphere.getItem()).getModule(atmosphere) == null || !(((IWorldBuilderModule) atmosphere.getItem()).getModule(atmosphere) instanceof PlanetModuleAtmosphere))
        {
            //Render world without atmosphere.
            PlanetModule planetModule = ((IWorldBuilderModule) world.getItem()).getModule(world);
            finalSize = (int) (sizeUnit * planetModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(planetModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);
        }
        else
        {
            //Render world...
            PlanetModule planetModule = ((IWorldBuilderModule) world.getItem()).getModule(world);
            finalSize = (int) (sizeUnit * planetModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(planetModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);

            //...and clouds...
            PlanetModuleAtmosphere atmosModule = (PlanetModuleAtmosphere) ((IWorldBuilderModule) atmosphere.getItem()).getModule(atmosphere);
            finalSize = (int) (sizeUnit * atmosModule.textureSize);
            Minecraft.getMinecraft().renderEngine.bindTexture(atmosModule.textureLocation);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);

            //...and atmosphere ring.
            Minecraft.getMinecraft().renderEngine.bindTexture(atmosModule.atmosphereTextureLocation);
            finalSize = (int) (sizeUnit * atmosModule.atmosphereTextureSize);
            drawTexturedModalRectStretched(this.guiLeft + 97 + (int) ((120 - finalSize) * 0.5F), this.guiTop + 9 + (int) ((120 - finalSize) * 0.5F), this.zLevel, finalSize, finalSize);
        }
    }

    /**
     * Draws the bound texture by stretching it over the specified width and height.
     */
    public static void drawTexturedModalRectStretched(int x, int y, double zLevel, int width, int height)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder renderer = tessellator.getBuffer();
        renderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderer.pos((double)(x), (double)(y + height), zLevel).tex(0, 1).endVertex();
        renderer.pos((double)(x + width), (double)(y + height), zLevel).tex(1, 1).endVertex();
        renderer.pos((double)(x + width), (double)(y), zLevel).tex(1, 0).endVertex();
        renderer.pos((double)(x), (double)(y), zLevel).tex(0, 0).endVertex();
        tessellator.draw();
    }
}
