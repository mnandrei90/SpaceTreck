public class PlayerCreator implements EntityCreator{
    @Override
    public Entity createEntity() {
        return new Player();
    }
}
