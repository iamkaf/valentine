package com.codekaffe.valentine.block.entity.renderer;

import com.codekaffe.valentine.block.custom.LoveyDoveyInfuserBlock;
import com.codekaffe.valentine.block.entity.LoveyDoveyInfuserBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class LoveyDoveyInfuserEntityRenderer implements BlockEntityRenderer<LoveyDoveyInfuserBlockEntity> {
    public LoveyDoveyInfuserEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(LoveyDoveyInfuserBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();

        matrices.push();
        matrices.translate(0.5f, 1.1f, 0.5f);
        matrices.scale(0.35f, 0.35f, 0.35f);

        World world = entity.getWorld();

        if (world != null) {
            BlockState blockState = world.getBlockState(entity.getPos());
            if (blockState.getBlock() instanceof LoveyDoveyInfuserBlock) {
                float blockRotation = blockState.get(Properties.HORIZONTAL_FACING).asRotation();
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(blockRotation));
            }
        }

        itemRenderer.renderItem(
                stack,
                ModelTransformationMode.GUI,
                getLightLevel(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                1
        );
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
