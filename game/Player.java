public class Player extends Entity {
    @Override
    public void init(StarTrek ref, int x, int y, Sprite sprite, EntityType type) {
        this.ref = ref;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.type = type;
    }

    public Entity fire() {
        EntityCreator bulletCreator = new BulletCreator();
        Entity bullet = bulletCreator.createEntity();
        bullet.init(ref, this.x + 20, this.y, new Sprite("bullet.gif"), EntityType.Bullet);
        bullet.setSpeedY(500);

        return bullet;
    }
}
