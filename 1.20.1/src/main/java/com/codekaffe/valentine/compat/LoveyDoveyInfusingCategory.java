package com.codekaffe.valentine.compat;

import com.codekaffe.valentine.KafValentine;
import com.codekaffe.valentine.block.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class LoveyDoveyInfusingCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE = new Identifier(KafValentine.MOD_ID,
            "textures/gui" + "/lovey_dovey_infuser_gui.png"
    );
    public static final CategoryIdentifier<LoveyDoveyInfusingDisplay> LOVEY_DOVEY_INFUSING = CategoryIdentifier.of(KafValentine.MOD_ID,
            "lovey_dovey_infusing"
    );

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return LOVEY_DOVEY_INFUSING;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Lovey Dovey Infuser");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.LOVEY_DOVEY_INFUSER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startPoint.x, startPoint.y, 175, 82)
        ));

        widgets.add(Widgets
                .createSlot(new Point(startPoint.x + 80, startPoint.y + 11))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets
                .createSlot(new Point(startPoint.x + 80, startPoint.y + 59))
                .markOutput()
                .entries(display.getOutputEntries().get(0)));


        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
