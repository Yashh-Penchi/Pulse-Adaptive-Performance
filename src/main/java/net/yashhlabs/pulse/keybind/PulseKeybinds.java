package net.yashhlabs.pulse.keybind;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

// unbound for now
public final class PulseKeybinds {

	private static final KeyBinding.Category CATEGORY =
			KeyBinding.Category.create(Identifier.of("pulse", "main"));

	public static KeyBinding openHudKey;

	private PulseKeybinds() {
	}

	public static void register() {
		openHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.pulse.open_hud",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY
		));
	}
}
