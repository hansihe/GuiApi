package com.hansihe.minecraft.guiapi;

public class GuiWidgetButton extends GuiWidgetBase {

	public String text = "";
	public int width;
	public int height;
	private int aposx;
	private int aposy;
	public boolean centered;
	private boolean active = false;
	
	public GuiWidgetButton(String text, int width, int height, boolean centered, int x, int y, GuiPanel panel) {
		super(x, y, panel);
		this.text = text;
		this.width = width;
		this.height = height;
		this.centered = centered;
	}
	
	public void onRender(int x, int y)
	{
		aposx = x;
		aposy = y;
		if(active)
		{
			MObfuscation.drawRect(xPos+x, yPos+y, xPos+x+width, yPos+y+height, 0xff808080);
		} else {
			MObfuscation.drawRect(xPos+x, yPos+y, xPos+x+width, yPos+y+height, 0xff666666);
		}
		MObfuscation.drawString(text, x+xPos+1, y+yPos+1, 0xffffffff);
	}
	
	public void onMouseInteract(int x, int y, int button)
	{
		if(isOnButton(x, y))
		{
			active = true;
		} else {
			active = false;
		}
	}
	
	public void onMouseMove()
	{
		//active = false;
	}
	
	public boolean isOnButton(int x, int y)
	{
		return (x>=(xPos+aposx) && x<=(xPos+aposx+width) && y>=(yPos+aposy) && y<=(yPos+aposy+height));
	}
}