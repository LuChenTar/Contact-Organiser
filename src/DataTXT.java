/**
 * Created by luchen on 17/03/15.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataTXT {
    String firstName;
    String middleName;
    String lastName;
    String phoneNum;
    String gender;
    String email;
    String street1;
    String street2;
    String suburb;
    String state;
    String postalCode;
    String country;
    String birthday;
    String notes;

    static final String FILENAME = "contact.txt";

    public DataTXT(String firstName, String middleName, String lastName, String phoneNum, String gender, String email,
                   String street1, String street2, String suburb, String state,
                   String postalCode, String country, String birthday, String notes) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.email = email;
        this.street1 = street1;
        this.street2 = street2;
        this.suburb = suburb;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.birthday = birthday;
        this.notes = notes;
    }

    public DataTXT() {
    }

    public List<Person> load() {
        List<Person> listPerson = new ArrayList<Person>();
        try {
            String line;
            BufferedReader lr = new BufferedReader(new FileReader(FILENAME));
            while ((line = lr.readLine()) != null) {
                String[] dataStr = null;
                dataStr = line.split("\\|");
                //to prevent faulty data format
                if (dataStr.length == 14) {
                    Person p = new Person(dataStr[0], dataStr[1], dataStr[2], dataStr[3],
                            dataStr[4], dataStr[5], dataStr[6], dataStr[7], dataStr[8],
                            dataStr[9], dataStr[10], dataStr[11], dataStr[12], dataStr[13]);
                    listPerson.add(p);
                }
            }
            System.out.println("I am loading TXT");
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPerson;
    }

    public boolean save(String firstName, String middleName, String lastName, String phoneNum, String gender, String email,
                        String street1, String street2, String suburb, String state,
                        String postalCode, String country, String birthday, String notes) {
        FileWriter fw;
        try {
            fw = new FileWriter(FILENAME, true);
            BufferedWriter lw = new BufferedWriter(fw);
            //insert space to fill the unfilled fields to make sure load successfully
            lw.append(firstName + "| " + middleName + "|" + lastName + "|" + phoneNum + "| " + gender + "| " +
                    email + "| " + street1 + "| " + street2 + "| " + suburb + "| " + state + "| " +
                    postalCode + "| " + country + "| " + birthday + "| " + notes);
            //add a new line so that next contact info will no erase the previous one
            lw.newLine();
            lw.close();
            System.out.println("new info has been saved");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //delete file from original list
    public boolean delete(String firstName, String middleName, String lastName, String phoneNum) {
        boolean deleted = false;
        try {
            List<Person> dataLis = new ArrayList<Person>();
            dataLis = load();
            //delete from GUI
            for (Person p : dataLis) {
                if (p.firstName.equalsIgnoreCase(firstName) && p.middleName.equalsIgnoreCase(middleName)
                        && p.lastName.equalsIgnoreCase(lastName) && (p.phoneNum.equalsIgnoreCase(phoneNum))) {
                    dataLis.remove(p);
                    deleted = true;
                    System.out.println(firstName + "The record has been deleted successfully");
                    break;
                }
            }
            //if deleted successfully then delete from txt
            if (deleted) {
                updateTXT(dataLis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    //update txt file after deleting
    public void updateTXT(List<Person> dataLis) {
        FileWriter fw;
        try {
            fw = new FileWriter(FILENAME);
            BufferedWriter lw = new BufferedWriter(fw);
            for (Person p : dataLis) {
                lw.append(p.firstName + "|" + p.middleName + "|" + p.lastName + "|" + p.phoneNum + "|" + p.gender + "|" +
                        p.email + "|" + p.street1 + "|" + p.street2 + "|" + p.suburb + "|" + p.state + "|" +
                        p.postalCode + "|" + p.country + "|" + p.birthday + "|" + p.notes);
                //add a new line so that next contact info will no erase the previous one
                lw.newLine();
            }
            System.out.println("I am updating txt");
            lw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
