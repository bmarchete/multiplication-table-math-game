/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author binho
 */
public class RandomUtil {
    
    public static int [] nextArrayInt(int size, int bound){
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();

        while (numbers.size() != size) {

            int r = random.nextInt(bound);

            if (numbers.stream().filter((number) -> (r == number)).count() == 0) {
                numbers.add(r);
            }

        }

        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
    
}
