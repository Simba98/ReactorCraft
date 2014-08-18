/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 27/11/2013 7:15:19 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.ReactorCraft.Models;

import Reika.DragonAPI.Instantiable.Rendering.LODModelPart;
import Reika.RotaryCraft.Base.RotaryModelBase;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;

public class ModelWasteStorage extends RotaryModelBase
{
	//fields
	LODModelPart Shape1;
	LODModelPart Shape2;
	LODModelPart Shape3;
	LODModelPart Shape4;
	LODModelPart Shape5;
	LODModelPart Shape6;
	LODModelPart Shape3a;
	LODModelPart Shape4a;
	LODModelPart Shape5a;
	LODModelPart Shape6a;
	LODModelPart Shape1a;
	LODModelPart Shape7;
	LODModelPart Shape7a;
	LODModelPart Shape7b;
	LODModelPart Shape7c;
	LODModelPart Shape7d;
	LODModelPart Shape7e;
	LODModelPart Shape7f;
	LODModelPart Shape7g;

	public ModelWasteStorage()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new LODModelPart(this, 15, 38);
		Shape1.addBox(0F, 0F, -1F, 2, 16, 1);
		Shape1.setRotationPoint(-1F, 8F, 8F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new LODModelPart(this, 0, 0);
		Shape2.addBox(0F, 0F, -1F, 16, 16, 2);
		Shape2.setRotationPoint(-8F, 8F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new LODModelPart(this, 37, 19);
		Shape3.addBox(0F, 0F, -1F, 14, 16, 2);
		Shape3.setRotationPoint(-7F, 8F, 2F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new LODModelPart(this, 70, 19);
		Shape4.addBox(0F, 0F, -1F, 12, 16, 2);
		Shape4.setRotationPoint(-6F, 8F, 4F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new LODModelPart(this, 99, 19);
		Shape5.addBox(0F, 0F, -1F, 10, 16, 1);
		Shape5.setRotationPoint(-5F, 8F, 6F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new LODModelPart(this, 0, 38);
		Shape6.addBox(0F, 0F, -1F, 6, 16, 1);
		Shape6.setRotationPoint(-3F, 8F, 7F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		this.setRotation(Shape6, 0F, 0F, 0F);
		Shape3a = new LODModelPart(this, 37, 0);
		Shape3a.addBox(0F, 0F, -1F, 14, 16, 2);
		Shape3a.setRotationPoint(-7F, 8F, -2F);
		Shape3a.setTextureSize(128, 128);
		Shape3a.mirror = true;
		this.setRotation(Shape3a, 0F, 0F, 0F);
		Shape4a = new LODModelPart(this, 70, 0);
		Shape4a.addBox(0F, 0F, -1F, 12, 16, 2);
		Shape4a.setRotationPoint(-6F, 8F, -4F);
		Shape4a.setTextureSize(128, 128);
		Shape4a.mirror = true;
		this.setRotation(Shape4a, 0F, 0F, 0F);
		Shape5a = new LODModelPart(this, 99, 0);
		Shape5a.addBox(0F, 0F, -1F, 10, 16, 1);
		Shape5a.setRotationPoint(-5F, 8F, -5F);
		Shape5a.setTextureSize(128, 128);
		Shape5a.mirror = true;
		this.setRotation(Shape5a, 0F, 0F, 0F);
		Shape6a = new LODModelPart(this, 0, 19);
		Shape6a.addBox(0F, 0F, -1F, 6, 16, 1);
		Shape6a.setRotationPoint(-3F, 8F, -6F);
		Shape6a.setTextureSize(128, 128);
		Shape6a.mirror = true;
		this.setRotation(Shape6a, 0F, 0F, 0F);
		Shape1a = new LODModelPart(this, 15, 19);
		Shape1a.addBox(0F, 0F, -1F, 2, 16, 1);
		Shape1a.setRotationPoint(-1F, 8F, -7F);
		Shape1a.setTextureSize(128, 128);
		Shape1a.mirror = true;
		this.setRotation(Shape1a, 0F, 0F, 0F);
		Shape7 = new LODModelPart(this, 0, 56);
		Shape7.addBox(0F, 0F, 0F, 8, 1, 15);
		Shape7.setRotationPoint(-4F, 13F, -7.5F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		this.setRotation(Shape7, 0F, 0F, 0F);
		Shape7a = new LODModelPart(this, 0, 74);
		Shape7a.addBox(0F, 0F, 0F, 15, 1, 8);
		Shape7a.setRotationPoint(-7.5F, 22F, -4F);
		Shape7a.setTextureSize(128, 128);
		Shape7a.mirror = true;
		this.setRotation(Shape7a, 0F, 0F, 0F);
		Shape7b = new LODModelPart(this, 0, 56);
		Shape7b.addBox(0F, 0F, 0F, 8, 1, 15);
		Shape7b.setRotationPoint(-4F, 22F, -7.5F);
		Shape7b.setTextureSize(128, 128);
		Shape7b.mirror = true;
		this.setRotation(Shape7b, 0F, 0F, 0F);
		Shape7c = new LODModelPart(this, 0, 56);
		Shape7c.addBox(0F, 0F, 0F, 8, 1, 15);
		Shape7c.setRotationPoint(-4F, 18F, -7.5F);
		Shape7c.setTextureSize(128, 128);
		Shape7c.mirror = true;
		this.setRotation(Shape7c, 0F, 0F, 0F);
		Shape7d = new LODModelPart(this, 0, 56);
		Shape7d.addBox(0F, 0F, 0F, 8, 1, 15);
		Shape7d.setRotationPoint(-4F, 9F, -7.5F);
		Shape7d.setTextureSize(128, 128);
		Shape7d.mirror = true;
		this.setRotation(Shape7d, 0F, 0F, 0F);
		Shape7e = new LODModelPart(this, 0, 74);
		Shape7e.addBox(0F, 0F, 0F, 15, 1, 8);
		Shape7e.setRotationPoint(-7.5F, 9F, -4F);
		Shape7e.setTextureSize(128, 128);
		Shape7e.mirror = true;
		this.setRotation(Shape7e, 0F, 0F, 0F);
		Shape7f = new LODModelPart(this, 0, 74);
		Shape7f.addBox(0F, 0F, 0F, 15, 1, 8);
		Shape7f.setRotationPoint(-7.5F, 13F, -4F);
		Shape7f.setTextureSize(128, 128);
		Shape7f.mirror = true;
		this.setRotation(Shape7f, 0F, 0F, 0F);
		Shape7g = new LODModelPart(this, 0, 74);
		Shape7g.addBox(0F, 0F, 0F, 15, 1, 8);
		Shape7g.setRotationPoint(-7.5F, 18F, -4F);
		Shape7g.setTextureSize(128, 128);
		Shape7g.mirror = true;
		this.setRotation(Shape7g, 0F, 0F, 0F);
	}

	@Override
	public void renderAll(TileEntity te, ArrayList li, float phi, float theta)
	{
		Shape1.render(te, f5);
		Shape2.render(te, f5);
		Shape3.render(te, f5);
		Shape4.render(te, f5);
		Shape5.render(te, f5);
		Shape6.render(te, f5);
		Shape3a.render(te, f5);
		Shape4a.render(te, f5);
		Shape5a.render(te, f5);
		Shape6a.render(te, f5);
		Shape1a.render(te, f5);
		Shape7.render(te, f5);
		Shape7a.render(te, f5);
		Shape7b.render(te, f5);
		Shape7c.render(te, f5);
		Shape7d.render(te, f5);
		Shape7e.render(te, f5);
		Shape7f.render(te, f5);
		Shape7g.render(te, f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

}
