package com.hansihe.minecraft.guiapi;

public class GuiWidgetText extends GuiWidgetBase {

	public String text = "";
	
	public GuiWidgetText(String text, int x, int y, GuiPanel panel) {
		super(x, y, panel);
		this.text = text;
	}
	
	public void onRender(int x, int y)
	{
		MObfuscation.drawString(text, x+xPos, y+yPos, 0xffffffff);
	}
}
