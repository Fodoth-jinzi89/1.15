package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.util.helpers.RenderHelper;

public class ElementButton extends ElementBase {

    public ElementButton(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        RenderHelper.bindTexture(texture);
        drawTexturedModalRect(posX(), posY(), 0, 0, width, height);

        if (enabled()) {
            if (intersectsWith(mouseX, mouseY)) {
                drawTexturedModalRect(posX(), posY(), width, 0, width, height);
            } else {
                drawTexturedModalRect(posX(), posY(), 0, 0, width, height);
            }
        } else {
            drawTexturedModalRect(posX(), posY(), width * 2, 0, width, height);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        return enabled() && gui.handleElementButtonClick(name(), mouseButton);
    }

}
