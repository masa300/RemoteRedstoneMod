package jp.masa300.remoteredstone.gui;

import jp.masa300.remoteredstone.RemoteRedstoneCore;
import jp.masa300.remoteredstone.block.tileentity.RemoteRedstone;
import jp.masa300.remoteredstone.network.PacketRemoteRedstone;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;


public class GUIRemoteRedstone extends GuiScreenCustom {
    private final RemoteRedstone tileEntity;
    private String remoteRSId;
    private RemoteRedstone.RemoteRSType type;

    public GUIRemoteRedstone(RemoteRedstone tileEntity) {
        this.tileEntity = tileEntity;
        this.remoteRSId = tileEntity.getRemoteRSId();
        this.type = tileEntity.getRemoteRSType();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick) {
        super.drawScreen(mouseX, mouseY, partialTick);
        this.fontRendererObj.drawString(this.type.name, this.width / 2 - 100, this.height / 2 - 30, 0xffffff);
//        this.fontRendererObj.drawString("ID", this.width / 2 - 100, this.height / 2 - 30, 0xffffff);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addGuiTextField(this.remoteRSId, this.width / 2 - 100, this.height / 2 - 20, Byte.MAX_VALUE, 200);
        this.buttonList.add(new GuiButton(21, this.width / 2 - 110, this.height - 30, 100, 20, "決定"));
        this.buttonList.add(new GuiButton(20, this.width / 2 + 10, this.height - 30, 100, 20, "キャンセル"));
    }

    @Override
    public void keyTyped(char par1, int par2) {
        super.keyTyped(par1, par2);
        this.textFieldList.forEach(textField -> textField.textboxKeyTyped(par1, par2));
        if (par2 == Keyboard.KEY_RETURN) {
            this.sendPacket();
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 20) {  //キャンセル
            this.mc.displayGuiScreen(null);
        } else if (button.id == 21) {    //決定
            this.sendPacket();
            this.mc.displayGuiScreen(null);
        }
    }

    private int getIntGuiTextFieldText(int number) {
        String str = this.textFieldList.get(number).getText();
        int i = 0;
        if (str == null || str.isEmpty()) {
            return i;
        }

        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return i;
        }
        return i;
    }

    private void addGuiTextField(String str, int xPosition, int yPosition, int maxLength, int width) {
        GuiTextField text = new GuiTextField(this.fontRendererObj, xPosition, yPosition, width, 20);
        text.setFocused(false);
        text.setMaxStringLength(maxLength);
        text.setText(str);
        this.textFieldList.add(text);
    }

    private void sendPacket() {
        this.saveValue();
        RemoteRedstoneCore.NETWORK_WRAPPER.sendToServer(new PacketRemoteRedstone(this.tileEntity, this.remoteRSId));
    }

    private void saveValue() {
        this.remoteRSId = this.textFieldList.get(0).getText();
    }
}
