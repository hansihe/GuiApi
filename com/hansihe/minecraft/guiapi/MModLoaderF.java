package com.hansihe.minecraft.guiapi;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.src.UnexpectedThrowable;

public class MModLoaderF {
	
	private static Minecraft instance;
	private static String notice = "This code is written by Risugami. It's only included here for compatibility reasons.";
	
	public static Minecraft getMinecraftInstance()
	{
        if (instance == null)
        {
            try
            {
                ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
                int i = threadgroup.activeCount();
                Thread athread[] = new Thread[i];
                threadgroup.enumerate(athread);
                for (int j = 0; j < athread.length; j++)
                {
                    //System.out.println(athread[j].getName());
                }

                for (int k = 0; k < athread.length; k++)
                {
                    if (!athread[k].getName().equals("Minecraft main thread"))
                    {
                        continue;
                    }
                    instance = (Minecraft)getPrivateValue(java.lang.Thread.class, athread[k], "target");
                    break;
                }
            }
            catch (SecurityException securityexception)
            {
                throw new RuntimeException(securityexception);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                throw new RuntimeException(nosuchfieldexception);
            }
        }
        return instance;
	}
	
    public static Object getPrivateValue(Class class1, Object obj, int i)
    throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredFields()[i];
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            ThrowException("An impossible error has occured!", illegalaccessexception);
            return null;
        }
    }

    public static Object getPrivateValue(Class class1, Object obj, String s)
    throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            ThrowException("An impossible error has occured!", illegalaccessexception);
            return null;
        }
    }
	
    public static void ThrowException(String s, Throwable throwable)
    {
        Minecraft minecraft = getMinecraftInstance();
        if (minecraft != null)
        {
            minecraft.displayUnexpectedThrowable(new UnexpectedThrowable(s, throwable));
        }
        else
        {
            throw new RuntimeException(throwable);
        }
    }
}
