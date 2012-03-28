package com.hansihe.minecraft.guiapi;

public class GuiWidgetIconRowHeart extends GuiWidgetIconRowBase {

	public GuiWidgetIconRowHeart(int x, int y, GuiPanel panel, DataHandler handler, int color) {
		super(x, y, panel, handler, color);
	}

	@Override
	public void renderIcon(int xpos, int ypos, int type) {
		switch(type)
		{
		case 0:
			MObfuscation.drawTexturedModalRect(xpos, ypos, 16, 0, 9, 9);
			break;
		case 1:
			MObfuscation.drawTexturedModalRect(xpos, ypos, 52, 0, 9, 9);
			break;
		case 2:
			MObfuscation.drawTexturedModalRect(xpos, ypos, 61, 0, 9, 9);
			break;
		}
	}
}
