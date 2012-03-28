package com.hansihe.minecraft.guiapi;

import java.util.ArrayList;

public class GuiPanel {
	
	public int x;
	public int y;
	private int height;
	private int width;
	private String title;
	public boolean extended = true;
	private boolean statushighl = false;
	public int id;
	
	private ArrayList<GuiWidgetBase> widgets = new ArrayList<GuiWidgetBase>() ;
	
	public GuiPanel(int x, int y, int height, int width, String title, int id)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.title = title;
		this.id = id;
	}
	
	//Rendering
	
	/**
	 * Called on panel render by the controller. Should not normally be called manually.
	 */
	public void render()
	{
		if(extended)
		{
			MObfuscation.drawRect(x, y, x+width, y+height, 0xC8323232); //Base
			MObfuscation.drawRect(x, y, x+width, y+10, 0xff323296); //Top control bar, background
			MObfuscation.drawString(title, x+2, y+2, 0xffffffff);
			MObfuscation.drawRectOutline(x, y, width, height, 0xff000000); //Fancy-ass black outline
			if(statushighl)
			{
				MObfuscation.drawRect(x+width-10, y+10, x+width, y+1, 0xff008800); //Minimize
			} else {
				MObfuscation.drawRect(x+width-10, y+10, x+width, y+1, 0xff006000); //Minimize
			}
			
			for (GuiWidgetBase widget : widgets)
			{
				widget.onRender(x, y);
			}
		}
		else
		{
			MObfuscation.drawRect(x, y, x+width, y+10, 0xff323296); //Top control bar, background
			MObfuscation.drawString(title, x+2, y+2, 0xffffffff);
			MObfuscation.drawRectOutline(x, y, width, 10, 0xff000000); //Fancy-ass black outline
			if(statushighl)
			{
				MObfuscation.drawRect(x+width-10, y+10, x+width, y+1, 0xff880000); //Maximize
			} else {
				MObfuscation.drawRect(x+width-10, y+10, x+width, y+1, 0xff600000); //Maximize
			}
		}
	}
	
	//Interaction
	
	/**
	 * Checks if the provided mouse coordinates are on the panel
	 * @param mouseX
	 * @param mouseY
	 * @return true if on panel
	 */
	public boolean isOnPanel(int mouseX, int mouseY)
	{
		if(extended)
		{
			return (mouseX>=x && mouseX<=(x+width) && mouseY>=y && mouseY<=(y+height));
		}
		else
		{
			return (mouseX>=x && mouseX<=(x+width) && mouseY>=y && mouseY<=(y+10));
		}
	}
	
	/**
	 * Checks if the provided mouse coordinates are on a dragable part of the panel
	 * @param mouseX
	 * @param mouseY
	 * @return true if dragable
	 */
	public boolean isOnMoveBar(int mouseX, int mouseY)
	{
		return (mouseX>=x && mouseX<=(x+width-10) && mouseY>=y && mouseY<=(y+10));
	}
	
	/**
	 * Checks if the provided mouse coordinates are on the minimize/maximize button
	 * @param mouseX
	 * @param mouseY
	 * @return
	 */
	public boolean isOnStatusButton(int mouseX, int mouseY)
	{
		return (mouseX>=(x+width-10) && mouseX<=(x+width) && mouseY>=(y+1) && mouseY<=(y+10));
	}
	
	public void mouseAction(boolean action, int button, int x, int y)
	{
		if(action)
		{
			if(isOnStatusButton(x, y))
			{
				if(button==0)
				{
					extended = !extended;
				}
			}
			for (GuiWidgetBase widget : widgets)
			{
				widget.onMousePress(x, y, button);
			}
		}
		else
		{
			if(isOnStatusButton(x, y))
			{
				if(button == -1)
				{
					statushighl = true;
				}
			}
			else
			{
				statushighl = false;
			}
			for (GuiWidgetBase widget : widgets)
			{
				widget.onMouseInteract(x, y, button);
			}
		}
	}
	
	public void mouseMove()
	{
		statushighl = false;
		for (GuiWidgetBase widget : widgets)
		{
			widget.onMouseMove();
		}
	}
	
	public boolean addWidget(GuiWidgetBase widget)
	{
		if(!widgets.contains(widget))
		{
			return widgets.add(widget);
		}
		return false;
	}
	
	public boolean removeWidget(GuiWidgetBase widget)
	{
		return widgets.remove(widget);
	}
}
