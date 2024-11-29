package io.github.angrybirds.entities;

import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {
    private static final float minVel = 0.3f;

    @Override
    public void beginContact(Contact contact){
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if(fixtureA.getUserData() instanceof Bird && fixtureB.getUserData() instanceof Pig){
            Pig pig = (Pig)fixtureB.getUserData();
            Bird bird = (Bird)fixtureA.getUserData();
            if(pig.getLinearVelocity()<=minVel && bird.getLinearVelocity()<=minVel) {
                return;
            }
            pig.takeDamage(bird.getDamage());
            //System.out.println("Collision began between " + fixtureA.getUserData() + " and " + fixtureB.getUserData());
        }
        else if(fixtureB.getUserData() instanceof Bird && fixtureA.getUserData() instanceof Pig){
            Pig pig = (Pig)fixtureA.getUserData();
            Bird bird = (Bird)fixtureB.getUserData();
            if(pig.getLinearVelocity()<=minVel && bird.getLinearVelocity()<=minVel) {
                return;
            }
            pig.takeDamage(bird.getDamage());
            //System.out.println("Collision began between " + fixtureB.getUserData() + " and " + fixtureB.getUserData());
        }

        if(fixtureA.getUserData() instanceof Bird && fixtureB.getUserData() instanceof Structures){
            Structures struct = (Structures)fixtureB.getUserData();
            Bird bird = (Bird)fixtureA.getUserData();
            if(struct.getLinearVelocity()<=minVel && bird.getLinearVelocity()<=minVel) {
                return;
            }
            struct.takeHit(bird.getDamage());
            //System.out.println("Collision began between " + fixtureA.getUserData() + " and " + fixtureB.getUserData());
        }
        else if(fixtureB.getUserData() instanceof Bird && fixtureA.getUserData() instanceof Structures){
            Structures struct = (Structures)fixtureA.getUserData();
            Bird bird = (Bird)fixtureB.getUserData();
            if(struct.getLinearVelocity()<=minVel && bird.getLinearVelocity()<=minVel) {
                return;
            }
            struct.takeHit(bird.getDamage());
            //System.out.println("Collision began between " + fixtureB.getUserData() + " and " + fixtureB.getUserData());
        }

        if(fixtureA.getUserData() instanceof Structures && fixtureB.getUserData() instanceof Pig){
            Pig pig = (Pig)fixtureB.getUserData();
            Structures struct = (Structures)fixtureA.getUserData();
            if(struct.getLinearVelocity()<=minVel || pig.getLinearVelocity()<=minVel) {
                return;
            }
            pig.takeDamage(1);
            struct.takeHit(1);
            //System.out.println("Collision began between " + fixtureA.getUserData() + " and " + fixtureB.getUserData());
        }
        else if(fixtureB.getUserData() instanceof Structures && fixtureA.getUserData() instanceof Pig){
            Pig pig = (Pig)fixtureA.getUserData();
            Structures struct = (Structures)fixtureB.getUserData();
            if(struct.getLinearVelocity()<=minVel || pig.getLinearVelocity()<=minVel) {
                return;
            }
            pig.takeDamage(1);
            struct.takeHit(1);
            //System.out.println("Collision began between " + fixtureB.getUserData() + " and " + fixtureB.getUserData());
        }
    }

    @Override
    public void endContact(Contact contact){
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        ////System.out.println("Collision ended between " + fixtureA.getUserData() + " and " + fixtureB.getUserData());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Called before collision response is resolved
        // Modify collision properties here if needed
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Called after collision response is resolved
        // Use impulse to determine the strength of the collision
    }
}
