package com.hansihe.minecraft.guiapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiController {

	private ArrayList<GuiPanel> panels;
	
	//Panel Parent
	//Mouse Parent
	private GuiPanel mouseparent = null;
	private int mouseparentx;
	private int mouseparenty;
	public int zlevel = 0;
	
	/**
	 * This makes a new GuiController. Normally, only one of these should be created per scene.
	 * 
	 * Functions to call:
	 * handleMouseInput - returns false on panel interaction, should be called from handleMouseInput
	 * handleKeyboardInput - returns false on panel interaction, should be called from handleKeyboardInput
	 * onDrawScreen - should be called as last in drawScreen
	 */
	public GuiController()
	{
		panels = new ArrayList<GuiPanel>();
	}
	
	//	Rendering/Updating
	
	public boolean handleMouseInput(int soHeight, int soWidth)
	{
        int x = (Mouse.getEventX() * soWidth) / (MObfuscation.getMinecraft().displayWidth/2);
        int y = (soHeight*2) - (Mouse.getEventY() * soHeight) / (MObfuscation.getMinecraft().displayHeight/2) - 1;
		if(Mouse.getEventButtonState())
		{
			for(GuiPanel curr : panels)
			{
				if(curr.isOnPanel(x, y))
				{
					if(curr.isOnMoveBar(x, y) && Mouse.getEventButton() == 0)
					{
						mouseparent = curr;
						mouseparentx = x - curr.x;
						mouseparenty = y - curr.y;
					}
					else
					{
						curr.mouseAction(Mouse.getEventButtonState(), Mouse.getEventButton(), x, y);
					}
					setFocus(curr);
					return false;
				}
			}
			return true;
		}
		else
		{
			if(mouseparent == null)
			{
				for(GuiPanel curr : panels)
				{
					if(curr.isOnPanel(x, y))
					{
						curr.mouseAction(Mouse.getEventButtonState(), Mouse.getEventButton(), x, y);
						return false;
					}
					curr.mouseMove();
				}
				return true;
			} else {
				if(Mouse.getEventButton() == -1)
				{
					mouseparent.x = x - mouseparentx;
					mouseparent.y = y - mouseparenty;
				} 
				else if(Mouse.getEventButton() == 0)
				{
					mouseparent = null;
				}
				return false;
			}
		}
	}
	
	public boolean handleKeyboardInput()
	{
		for(GuiPanel curr : panels)
		{
			//Do key shit here
		}
		return true;
	}
	
	/**
	 * Call when the panels should be drawn. This is usually after everything else.
	 */
	public void onDrawScreen()
	{
		GL11.glPushMatrix();
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		ListIterator<GuiPanel> li = panels.listIterator(panels.size());
		while(li.hasPrevious())
		{
			li.previous().render();
		}
		GL11.glPopMatrix();
	}
	
	//	Panel Managing
	
	/**
	 * Add a panel to the controller
	 * @param panel
	 * @return true if successful
	 */
	public boolean addPanel(GuiPanel panel)
	{
		if(!panels.contains(panel))
		{
			boolean p = panels.add(panel);
			return p;
		}
		return false;
	}
	
	/**
	 * Remove a panel from the controller
	 * @param panel
	 * @return true if successful
	 */
	public boolean removePanel(GuiPanel panel)
	{
		boolean p = panels.remove(panel);
		return p;
	}
	
	/**
	 * 
	 * @return - ArrayList with all panels in controller
	 */
	public ArrayList<GuiPanel> getPanels()
	{
		return panels;
	}
	
	//Internal Panel Managing
	
	/**
	 * Set the provided panel as the top one in the stack
	 * @param focus - the panel to set in focus
	 */
	private void setFocus(GuiPanel focus)
	{
		ArrayList<GuiPanel> newp = new ArrayList<GuiPanel>();
		for(GuiPanel curr : panels)
		{
			if(curr == focus)
			{
				newp.add(curr);
			}
		}
		for(GuiPanel curr : panels)
		{
			if(curr != focus)
			{
				newp.add(curr);
			}
		}
		panels = newp;
	}
}
