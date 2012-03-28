package com.hansihe.minecraft.guiapi;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ChunkCoordinates;

public class GuiWidgetCompass extends GuiWidgetBase {

	private DataHandler handler;
	
	public GuiWidgetCompass(int x, int y, GuiPanel panel, DataHandler handler) {
		super(x, y, panel);
		this.handler = handler;
	}

	@Override
	public void onRender(int x, int y) {
		
		Object[] data = handler.provider();
		
		//Calculating
        double d2 = (double)((ChunkCoordinates)data[0]).posX - ((ChunkCoordinates)data[1]).posX;
        double d4 = (double)((ChunkCoordinates)data[0]).posZ - ((ChunkCoordinates)data[1]).posZ;
        double d = ((double)((Float)data[2] - 90F) * Math.PI) / 180D - Math.atan2(d4, d2);
		
		//Rendering
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, MObfuscation.getMinecraft().renderEngine.getTexture("%blur%/guiapi/compass.png"));
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		GL11.glTranslatef((x+xPos)/2, (y+yPos)/2, 0);
		GL11.glRotatef((float) -(Math.toDegrees(d)-180), 0, 0, 1);
		MObfuscation.drawTexturedModalRect(-6, -6, 0, 0, 11, 11);
		GL11.glPopMatrix();
	}
}
