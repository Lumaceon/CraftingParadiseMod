package lumaceon.mods.craftingparadise.client;

import lumaceon.mods.craftingparadise.planet.ActivePlanet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;

public class CustomSkyRenderer extends IRenderHandler
{
    //public static ResourceLocation moonLoc = new ResourceLocation(Reference.MOD_ID, "textures/environment/iron_core.png");
    public static ResourceLocation locationSunPng = new ResourceLocation("textures/environment/sun.png");
    //public static ResourceLocation locationEndSkyPng = new ResourceLocation("textures/environment/end_sky.png");

    private boolean isInitialized = false;

    private Field isVboEnabled;

    //private boolean isVboEnabled = false;

    private Field starVBO;
    private Field skyVBO;
    private Field sky2VBO;

    private Field starGLCallList;
    private Field glSkyList;
    private Field glSkyList2;

    public void init()
    {
        isVboEnabled = ReflectionHelper.findField(RenderGlobal.class, "vboEnabled", "field_175005_X");
        starVBO = ReflectionHelper.findField(RenderGlobal.class, "starVBO", "field_175013_s");
        skyVBO = ReflectionHelper.findField(RenderGlobal.class, "skyVBO", "field_175012_t");
        sky2VBO = ReflectionHelper.findField(RenderGlobal.class, "sky2VBO", "field_175011_u");
        starGLCallList = ReflectionHelper.findField(RenderGlobal.class, "starGLCallList", "field_72772_v");
        glSkyList = ReflectionHelper.findField(RenderGlobal.class, "glSkyList", "field_72771_w");
        glSkyList2 = ReflectionHelper.findField(RenderGlobal.class, "glSkyList2", "field_72781_x");
        isInitialized = true;

        /*this.starGLCallList = GLAllocation.generateDisplayLists(3);
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
        byte b2 = 64;
        int i = 256 / b2 + 2;
        float f = 16.0F;
        int j;
        int k;

        for (j = -b2 * i; j <= b2 * i; j += b2)
        {
            for (k = -b2 * i; k <= b2 * i; k += b2)
            {
                vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
                vertexbuffer.pos((double) (j + 0), (double) f, (double) (k + 0));
                vertexbuffer.pos((double) (j + b2), (double) f, (double) (k + 0));
                vertexbuffer.pos((double) (j + b2), (double) f, (double) (k + b2));
                vertexbuffer.pos((double) (j + 0), (double) f, (double) (k + b2));
                tessellator.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        f = -16.0F;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);

        for (j = -b2 * i; j <= b2 * i; j += b2)
        {
            for (k = -b2 * i; k <= b2 * i; k += b2)
            {
                vertexbuffer.pos((double) (j + b2), (double) f, (double) (k + 0));
                vertexbuffer.pos((double) (j + 0), (double) f, (double) (k + 0));
                vertexbuffer.pos((double) (j + 0), (double) f, (double) (k + b2));
                vertexbuffer.pos((double) (j + b2), (double) f, (double) (k + b2));
            }
        }

        tessellator.draw();
        GL11.glEndList();*/
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {
        if(!isInitialized)
            init();

        //This would probably have resulted in a recursion error. This is probably where this method is called in the first place.
        //net.minecraftforge.client.IRenderHandler renderer = world.provider.getSkyRenderer();
        /*if(renderer != null)
        {
            renderer.render(partialTicks, world, mc);
            return;
        }*/

        if(world.provider.isSurfaceWorld())
        {
            GlStateManager.disableTexture2D();
            Vec3d vec3d = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
            float f = (float)vec3d.x;
            float f1 = (float)vec3d.y;
            float f2 = (float)vec3d.z;

            //if(pass != 2)
            //{
                float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                f = f3;
                f1 = f4;
                f2 = f5;
            //}

            GlStateManager.color(f, f1, f2);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            GlStateManager.depthMask(false);
            GlStateManager.enableFog();
            GlStateManager.color(f, f1, f2);


            boolean useVbo = false;
            try {
                useVbo = this.isVboEnabled.getBoolean(mc.renderGlobal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            VertexBuffer starsVboValue = null;
            VertexBuffer skyVboValue = null;
            VertexBuffer sky2VboValue = null;
            try {
                starsVboValue = ((VertexBuffer) this.starVBO.get(mc.renderGlobal));
                skyVboValue = ((VertexBuffer) this.skyVBO.get(mc.renderGlobal));
                sky2VboValue = ((VertexBuffer) this.sky2VBO.get(mc.renderGlobal));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            int glStarListValue = -1;
            int glSkyListValue = -1;
            int glSky2ListValue = -1;
            try {
                glStarListValue = this.starGLCallList.getInt(mc.renderGlobal);
                glSkyListValue = this.glSkyList.getInt(mc.renderGlobal);
                glSky2ListValue = this.glSkyList2.getInt(mc.renderGlobal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(useVbo && skyVboValue != null)
            {
                skyVboValue.bindBuffer();
                GlStateManager.glEnableClientState(32884);
                GlStateManager.glVertexPointer(3, 5126, 12, 0);
                skyVboValue.drawArrays(7);
                skyVboValue.unbindBuffer();
                GlStateManager.glDisableClientState(32884);
            }
            else
            {
                GlStateManager.callList(glSkyListValue);
            }

            GlStateManager.disableFog();
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderHelper.disableStandardItemLighting();
            float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

            if (afloat != null)
            {
                GlStateManager.disableTexture2D();
                GlStateManager.shadeModel(7425);
                GlStateManager.pushMatrix();
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                float f6 = afloat[0];
                float f7 = afloat[1];
                float f8 = afloat[2];

                //if (pass != 2)
                //{
                    float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
                    float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
                    float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
                    f6 = f9;
                    f7 = f10;
                    f8 = f11;
                //}

                bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
                bufferbuilder.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();
                int j = 16;

                for (int l = 0; l <= 16; ++l)
                {
                    float f21 = (float)l * ((float)Math.PI * 2F) / 16.0F;
                    float f12 = MathHelper.sin(f21);
                    float f13 = MathHelper.cos(f21);
                    bufferbuilder.pos((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
                }

                tessellator.draw();
                GlStateManager.popMatrix();
                GlStateManager.shadeModel(7424);
            }

            GlStateManager.enableTexture2D();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            float f16 = 1.0F - world.getRainStrength(partialTicks);
            GlStateManager.color(1.0F, 1.0F, 1.0F, f16);
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            float f17 = 30.0F;
            mc.renderEngine.bindTexture(locationSunPng);
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos((double)(-f17), 100.0D, (double)(-f17)).tex(0.0D, 0.0D).endVertex();
            bufferbuilder.pos((double)f17, 100.0D, (double)(-f17)).tex(1.0D, 0.0D).endVertex();
            bufferbuilder.pos((double)f17, 100.0D, (double)f17).tex(1.0D, 1.0D).endVertex();
            bufferbuilder.pos((double)(-f17), 100.0D, (double)f17).tex(0.0D, 1.0D).endVertex();
            tessellator.draw();

            //Custom Moon Rendering Code.
            boolean renderMoon = true;
            float alpha = 1.0F;
            long dayTime = world.getWorldTime() % 24000;

            //Fade out
            if(dayTime <= 1000 || dayTime >= 23000)
            {
                //Transform worldtime by day cycle value minus max local value max. New range is [0, 2000].
                long fadeTime = (world.getWorldTime() + 1000) % 24000;
                fadeTime -= 2000;
                alpha = Math.abs(fadeTime / 2000.0F);
            }
            //Negate during daytime
            else if(dayTime > 1000 && dayTime < 11500)
            {
                alpha = 0.0F;
                renderMoon = false;
            }
            //Fade in
            else if(dayTime >= 11500 && dayTime <= 14000)
            {
                //Transform worldtime back by the lower end value. New range is [0, 2500].
                long fadeTime = (world.getWorldTime() - 11500) % 24000;
                alpha = fadeTime / 2500.0F;
            }

            //Render the moon if necessary, with each module valid.
            if(renderMoon)
            {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
                float f10;
                if(ActivePlanet.isCoreVisible())
                {
                    f10 = ActivePlanet.coreModule.textureSize;
                    mc.renderEngine.bindTexture(ActivePlanet.coreModule.textureLocation);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex(0, 0).endVertex();
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex(0, 1).endVertex();
                    tessellator.draw();
                }
                if(ActivePlanet.isMantleVisible())
                {
                    f10 = ActivePlanet.mantleModule.textureSize;
                    mc.renderEngine.bindTexture(ActivePlanet.mantleModule.textureLocation);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex(0, 0).endVertex();
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex(0, 1).endVertex();
                    tessellator.draw();
                }
                if(ActivePlanet.isCrustVisible())
                {
                    f10 = ActivePlanet.crustModule.textureSize;
                    mc.renderEngine.bindTexture(ActivePlanet.crustModule.textureLocation);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex( 0, 0).endVertex();
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex( 0, 1).endVertex();
                    tessellator.draw();
                }
                if(ActivePlanet.isLandVisible())
                {
                    f10 = ActivePlanet.landscapeModule.textureSize;
                    mc.renderEngine.bindTexture(ActivePlanet.landscapeModule.textureLocation);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex(0, 0).endVertex();
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex(0, 1).endVertex();
                    tessellator.draw();
                }
                if(ActivePlanet.isWorldVisible())
                {
                    f10 = ActivePlanet.worldModule.textureSize;
                    mc.renderEngine.bindTexture(ActivePlanet.worldModule.textureLocation);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex( 0, 0).endVertex();
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex(0, 1).endVertex();
                    tessellator.draw();
                }
                if(ActivePlanet.isAtmosphereVisible())
                {
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    f10 = ActivePlanet.atmosphereModule.textureSize;
                    mc.renderEngine.bindTexture(ActivePlanet.atmosphereModule.textureLocation);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                    bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex(0, 0).endVertex();
                    bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex(0, 1).endVertex();
                    tessellator.draw();

                    if(ActivePlanet.atmosphereModule.atmosphereTextureLocation != null)
                    {
                        f10 = ActivePlanet.atmosphereModule.atmosphereTextureSize;
                        mc.renderEngine.bindTexture(ActivePlanet.atmosphereModule.atmosphereTextureLocation);
                        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                        bufferbuilder.pos((double)(-f10), -100.0D, (double)f10).tex(1, 1).endVertex();
                        bufferbuilder.pos((double)f10, -100.0D, (double)f10).tex(1, 0).endVertex();
                        bufferbuilder.pos((double)f10, -100.0D, (double)(-f10)).tex(0, 0).endVertex();
                        bufferbuilder.pos((double)(-f10), -100.0D, (double)(-f10)).tex(0, 1).endVertex();
                        tessellator.draw();
                    }
                }
            }

            //End custom moon rendering code

            GlStateManager.disableTexture2D();
            float f15 = world.getStarBrightness(partialTicks) * f16;

            if(f15 > 0.0F)
            {
                GlStateManager.color(f15, f15, f15, f15);
                if(useVbo)
                {
                    starsVboValue.bindBuffer();
                    GlStateManager.glEnableClientState(32884);
                    GlStateManager.glVertexPointer(3, 5126, 12, 0);
                    starsVboValue.drawArrays(7);
                    starsVboValue.unbindBuffer();
                    GlStateManager.glDisableClientState(32884);
                }
                else
                {
                    GlStateManager.callList(glStarListValue);
                }
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.enableFog();
            GlStateManager.popMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.color(0.0F, 0.0F, 0.0F);
            double d0 = mc.player.getPositionEyes(partialTicks).y - world.getHorizon();

            if (d0 < 0.0D)
            {
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 12.0F, 0.0F);

                if(useVbo)
                {
                    sky2VboValue.bindBuffer();
                    GlStateManager.glEnableClientState(32884);
                    GlStateManager.glVertexPointer(3, 5126, 12, 0);
                    sky2VboValue.drawArrays(7);
                    sky2VboValue.unbindBuffer();
                    GlStateManager.glDisableClientState(32884);
                }
                else
                {
                    GlStateManager.callList(glSky2ListValue);
                }

                GlStateManager.popMatrix();
                float f18 = 1.0F;
                float f19 = -((float)(d0 + 65.0D));
                float f20 = -1.0F;
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                bufferbuilder.pos(-1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, (double)f19, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, (double)f19, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
            }

            if(world.provider.isSkyColored())
            {
                GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
            }
            else
            {
                GlStateManager.color(f, f1, f2);
            }

            //GlStateManager.pushMatrix();
            //GlStateManager.translate(0.0F, -((float)(d0 - 16.0D)), 0.0F);
            //GlStateManager.callList(this.glSkyList2);
            //GlStateManager.popMatrix();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
        }
    }
}
