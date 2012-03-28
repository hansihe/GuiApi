package com.hansihe.minecraft.guiapi;

public abstract class GuiWidgetBase {

	public int xPos;
	public int yPos;
	public GuiPanel panel;
	
	public GuiWidgetBase(int x, int y, GuiPanel panel)
	{
		xPos = x;
		yPos = y;
		this.panel = panel;
	}
	
	public boolean isOnWidget(int x, int y)
	{
		return false;
	}
	
	public abstract void onRender(int x, int y);
	
	public void onKeyPress(char c, int code)
	{
		
	}
	
	public void onMousePress(int x, int y, int button)
	{
		
	}
	
	public void onMouseInteract(int x, int y, int button)
	{
		
	}
	
	public void onMouseMove()
	{
		
	}
}
