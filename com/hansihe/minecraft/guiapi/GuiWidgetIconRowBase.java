package com.hansihe.minecraft.guiapi;

import org.lwjgl.opengl.GL11;

public abstract class GuiWidgetIconRowBase extends GuiWidgetBase {

	private DataHandler handler;
	private int textcolor;
	
	public GuiWidgetIconRowBase(int x, int y, GuiPanel panel, DataHandler handler, int textcolor) {
		super(x, y, panel);
		this.handler = handler;
		this.textcolor = textcolor;
	}

	@Override
	public void onRender(int x, int y) {
		Object[] data = handler.provider();
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, MObfuscation.getMinecraft().renderEngine.getTexture(getTexture()));
		
		for(int k=0; k<((Integer)data[0]/2); k++)
		{
			renderIcon(x+xPos+(8*k), y+yPos, 0);
		}
		int i;
		for(i=0; i<((Integer)data[1]/2); i++)
		{
			renderIcon(x+xPos+(8*i), y+yPos, 1);
		}
		if(((Integer)data[1]%2)==1)
		{
			renderIcon(x+xPos+(8*i), y+yPos, 2);
		}
		MObfuscation.drawCenteredString((Integer)data[1]+"/"+(Integer)data[0], x+xPos+((Integer)data[0]*2), y+yPos+10, textcolor);
	}

	/**
	 * 
	 * @param xpos
	 * @param ypos
	 * @param type - 0 for background, 1 for whole, 2 for half
	 */
	public abstract void renderIcon(int xpos, int ypos, int type);
	
	public String getTexture()
	{
		return "/gui/icons.png";
	}
}
