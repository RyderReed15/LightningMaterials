package lightningmats.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LightningEntity extends EntityThrowable
{
    public LightningEntity(World par1World)
    {
        super(par1World);
    }

    public LightningEntity(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }

    public LightningEntity(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            byte b0 = 0;

            if (par1MovingObjectPosition.entityHit instanceof EntityBlaze)
            {
                b0 = 3;
            }

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.anvil, 10);
        }

        for (int i = 0; i < 8; ++i)
        {
        	this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.explode();
            this.setDead();
            
        }
    }
    private void explode()
    {
       
    {
    float f = 0.1F;
        this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
    }
}
    
}
