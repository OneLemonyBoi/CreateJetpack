package onelemonyboi.createjetpack;

import net.minecraft.util.text.TranslationTextComponent;

public class JetpackLang {
    public static TranslationTextComponent translate(String key, Object... args) {
        return createTranslationTextComponent(key, args);
    }

    public static TranslationTextComponent createTranslationTextComponent(String key, Object... args) {
        return new TranslationTextComponent("createjetpack." + key, args);
    }
}
