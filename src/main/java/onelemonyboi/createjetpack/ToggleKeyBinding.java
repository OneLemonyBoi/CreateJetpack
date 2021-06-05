package onelemonyboi.createjetpack;

import net.minecraft.client.settings.KeyBinding;

public class ToggleKeyBinding extends KeyBinding {
    boolean state;

    public ToggleKeyBinding(String description, int keyCode, String category) {
        super(description, keyCode, category);
        this.state = false;
    }

    public ToggleKeyBinding(String description, int keyCode, String category, boolean state) {
        super(description, keyCode, category);
        this.state = state;
    }

    public boolean isActive() {
        return this.state;
    }

    @Override
    public void setPressed(boolean valueIn) {
        if (valueIn) {
            this.state = !this.state;
        }
        super.setPressed(valueIn);
    }
}
