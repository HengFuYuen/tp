package seedu.dietbook.list;

import seedu.dietbook.food.Food;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatedFoodEntry extends FoodEntry implements Comparable<DatedFoodEntry> {

    private final LocalDateTime dateTime;
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    /** 
     * Default constructor method.
     * Creates a food entry with a DateTime set to now.
     */
    public DatedFoodEntry(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat) {
        super(portionSize, name, calorie, carbohydrate, protein, fat);
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Convenience constructor for testing.
     * Also could be for adding food objects directly via DataBase.
     */
    public DatedFoodEntry(int portionSize, Food food) {
        super(portionSize, food);
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Convenience constructor for testing.
     * Could also be for adding backlogged entries with food objects directly via Database.
     */
    public DatedFoodEntry(int portionSize, Food food, LocalDateTime dateTime) {
        super(portionSize, food);
        assert (dateTime != null) : "Should not add null DateTime."
                + "Use other constructor to create with LocalDateTime.now() instead.";
        this.dateTime = dateTime;
    }

    /**
     * For adding backlogged entries.
     */
    public DatedFoodEntry(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat, LocalDateTime dateTime) {
        super(portionSize, name, calorie, carbohydrate, protein, fat);

        assert (dateTime != null) : "Should not add null DateTime."
                + "Use other constructor to create with LocalDateTime.now() instead.";
        assert (dateTime.isBefore(LocalDateTime.now())) : "Attempting to add entry that hasn't happened yet.";
        
        this.dateTime = dateTime;

    }

    /**
     * Getter method for LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * To String representation of a dated food entry that also contains a date.
     * Do not want to overwrite super method so that option to print with and without date is possible.
     * @return string rep of entry in the form of (entry details) [datetime]
     */
    public String toDatedString() {
        return String.format("%s [%s]", super.toString(), dateTime.format(DATE_TIME_FORMAT));
    }

    @Override
    public int compareTo(DatedFoodEntry other) {
        return dateTime.compareTo(other.getDateTime());
    }
    
}
