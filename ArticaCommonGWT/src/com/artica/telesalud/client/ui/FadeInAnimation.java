package com.artica.telesalud.client.ui;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.UIObject;

public class FadeInAnimation extends Animation {
    private final UIObject uiObject;

    FadeInAnimation(final UIObject uiObject) {
      this.uiObject = uiObject;
    }

    @Override
    protected void onUpdate(final double progress) {
      uiObject.getElement().getStyle().setOpacity(progress);
    }
}