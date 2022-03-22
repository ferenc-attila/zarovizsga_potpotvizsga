package settlers;

public class Settler {

    private static final int INCOME_PER_TON = 500;

    private Long id;
    private String nameOfSettler;
    private int amountOfTobacco;
    private int expectedIncome;

    public Settler(String nameOfSettler, int amountOfTobacco) {
        this.nameOfSettler = nameOfSettler;
        this.amountOfTobacco = amountOfTobacco;
    }

    public Settler(Long id, String nameOfSettler, int amountOfTobacco) {
        this(nameOfSettler, amountOfTobacco);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNameOfSettler() {
        return nameOfSettler;
    }

    public int getAmountOfTobacco() {
        return amountOfTobacco;
    }

    public int getExpectedIncome() {
        return amountOfTobacco * INCOME_PER_TON;
    }

    public void setNameOfSettler(String nameOfSettler) {
        this.nameOfSettler = nameOfSettler;
    }

    public void setAmountOfTobacco(int amountOfTobacco) {
        this.amountOfTobacco = amountOfTobacco;
    }

    public void setExpectedIncome(int expectedIncome) {
        this.expectedIncome = expectedIncome;
    }
}
