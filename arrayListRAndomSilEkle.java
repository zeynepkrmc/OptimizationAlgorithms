import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // a, b, c, d elemanlarından oluşan bir ArrayList oluştur
        ArrayList<Character> array = new ArrayList<>();
        array.add('a');
        array.add('b');
        array.add('c');
        array.add('d');

        // Array eleman sayısı kadar rastgele bir sayı üret
        Random random = new Random();
        int randomIndex = random.nextInt(array.size());

        // Rastgele seçilen index'teki elemanı sil
        char removedElement = array.remove(randomIndex);

        // Silinen elemanı array'in sonuna ekle
        array.add(removedElement);

        // Sonuçları yazdır
        System.out.println("Oluşturulan array: " + array);
        System.out.println("Silinen ve sona eklenen eleman: " + removedElement);
    }
}
