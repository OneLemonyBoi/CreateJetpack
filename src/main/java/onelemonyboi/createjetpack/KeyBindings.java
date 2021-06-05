package onelemonyboi.createjetpack;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding activateJetpack = new KeyBinding("createjetpack.key.trigger", GLFW.GLFW_KEY_SPACE, "createjetpack.category.jetpack");
    public static ToggleKeyBinding toggleHover = new ToggleKeyBinding("createjetpack.key.hover", GLFW.GLFW_KEY_SEMICOLON, "createjetpack.category.jetpack");

    public static void register() {
        ClientRegistry.registerKeyBinding(activateJetpack);
        ClientRegistry.registerKeyBinding(toggleHover);
    }
}
