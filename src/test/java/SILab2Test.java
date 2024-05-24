import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void everyBranch() {

        //exception case
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 500));
        assertEquals("allItems list can't be null!", exception.getMessage());

        List<Item> itemList1 = new ArrayList<>();
        itemList1.add(new Item(null, null, 150, 0.1F));


        //Barcode exception
        RuntimeException exception1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemList1, 500));
        assertEquals("No barcode!", exception1.getMessage());
//        assertFalse(SILab2.checkCart(itemList1,500));

        List<Item> itemList2 = new ArrayList<>();
        itemList2.add(new Item("Adidas Ultraboost 21", "0345678901235", 600, 0.1F));
        itemList2.add(new Item("New Balance 574 Classic", "4567890123457", 3000, 0.0F));
        itemList2.add(new Item("Puma RS-X3 Puzzle", "3456@89012346", 300, 0.05F));


        //First two items for all other branches and with last item exception: "Invalid character in item barcode!"
        RuntimeException exception2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemList2, 500));
        assertEquals("Invalid character in item barcode!", exception2.getMessage());
//        assertFalse(SILab2.checkCart(itemList2, 500));
    }


    @Test
    void multipleConditions() {
        //if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
        // T && T && T => TTT
        // T && T && F => TTF
        // T && F && X => TFX
        // F && X && X => FXX

        // When all condition are true
        Item item1 = new Item("Adidas Ultraboost 21", "0345678901235", 600, 0.15F);
        assertTrue(item1.getPrice() > 300 && item1.getDiscount() > 0 && item1.getBarcode().charAt(0) == '0');

        // When the barcode condition is false
        Item item2 = new Item("Nike Air Max 270", "1234567890124", 450, 0.1F);
        assertFalse(item2.getPrice() > 300 && item2.getDiscount() > 0 && item2.getBarcode().charAt(0) == '0');

        // When discount condition is false
        Item item3 = new Item("Puma RS-X3 Puzzle", "3456789012346", 1000, 0.0F);
        assertFalse(item3.getPrice() > 300 && item3.getDiscount() > 0 && item3.getBarcode().charAt(0) == '0');

        // When price(first) condition is false and next conditions doesn't matter
        Item item4 = new Item("New Balance 574 Classic", "4567890123457", 200, 0.25F);
        assertFalse(item4.getPrice() > 300 && item4.getDiscount() > 0 && item4.getBarcode().charAt(0) == '0');


    }
}
