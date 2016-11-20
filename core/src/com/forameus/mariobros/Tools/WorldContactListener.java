package com.forameus.mariobros.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.forameus.mariobros.Sprites.Items.Item;
import com.forameus.mariobros.MarioBros;
import com.forameus.mariobros.Sprites.Enemies.Enemy;
import com.forameus.mariobros.Sprites.TileObjects.InteractiveTileObject;
import com.forameus.mariobros.Sprites.Mario;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = fixA.getUserData() == "head" ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject)
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
        }

        switch (cDef){
            case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
                    ((Enemy)(fixA.getUserData())).hitOnHead();
                else
                    ((Enemy)(fixB.getUserData())).hitOnHead();
                break;

            case MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_BIT)
                    ((Enemy)(fixA.getUserData())).reverseVelocity(true, false);
                else
                    ((Enemy)(fixB.getUserData())).reverseVelocity(true, false);
                break;

            case MarioBros.MARIO_BIT | MarioBros.ENEMY_BIT:
                Gdx.app.log("MARIO", "DIED");
                break;

            case MarioBros.ENEMY_BIT | MarioBros.ENEMY_BIT:
                ((Enemy)(fixA.getUserData())).reverseVelocity(true, false);
                ((Enemy)(fixB.getUserData())).reverseVelocity(true, false);
                break;

            case MarioBros.ITEM_BIT | MarioBros.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
                    ((Item)(fixA.getUserData())).reverseVelocity(true, false);
                else
                    ((Item)(fixB.getUserData())).reverseVelocity(true, false);
                break;

            case MarioBros.ITEM_BIT | MarioBros.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
                    ((Item)(fixA.getUserData())).use((Mario) fixB.getUserData());
                else
                    ((Item)(fixB.getUserData())).use((Mario) fixA.getUserData());
                break;

        }


    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
