package net.yashhlabs.pulse.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

/**
 * Wires PulseConfig into Mod Menu's "config" button. Only loaded by Mod
 * Menu itself when Mod Menu is installed — see the "modmenu" entrypoint
 * in fabric.mod.json.
 */
public class PulseModMenuIntegration implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(PulseConfig.class, parent).get();
	}
}
