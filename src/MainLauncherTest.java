import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainLauncherTest {
    NewContactFrame nf = new NewContactFrame();

    //empty input
    @Test
    //test case 1
    public void checkAllTest1() {
        nf.inputfirstName.setText("");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        assertFalse(nf.checkAll());
    }

    @Test
    //test case 2
    public void checkAllTest2() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("");
        nf.inputphoneNum.setText("777");
        assertFalse(nf.checkAll());
    }

    @Test
    //test case 3
    public void checkAllTest3() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("");
        assertFalse(nf.checkAll());
    }

    @Test
    //test case 123
    //check if it is true when user has essential labels
    public void checkAllTest123() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("444");
        assertTrue(nf.checkAll());
    }

    //non-numeric input
    @Test
    //test case 4
    public void checkAllTest4() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputphoneNum.setText("rr33sdfsd@@r");
        assertFalse(nf.checkAll());
    }

    @Test
    //test case 5
    public void checkAllTest5() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputPostalCode.setText("3r4b");
        assertFalse(nf.checkAll());
    }
    //end of non-numberic input

    //wrong formatting
    @Test
    //test case 6
    public void checkAllTest6() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputEmail.setText("abc.com@");
        assertFalse(nf.checkAll());
    }

    @Test
    //test case 7
    public void checkAllTest7() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputEmail.setText("abc.com");
        assertFalse(nf.checkAll());
    }


    @Test
    //test case 8
    //a. year is not 4 digits
    public void checkAllTest8a() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputBirthdayYear.setText("");
        nf.inputBirthdayMonth.setText("12");
        nf.inputBirthdayDay.setText("12");
        assertFalse(nf.checkAll());
    }

    //b. month is missing
    public void checkAllTest8b() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputBirthdayYear.setText("1999");
        nf.inputBirthdayMonth.setText("");
        nf.inputBirthdayDay.setText("12");
        assertFalse(nf.checkAll());
    }

    //c. day is missing
    public void checkAllTest8c() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputBirthdayYear.setText("1999");
        nf.inputBirthdayMonth.setText("12");
        nf.inputBirthdayDay.setText("");
        assertFalse(nf.checkAll());
    }


    @Test
    //test case 9
    //this part may hard to be tested on
    //since the month and day can both be very small
    //but combined by the validation test of rule of the natural date
    //the test will cover most cases
    public void checkAllTest9() {
        nf.inputfirstName.setText("Lu");
        nf.inputlastName.setText("Chen");
        nf.inputphoneNum.setText("777");
        nf.inputBirthdayYear.setText("02");
        nf.inputBirthdayMonth.setText("19");
        nf.inputBirthdayDay.setText("1999");
        assertFalse(nf.checkAll());
    }


    @Test
    //test case 10
    //a. the number of day should be any positive number no greater than 31
    public void checkAllTest10a() {
        String m = "03";
        String d = "03";
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(12) + 1;
            int ranDay = rand.nextInt(31) + 1;
            //month generator
            if (randomNum != 2 && randomNum != 4 && randomNum != 6 && randomNum < 9) {
                m = "0" + String.valueOf(randomNum);
            } else if (randomNum != 11 && randomNum > 9) {
                m = String.valueOf(randomNum);
            }
            //day generator
            if (ranDay < 32 && ranDay > 9) {
                d = String.valueOf(ranDay);
            } else {
                d = "0" + String.valueOf(ranDay);
            }
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText("2011");
            nf.inputBirthdayMonth.setText(m);
            nf.inputBirthdayDay.setText(d);
            assertTrue(nf.checkAll());
        }
    }


    @Test
    //test case 10
    //any number of the day is non-positive or two digits number greater than 31 is false
    public void checkAllTest10b() {
        String m = "03";
        String d = "03";
        for (int i = 0; i < 10000; i++) {
            //month generator
            Random rand = new Random();
            int randomNum = rand.nextInt(12) + 1;
            if (randomNum != 2 && randomNum != 4 && randomNum != 6 && randomNum < 9) {
                m = "0" + String.valueOf(randomNum);
            } else if (randomNum != 11 && randomNum > 9) {
                m = String.valueOf(randomNum);
            }

            //case: 0 or negative
            int ranDay = rand.nextInt(100) - 99;
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText("2000");
            nf.inputBirthdayMonth.setText(m);
            nf.inputBirthdayDay.setText(String.valueOf(ranDay));
            assertFalse(nf.checkAll());

            //case: two digits number no smaller than 32
            ranDay = rand.nextInt(68) + 32;
            d = String.valueOf(ranDay);
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText("2000");
            nf.inputBirthdayMonth.setText(m);
            nf.inputBirthdayDay.setText(d);
            assertFalse(nf.checkAll());
        }
    }

    @Test
    //test case 11
    //a. the number of day should be any positive number no greater than 30
    public void checkAllTest11a() {
        String m = "04";
        String d = "04";
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(12) + 1;
            int ranDay = rand.nextInt(30) + 1;
            //month generator
            if (randomNum == 4 || randomNum == 6 || randomNum == 9) {
                m = "0" + String.valueOf(randomNum);
            } else if (randomNum == 11) {
                m = String.valueOf(randomNum);
            }
            //day generator
            if (ranDay < 31 && ranDay > 9) {
                d = String.valueOf(ranDay);
            } else {
                d = "0" + String.valueOf(ranDay);
            }
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText("2000");
            nf.inputBirthdayMonth.setText(m);
            nf.inputBirthdayDay.setText(d);
            assertTrue(nf.checkAll());
        }
    }


    @Test
    //test case 11
    //b. any number of the day is non-positive or two digits number greater than 30 is false
    public void checkAllTest11b() {
        String m = "04";
        String d = "04";
        for (int i = 0; i < 10000; i++) {
            //month generator
            Random rand = new Random();
            int randomNum = rand.nextInt(12) + 1;
            if (randomNum == 4 || randomNum == 6 || randomNum == 9) {
                m = "0" + String.valueOf(randomNum);
            } else if (randomNum == 11) {
                m = String.valueOf(randomNum);
            }

            //case: 0 or negative
            int ranDay = rand.nextInt(100) - 99;
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText("2000");
            nf.inputBirthdayMonth.setText(m);
            nf.inputBirthdayDay.setText(String.valueOf(ranDay));
            assertFalse(nf.checkAll());

            //case: two digits number no smaller than 31
            ranDay = rand.nextInt(69) + 31;
            d = String.valueOf(ranDay);
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText("2000");
            nf.inputBirthdayMonth.setText(m);
            nf.inputBirthdayDay.setText(d);
            assertFalse(nf.checkAll());
        }
    }


    @Test
    //test case 12 - non-leap year
    //a. the number of day should be any positive number no greater than 29
    public void checkAllTest12a() {
        String y = "0003";
        String d = "04";
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(9999) + 1;
            int ranDay = rand.nextInt(28) + 1;
            //year generator
            if (!(randomNum % 400 == 0 || (randomNum % 4 == 0 && randomNum % 100 != 0))) {
                if (randomNum < 10) {
                    y = "000" + String.valueOf(randomNum);
                } else if (randomNum < 100) {
                    y = "00" + String.valueOf(randomNum);
                } else if (randomNum < 1000) {
                    y = "0" + String.valueOf(randomNum);
                } else {
                    y = String.valueOf(randomNum);
                }
            }

            //day generator
            if (ranDay < 29 && ranDay > 9) {
                d = String.valueOf(ranDay);
            } else {
                d = "0" + String.valueOf(ranDay);
            }

            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText(y);
            nf.inputBirthdayMonth.setText("02");
            nf.inputBirthdayDay.setText(d);
            assertTrue(nf.checkAll());
        }
    }

    @Test
    //test case 12b -non-leap year
    //b. any number of the day is non-positive or two digits number greater than 29 is false
    public void checkAllTest12b() {
        String y = "0003";
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(9999) + 1;
            //year generator
            if (!(randomNum % 400 == 0 || (randomNum % 4 == 0 && randomNum % 100 != 0))) {
                if (randomNum < 10) {
                    y = "000" + String.valueOf(randomNum);
                } else if (randomNum < 100) {
                    y = "00" + String.valueOf(randomNum);
                } else if (randomNum < 1000) {
                    y = "0" + String.valueOf(randomNum);
                } else {
                    y = String.valueOf(randomNum);
                }
            }

            //case: 0 or negative
            int ranDay = rand.nextInt(100) - 99;
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText(y);
            nf.inputBirthdayMonth.setText("02");
            nf.inputBirthdayDay.setText(String.valueOf(ranDay));
            assertFalse(nf.checkAll());

            //case: two digits number no smaller than 29
            ranDay = rand.nextInt(71) + 29;
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText(y);
            nf.inputBirthdayMonth.setText("02");
            nf.inputBirthdayDay.setText(String.valueOf(ranDay));
            assertFalse(nf.checkAll());
        }
    }

    @Test
    //test case 13 - leap year
    //a. the number of day should be any positive number no greater than 29
    public void checkAllTest13a() {
        String y = "0004";
        String d = "04";
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(9999) + 1;
            int ranDay = rand.nextInt(29) + 1;
            //year generator
            if (randomNum % 400 == 0 || (randomNum % 4 == 0 && randomNum % 100 != 0)) {
                if (randomNum < 10) {
                    y = "000" + String.valueOf(randomNum);
                } else if (randomNum < 100) {
                    y = "00" + String.valueOf(randomNum);
                } else if (randomNum < 1000) {
                    y = "0" + String.valueOf(randomNum);
                } else {
                    y = String.valueOf(randomNum);
                }
            }

            //day generator
            if (ranDay < 30 && ranDay > 9) {
                d = String.valueOf(ranDay);
            } else {
                d = "0" + String.valueOf(ranDay);
            }

            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText(y);
            nf.inputBirthdayMonth.setText("02");
            nf.inputBirthdayDay.setText(d);
            assertTrue(nf.checkAll());
        }
    }

    @Test
    //test case 13b - leap year
    //b. any number of the day is non-positive or two digits number greater than 29 is false
    public void checkAllTest13b() {
        String y = "0004";
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(9999) + 1;
            //year generator
            if (randomNum % 400 == 0 || (randomNum % 4 == 0 && randomNum % 100 != 0)) {
                if (randomNum < 10) {
                    y = "000" + String.valueOf(randomNum);
                } else if (randomNum < 100) {
                    y = "00" + String.valueOf(randomNum);
                } else if (randomNum < 1000) {
                    y = "0" + String.valueOf(randomNum);
                } else {
                    y = String.valueOf(randomNum);
                }
            }

            //case: 0 or negative
            int ranDay = rand.nextInt(100) - 99;
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText(y);
            nf.inputBirthdayMonth.setText("02");
            nf.inputBirthdayDay.setText(String.valueOf(ranDay));
            assertFalse(nf.checkAll());

            //case: two digits number no smaller than 30
            ranDay = rand.nextInt(70) + 30;
            nf.inputfirstName.setText("Lu");
            nf.inputlastName.setText("Chen");
            nf.inputphoneNum.setText("777");
            nf.inputBirthdayYear.setText(y);
            nf.inputBirthdayMonth.setText("02");
            nf.inputBirthdayDay.setText(String.valueOf(ranDay));
            assertFalse(nf.checkAll());
        }
    }
}