/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 08/09/2013 5:51:35 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.ReactorCraft.Models;

import Reika.DragonAPI.Instantiable.Rendering.LODModelPart;
import Reika.RotaryCraft.Base.RotaryModelBase;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class ModelCentrifuge extends RotaryModelBase
{
	//fields
	LODModelPart Shape2;
	LODModelPart Shape2a;
	LODModelPart Shape2b;
	LODModelPart Shape2c;
	LODModelPart Shape2d;
	LODModelPart Shape2e;
	LODModelPart Shape2f;
	LODModelPart Shape2g;
	LODModelPart Shape1;
	LODModelPart Shape3;
	LODModelPart Shape3a;
	LODModelPart Shape3b;
	LODModelPart Shape3c;

	public ModelCentrifuge()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape2 = new LODModelPart(this, 0, 18);
		Shape2.addBox(6F, 0F, -3F, 1, 14, 6);
		Shape2.setRotationPoint(0F, 9F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape2a = new LODModelPart(this, 0, 18);
		Shape2a.addBox(-3F, 0F, 6F, 6, 14, 1);
		Shape2a.setRotationPoint(0F, 9F, 0F);
		Shape2a.setTextureSize(128, 128);
		Shape2a.mirror = true;
		this.setRotation(Shape2a, 0F, 0F, 0F);
		Shape2b = new LODModelPart(this, 0, 18);
		Shape2b.addBox(-7F, 0F, -3F, 1, 14, 6);
		Shape2b.setRotationPoint(0F, 9F, 0F);
		Shape2b.setTextureSize(128, 128);
		Shape2b.mirror = true;
		this.setRotation(Shape2b, 0F, -0.7853982F, 0F);
		Shape2c = new LODModelPart(this, 0, 18);
		Shape2c.addBox(-3F, 0F, -7F, 6, 14, 1);
		Shape2c.setRotationPoint(0F, 9F, 0F);
		Shape2c.setTextureSize(128, 128);
		Shape2c.mirror = true;
		this.setRotation(Shape2c, 0F, 0F, 0F);
		Shape2d = new LODModelPart(this, 0, 18);
		Shape2d.addBox(-7F, 0F, -3F, 1, 14, 6);
		Shape2d.setRotationPoint(0F, 9F, 0F);
		Shape2d.setTextureSize(128, 128);
		Shape2d.mirror = true;
		this.setRotation(Shape2d, 0F, 0F, 0F);
		Shape2e = new LODModelPart(this, 0, 18);
		Shape2e.addBox(-7F, 0F, -3F, 1, 14, 6);
		Shape2e.setRotationPoint(0F, 9F, 0F);
		Shape2e.setTextureSize(128, 128);
		Shape2e.mirror = true;
		this.setRotation(Shape2e, 0F, 0.7853982F, 0F);
		Shape2f = new LODModelPart(this, 0, 18);
		Shape2f.addBox(-7F, 0F, -3F, 1, 14, 6);
		Shape2f.setRotationPoint(0F, 9F, 0F);
		Shape2f.setTextureSize(128, 128);
		Shape2f.mirror = true;
		this.setRotation(Shape2f, 0F, 2.356194F, 0F);
		Shape2g = new LODModelPart(this, 0, 18);
		Shape2g.addBox(-7F, 0F, -3F, 1, 14, 6);
		Shape2g.setRotationPoint(0F, 9F, 0F);
		Shape2g.setTextureSize(128, 128);
		Shape2g.mirror = true;
		this.setRotation(Shape2g, 0F, -2.356194F, 0F);
		Shape1 = new LODModelPart(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape1.setRotationPoint(-8F, 23F, -8F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape3 = new LODModelPart(this, 0, 61);
		Shape3.addBox(-6F, 0F, -3F, 12, 1, 6);
		Shape3.setRotationPoint(0F, 8.5F, 0F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0.7853982F, 0F);
		Shape3a = new LODModelPart(this, 0, 39);
		Shape3a.addBox(-3F, 0F, -6F, 6, 1, 12);
		Shape3a.setRotationPoint(0F, 8.4F, 0F);
		Shape3a.setTextureSize(128, 128);
		Shape3a.mirror = true;
		this.setRotation(Shape3a, 0F, 0.7679449F, 0F);
		Shape3b = new LODModelPart(this, 0, 53);
		Shape3b.addBox(-6F, 0F, -3F, 12, 1, 6);
		Shape3b.setRotationPoint(0F, 8.1F, 0F);
		Shape3b.setTextureSize(128, 128);
		Shape3b.mirror = true;
		this.setRotation(Shape3b, 0F, 0F, 0F);
		Shape3c = new LODModelPart(this, 0, 69);
		Shape3c.addBox(-3F, 0F, -6F, 6, 1, 12);
		Shape3c.setRotationPoint(0F, 8F, 0F);
		Shape3c.setTextureSize(128, 128);
		Shape3c.mirror = true;
		this.setRotation(Shape3c, 0F, 0F, 0F);
	}

	@Override
	public void renderAll(TileEntity te, ArrayList li, float phi, float theta)
	{
		Shape1.render(te, f5);

		GL11.glRotatef(phi, 0, 1, 0);

		Shape2.render(te, f5);
		Shape2a.render(te, f5);
		Shape2b.render(te, f5);
		Shape2c.render(te, f5);
		Shape2d.render(te, f5);
		Shape2e.render(te, f5);
		Shape2f.render(te, f5);
		Shape2g.render(te, f5);
		Shape3.render(te, f5);
		Shape3a.render(te, f5);
		Shape3b.render(te, f5);
		Shape3c.render(te, f5);

		GL11.glRotatef(-phi, 0, 1, 0);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

}
