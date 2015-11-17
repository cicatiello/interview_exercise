package org.cicatiello.interview_exercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class MainTest {

    @Test
    public void testInput1() {
    	String cartLineInput1 = "1 book at 12.49";
    	String cartLineInput2 = "1 music CD at 14.99";
    	String cartLineInput3 = "1 chocolate bar at 0.85";

    	String assertedOutput = "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83";
    	System.out.println(assertedOutput);
    }

}
