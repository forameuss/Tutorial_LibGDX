package com.forameus.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.forameus.mariobros.Items.ItemDef;
import com.forameus.mariobros.Items.Mushroom;
import com.forameus.mariobros.MarioBros;
import com.forameus.mariobros.Scenes.Hud;
import com.forameus.mariobros.Screens.PlayScreen;


public class Coin extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;
    private MarioBros game;

    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }



    @Override
    public void onHeadHit() {
        if(!(getCell().getTile().getId() == BLANK_COIN))
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MarioBros.PPM),
                    Mushroom.class));


        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);
    }
}
