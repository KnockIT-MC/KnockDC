package io.github.keishispl.knockDC.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public final class Component {
    private TextComponent component;

    public Component() {
        this.component = new TextComponent();
    }

    public Component(String text) {
        this.component = new TextComponent(format(text));
    }

    @Deprecated
    public Component setHover(String text) {
        this.setHover(HoverEvent.Action.SHOW_TEXT, format(text));
        return this;
    }

    @Deprecated
    public Component setHover(HoverEvent.Action action, String text) {
        this.component.setHoverEvent(new HoverEvent(action, new ComponentBuilder(format(text)).create()));
        return this;
    }

    public Component setClick(String url) {
        this.setClick(ClickEvent.Action.OPEN_URL, url);
        return this;
    }

    public Component setClick(ClickEvent.Action action, String value) {
        this.component.setClickEvent(new ClickEvent(action, value));
        return this;
    }

    public TextComponent create() { return this.component; }

    private String format(String text) {
        return ChatUtils.translateHexColors(text);
    }
}
