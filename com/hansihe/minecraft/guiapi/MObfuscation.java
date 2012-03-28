package com.hansihe.minecraft.guiapi;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Tessellator;

public class MObfuscation {
	
	/**
	 * Gets the current Minecraft instance
	 * @return Current instance of Minecraft
	 */
	public static Minecraft getMinecraft()
	{
		return MModLoaderF.getMinecraftInstance();
	}
	
	/**
	 * Draws a rectangle with a solid color to the screen.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color - Color in the format of 0xffffff
	 */
	public static void drawRect(int x, int y, int width, int height, int color)
	{
		MGuiBridge.getInstance().drawRect(x, y, width, height, color);
	}
	
	public static void drawRectOutline(int x, int y, int width, int height, int color)
	{
		MGuiBridge.getInstance().drawRectOutline(x, y, width, height, color);
	}
	
	public static void drawString(String string, int x, int y, int color)
	{
		getMinecraft().fontRenderer.drawString(string, x, y, color);
	}
	
	public static void drawCenteredString(String string, int x, int y, int color)
	{
		getMinecraft().fontRenderer.drawString(string, x-(getMinecraft().fontRenderer.getStringWidth(string)/2), y, color);
	}
	
	public static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
	{
		MGuiBridge.getInstance().drawTexturedModalRect(x, y, u, v, width, height);
	}
}
