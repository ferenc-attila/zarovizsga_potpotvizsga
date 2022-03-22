package bistros;

public class Bar extends Bistro{

    public Bar(String name, Address address) {
        super(name, address);
    }

    @Override
    public String getName() {
        return super.getName().concat(" (Only Drinks)");
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        validateMenuItem(menuItem);
        super.addMenuItem(menuItem);
    }

    private void validateMenuItem(MenuItem menuItem) {
        if (menuItem.getType() == MenuItemType.FOOD) {
            throw new IllegalArgumentException("Only drink can be added to menu!");
        }
    }
}
