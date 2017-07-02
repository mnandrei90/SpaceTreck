public class Bullet extends Entity {

    @Override
    public void init(StarTrek ref, int x, int y, Sprite sprite, EntityType type) {
        this.ref = ref;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.type = type;
    }
}
