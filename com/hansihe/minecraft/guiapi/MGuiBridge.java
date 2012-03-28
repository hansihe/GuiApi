package com.hansihe.minecraft.guiapi;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;

public class MGuiBridge extends Gui{

	private static MGuiBridge instance = null;
	
	public static MGuiBridge getInstance()
	{
		if(instance == null)
		{
			instance = new MGuiBridge();
		}
		instance.zLevel = 1000F;
		return instance;
	}
	
	public void drawRect(int x, int y, int width, int height, int color)
	{
		super.drawRect(x, y, width, height, color);
	}
	
	public void drawRectOutline(int x, int y, int width, int height, int color)
	{
		super.drawHorizontalLine(x, x+width, y, color);
		super.drawHorizontalLine(x, x+width, y+height, color);
		super.drawVerticalLine(x, y, y+height, color);
		super.drawVerticalLine(x+width, y, y+height, color);
	}
	
/*	public void drawString(FontRenderer fr, String string, int x, int y, int color)
	{
		super.drawString(fr, string, x, y, color);
	}
	
	public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
	{
		super.drawTexturedModalRect(x, y, u, v, width, height);
	}*/
}
