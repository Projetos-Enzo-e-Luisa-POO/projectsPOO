package com.badlogic.amnesia.Model.ControlInterfaces;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface RenderStrategy {
    public void render(Batch batch, float imgSize);
}
