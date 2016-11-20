package com.forameus.mariobros.Sprites.TileObjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.forameus.mariobros.Sprites.Items.ItemDef;
import com.forameus.mariobros.Sprites.Items.Mushroom;
import com.forameus.mariobros.MarioBros;
import com.forameus.mariobros.Scenes.Hud;
import com.forameus.mariobros.Screens.PlayScreen;
import com.forameus.mariobros.Sprites.Mario;


public class Coin extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;
    private MarioBros game;

    public Coin(PlayScreen screen, MapObject object) {
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }



    @Override
    public void onHeadHit(Mario mario) {
        if(!(getCell().getTile().getId() == BLANK_COIN))
            //Si el coin contiene la propiedad mushroom, spawnea uno
            if(object.getProperties().containsKey("mushroom"))
                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MarioBros.PPM),Mushroom.class));


        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);
    }
}
