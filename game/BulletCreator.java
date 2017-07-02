public class BulletCreator implements EntityCreator {
    @Override
    public Entity createEntity() {
        return new Bullet();
    }
}
