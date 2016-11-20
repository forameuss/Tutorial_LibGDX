package com.forameus.mariobros.Sprites.TileObjects;


import com.badlogic.gdx.maps.MapObject;
import com.forameus.mariobros.MarioBros;
import com.forameus.mariobros.Scenes.Hud;
import com.forameus.mariobros.Screens.PlayScreen;
import com.forameus.mariobros.Sprites.Mario;


public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(mario.isBig()) {
            setCategoryFilter(MarioBros.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
        }
    }
}
