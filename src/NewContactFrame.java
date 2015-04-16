/**
 * Created by luchen on 15/03/15.
 *
 *setup new contact frame
 **/

//problem: how to not turn off the main frame
//but when i press the save button in the new contact frame
//still get a update

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewContactFrame extends JFrame {
    //variable declaration
    int height = 305;
    int width = 450;

    DataTXT contact = new DataTXT();

    private JFrame jFrame = new JFrame();
    private JPanel jPanel = new JPanel();
    protected JTextField inputfirstName;
    private JTextField inputmiddleName;
    protected JTextField inputlastName;
    protected JTextField inputphoneNum;
    protected JTextField inputEmail;
    private JTextField inputstrLine1;
    private JTextField inputstrLine2;
    private JTextField inputsuburb;
    private JTextField inputstate;
    protected JTextField inputPostalCode;
    private JTextField inputCountry;
    protected JTextField inputBirthdayDay;
    protected JTextField inputBirthdayMonth;
    protected JTextField inputBirthdayYear;
    private JTextField inputNotes;
    private String GENDER;
    private JButton saveButton;
    private JRadioButton rdbtnFemale;
    private JRadioButton rdbtnMale;
    private ButtonGroup group = new ButtonGroup();
    private String errMessage = "You have input invaild contact information";
    private String err[] = new String[11];
    private String savedFirstName;
    private String savedMiddleName;
    private String savedLastName;
    private String savedPhoneNum;
    private boolean isLeapYear = true;
    private boolean isLong = true;//long mouth means it has 31 days
    private boolean isFeb = true;
    private boolean isBirthdayY = true; // check if the birthday is filled with 8 digits
    private boolean isBirthdayM = true; // check if the birthday is filled with 8 digits
    private boolean isBirthdayD = true; // check if the birthday is filled with 8 digits
    //end of variable declaration

    public NewContactFrame() {
        jFrame.setTitle("Organiser Plus - Contact");
        jFrame.setSize(width, height);
        setUpContactPanel();
        jFrame.setVisible(true);
    }

    public void setUpContactPanel() {
        //set up the background
        jPanel.setBackground(new Color(225, 227, 230));
        jPanel.setForeground(Color.LIGHT_GRAY);
        //end of setting up the background

        SpringLayout currentLayout = new SpringLayout();
        jPanel.setLayout(currentLayout);

        jFrame.add(jPanel);

        //create JTextfield and JLabel to add/edit the contact
        // First Name - Creating a label
        JLabel firstName = new JLabel("* First Name");
        firstName.setFont(new Font("Courier", Font.PLAIN, 12));
        jPanel.add(firstName);

        // First Name - Creating a text-field
        inputfirstName = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.NORTH, firstName, 6, SpringLayout.NORTH, inputfirstName);
        currentLayout.putConstraint(SpringLayout.EAST, firstName, 0, SpringLayout.WEST, inputfirstName);
        currentLayout.putConstraint(SpringLayout.NORTH, inputfirstName, 1, SpringLayout.NORTH, jPanel);
        currentLayout.putConstraint(SpringLayout.WEST, inputfirstName, 85, SpringLayout.WEST, jPanel);
        jPanel.add(inputfirstName);

        // Middle Name - Creating a label
        JLabel middleName = new JLabel("Middle Name");
        middleName.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.NORTH, middleName, 0, SpringLayout.NORTH, firstName);
        jPanel.add(middleName);

        // Middle Name - Creating a text-field
        inputmiddleName = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.NORTH, inputmiddleName, 1, SpringLayout.NORTH, inputfirstName);
        jPanel.add(inputmiddleName);

        // Last Name - Creating a label
        JLabel lastName = new JLabel("* Last Name");
        lastName.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.WEST, lastName, 0, SpringLayout.WEST, firstName);
        jPanel.add(lastName);

        // Last Name - Creating a text-field
        inputlastName = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.SOUTH, lastName, 0, SpringLayout.SOUTH, inputlastName);
        currentLayout.putConstraint(SpringLayout.NORTH, inputlastName, 0, SpringLayout.SOUTH, inputfirstName);
        currentLayout.putConstraint(SpringLayout.WEST, inputlastName, 0, SpringLayout.WEST, inputfirstName);
        jPanel.add(inputlastName);

        // Phone Number - Creating a label
        JLabel phoneNum = new JLabel("* Phone Number");
        phoneNum.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.NORTH, phoneNum, 12, SpringLayout.SOUTH, middleName);
        currentLayout.putConstraint(SpringLayout.EAST, middleName, 0, SpringLayout.EAST, phoneNum);
        currentLayout.putConstraint(SpringLayout.EAST, phoneNum, -171, SpringLayout.EAST, jPanel);
        jPanel.add(phoneNum);

        // Phone Number - Creating a text-field
        inputphoneNum = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.NORTH, inputphoneNum, 29, SpringLayout.NORTH, jPanel);
        currentLayout.putConstraint(SpringLayout.EAST, inputmiddleName, 0, SpringLayout.EAST, inputphoneNum);
        currentLayout.putConstraint(SpringLayout.WEST, inputphoneNum, 293, SpringLayout.WEST, jPanel);
        jPanel.add(inputphoneNum);

        // Gender - Creating a label
        JLabel gender = new JLabel("  Gender");
        gender.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.NORTH, gender, 18, SpringLayout.SOUTH, lastName);
        currentLayout.putConstraint(SpringLayout.WEST, gender, 0, SpringLayout.WEST, firstName);
        jPanel.add(gender);

        // Gender - Creating radio-button for M
        rdbtnMale = new JRadioButton("Male", false);
        rdbtnMale.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        currentLayout.putConstraint(SpringLayout.NORTH, rdbtnMale, -4, SpringLayout.NORTH, gender);
        jPanel.add(rdbtnMale);

        // Gender - Creating radio-button for F
        rdbtnFemale = new JRadioButton("Female", false);
        rdbtnFemale.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        currentLayout.putConstraint(SpringLayout.WEST, rdbtnFemale, 153, SpringLayout.WEST, jPanel);
        currentLayout.putConstraint(SpringLayout.EAST, rdbtnMale, -12, SpringLayout.WEST, rdbtnFemale);
        currentLayout.putConstraint(SpringLayout.NORTH, rdbtnFemale, -4, SpringLayout.NORTH, gender);
        jPanel.add(rdbtnFemale);


        group.add(rdbtnMale);
        group.add(rdbtnFemale);

        // Email - Creating a label
        JLabel email = new JLabel("  Email");
        email.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.NORTH, email, 0, SpringLayout.NORTH, gender);
        currentLayout.putConstraint(SpringLayout.WEST, email, 6, SpringLayout.EAST, rdbtnFemale);
        jPanel.add(email);

        // Email - Creating a text-field
        inputEmail = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.NORTH, inputEmail, -6, SpringLayout.NORTH, gender);
        currentLayout.putConstraint(SpringLayout.EAST, inputEmail, 0, SpringLayout.EAST, inputmiddleName);
        jPanel.add(inputEmail);


        // Street Line 1 - creating a label
        JLabel streetLine1 = new JLabel("  Street 1");
        streetLine1.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.WEST, streetLine1, 0, SpringLayout.WEST, firstName);
        jPanel.add(streetLine1);

        // Street Line 1 - creating a text-field
        inputstrLine1 = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.NORTH, inputstrLine1, -6, SpringLayout.NORTH, streetLine1);
        currentLayout.putConstraint(SpringLayout.WEST, inputstrLine1, 6, SpringLayout.EAST, streetLine1);
        jPanel.add(inputstrLine1);
        inputstrLine1.setColumns(11);

        // Address Line 2 - creating a label
        JLabel streetline2 = new JLabel("  Street 2");
        streetline2.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.EAST, streetline2, 0, SpringLayout.EAST, middleName);
        jPanel.add(streetline2);

        // Address Line 2 - creating a text-field
        inputstrLine2 = new JTextField(5);
        currentLayout.putConstraint(SpringLayout.EAST, streetline2, -14, SpringLayout.WEST, inputstrLine2);
        currentLayout.putConstraint(SpringLayout.NORTH, inputstrLine2, 6, SpringLayout.SOUTH, inputEmail);
        currentLayout.putConstraint(SpringLayout.WEST, inputstrLine2, 0, SpringLayout.WEST, inputmiddleName);
        jPanel.add(inputstrLine2);
        inputstrLine2.setColumns(10);

        //Suburb - creating a label
        JLabel suburb = new JLabel("  Suburb");
        suburb.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.SOUTH, streetLine1, -18, SpringLayout.NORTH, suburb);
        currentLayout.putConstraint(SpringLayout.WEST, suburb, 0, SpringLayout.WEST, firstName);
        jPanel.add(suburb);

        // City - creating a text-field
        inputsuburb = new JTextField(9);
        currentLayout.putConstraint(SpringLayout.NORTH, suburb, 6, SpringLayout.NORTH, inputsuburb);
        currentLayout.putConstraint(SpringLayout.NORTH, inputsuburb, 138, SpringLayout.NORTH, jPanel);
        jPanel.add(inputsuburb);

        // State - creating a label
        JLabel state = new JLabel("  State");
        state.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.NORTH, state, 144, SpringLayout.NORTH, jPanel);
        currentLayout.putConstraint(SpringLayout.EAST, inputsuburb, -10, SpringLayout.WEST, state);
        currentLayout.putConstraint(SpringLayout.SOUTH, streetline2, -18, SpringLayout.NORTH, state);
        currentLayout.putConstraint(SpringLayout.WEST, state, 0, SpringLayout.WEST, email);
        jPanel.add(state);

        // State - creating a text-field
        inputstate = new JTextField(10);
        currentLayout.putConstraint(SpringLayout.WEST, inputstate, 293, SpringLayout.WEST, jPanel);
        jPanel.add(inputstate);

        // Zip code - creating a label
        JLabel postalCode = new JLabel("  Postal Code");
        postalCode.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.WEST, postalCode, 0, SpringLayout.WEST, firstName);
        jPanel.add(postalCode);

        // Zip code - creating a text-field
        inputPostalCode = new JTextField(9);
        currentLayout.putConstraint(SpringLayout.NORTH, inputPostalCode, 10, SpringLayout.SOUTH, inputsuburb);
        currentLayout.putConstraint(SpringLayout.NORTH, postalCode, 6, SpringLayout.NORTH, inputPostalCode);
        jPanel.add(inputPostalCode);

        // Country - creating a label
        JLabel country = new JLabel(" Country");
        country.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.NORTH, country, 16, SpringLayout.SOUTH, inputstate);
        currentLayout.putConstraint(SpringLayout.WEST, country, 235, SpringLayout.WEST, jPanel);
        jPanel.add(country);

        //notes  - creating a text-field
        inputCountry = new JTextField(10);
        currentLayout.putConstraint(SpringLayout.EAST, inputPostalCode, -68, SpringLayout.WEST, inputCountry);
        currentLayout.putConstraint(SpringLayout.NORTH, inputCountry, 176, SpringLayout.NORTH, jPanel);
        currentLayout.putConstraint(SpringLayout.EAST, inputCountry, -23, SpringLayout.EAST, jPanel);
        currentLayout.putConstraint(SpringLayout.SOUTH, inputstate, -10, SpringLayout.NORTH, inputCountry);
        jPanel.add(inputCountry);


        //Birthday - creating a label
        JLabel birthday = new JLabel("Birthday(dd/mm/yyyy)");
        birthday.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.EAST, birthday, 0, SpringLayout.EAST, inputfirstName);
        jPanel.add(birthday);


        //Birthday day - creating a text-field
        inputBirthdayDay = new JTextField(2);
        currentLayout.putConstraint(SpringLayout.NORTH, inputBirthdayDay, -6, SpringLayout.NORTH, birthday);
        currentLayout.putConstraint(SpringLayout.WEST, inputBirthdayDay, 6, SpringLayout.EAST, birthday);
        jPanel.add(inputBirthdayDay);

        //Birthday month - creating a text-field
        inputBirthdayMonth = new JTextField(2);
        currentLayout.putConstraint(SpringLayout.NORTH, inputBirthdayMonth, -6, SpringLayout.NORTH, birthday);
        currentLayout.putConstraint(SpringLayout.WEST, inputBirthdayMonth, 0, SpringLayout.WEST, email);
        jPanel.add(inputBirthdayMonth);

        //Birthday year - creating a text-field
        inputBirthdayYear = new JTextField(4);
        currentLayout.putConstraint(SpringLayout.NORTH, inputBirthdayYear, -6, SpringLayout.NORTH, birthday);
        inputBirthdayYear.setColumns(4);
        jPanel.add(inputBirthdayYear);

        // slash1 - creating a label
        JLabel slash1 = new JLabel("/");
        slash1.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.EAST, slash1, -225, SpringLayout.EAST, jPanel);
        currentLayout.putConstraint(SpringLayout.NORTH, slash1, 18, SpringLayout.SOUTH, inputPostalCode);
        jPanel.add(slash1);

        // slash2 - creating a label
        JLabel slash2 = new JLabel("/");
        slash2.setFont(new Font("Courier", Font.PLAIN, 13));
        currentLayout.putConstraint(SpringLayout.WEST, inputBirthdayYear, 17, SpringLayout.EAST, slash2);
        currentLayout.putConstraint(SpringLayout.EAST, slash2, -155, SpringLayout.EAST, jPanel);
        currentLayout.putConstraint(SpringLayout.NORTH, slash2, 18, SpringLayout.SOUTH, inputCountry);
        jPanel.add(slash2);

        // Notes - creating a label
        JLabel notes = new JLabel(" Notes");
        notes.setFont(new Font("Courier", Font.PLAIN, 12));
        currentLayout.putConstraint(SpringLayout.WEST, notes, 0, SpringLayout.WEST, firstName);
        jPanel.add(notes);

        //notes  - creating a text-field
        inputNotes = new JTextField(13);
        currentLayout.putConstraint(SpringLayout.NORTH, inputNotes, 250, SpringLayout.NORTH, jPanel);
        currentLayout.putConstraint(SpringLayout.SOUTH, birthday, -12, SpringLayout.NORTH, inputNotes);
        currentLayout.putConstraint(SpringLayout.NORTH, notes, 6, SpringLayout.NORTH, inputNotes);
        jPanel.add(inputNotes);


        // Save - Creating a button
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Courier", Font.PLAIN, 12));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        currentLayout.putConstraint(SpringLayout.EAST, inputNotes, -47, SpringLayout.WEST, saveButton);
        saveButton.setForeground(Color.RED);
        currentLayout.putConstraint(SpringLayout.SOUTH, saveButton, 0, SpringLayout.SOUTH, jPanel);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set up the cursor to waiting state
                try {
                    boolean isEric = false;
                    //unable to enter new contact named "Eric"
                    if(inputfirstName.getText().equalsIgnoreCase("Eric")) {
                        JFrame pop = new JFrame("Error!");
                        isEric = true;
                        pop.setVisible(true);
                        pop.setSize(400, 250);
                        JPanel jPanelpop = new JPanel();
                        JLabel errmessage = new JLabel("You can not enter Eric!");
                        errmessage.setFont(new Font("Courier", Font.PLAIN, 13));
                        JButton reenter = new JButton("Re-enter");
                        reenter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                clearFields();
                                jFrame.setVisible(true);
                                pop.dispose();


                            }
                        });
                        jPanelpop.add(errmessage);
                        jPanelpop.add(reenter);
                        pop.setContentPane(jPanelpop);
                    }
                    // end of unable Eric
                    saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    if (rdbtnMale.isSelected()) {
                        GENDER = "male";
                    } else if (rdbtnFemale.isSelected()) {
                        GENDER = "female";
                    } else {
                        GENDER = "unknown";
                    }
                    if (checkAll()) {


                        if(!isEric) {
                            System.out.println("Prepare to search existing: " + savedFirstName);
                            //if user is editting the info
                            //delete existed info
                            //and re-add it into the file
                            searchExistedInfo(savedFirstName,
                                    savedMiddleName,
                                    savedLastName,
                                    savedPhoneNum);
                            //if user is adding a new info
                            //directly adding it into the file
                            contact.save(inputfirstName.getText().trim(),
                                    inputmiddleName.getText().trim(),
                                    inputlastName.getText().trim(),
                                    inputphoneNum.getText().trim(),
                                    GENDER, inputEmail.getText().trim(),
                                    inputstrLine1.getText().trim(),
                                    inputstrLine2.getText().trim(),
                                    inputsuburb.getText().trim(),
                                    inputstate.getText().trim(),
                                    inputPostalCode.getText().trim(),
                                    inputCountry.getText().trim(),
                                    (inputBirthdayDay.getText().toString()
                                            + inputBirthdayMonth.getText().toString()
                                            + inputBirthdayYear.getText().toString()),
                                    inputNotes.getText().trim());
                            jFrame.dispose();
                        }
                    } else {
                        JFrame popup = new JFrame("Error!");
                        popup.setVisible(true);
                        popup.setSize(400, 250);
                        JPanel jPanelpopup = new JPanel();
                        JLabel errormessage = new JLabel(errMessage);
                        errormessage.setFont(new Font("Courier", Font.PLAIN, 13));
                        JButton reenter = new JButton("Re-enter");
                        reenter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
//                                clearFields();
                                popup.dispose();
                            }
                        });
                        for (int i = 0; i < 11; i++) {
                            err[i] = null;
                        }
                        jPanelpopup.add(errormessage);
                        jPanelpopup.add(reenter);
                        popup.setContentPane(jPanelpopup);
                    }
                } finally {
                    saveButton.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
        jPanel.add(saveButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Courier", Font.PLAIN, 12));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.setForeground(Color.GRAY);
        currentLayout.putConstraint(SpringLayout.EAST, saveButton, -17, SpringLayout.WEST, clearButton);
        currentLayout.putConstraint(SpringLayout.NORTH, clearButton, 0, SpringLayout.NORTH, saveButton);
        currentLayout.putConstraint(SpringLayout.EAST, clearButton, -10, SpringLayout.EAST, jPanel);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        jPanel.add(clearButton);
    }

    //  populate the selected information to add/edit interface
//    space is ignored
    public void showSelectedDetails(Person p) {
        inputfirstName.setText(p.firstName.trim());
        inputmiddleName.setText(p.middleName.trim());
        inputlastName.setText(p.lastName.trim());
        inputphoneNum.setText(String.valueOf(p.phoneNum).trim());
        inputEmail.setText(p.email.trim());
        inputstrLine1.setText(p.street1.trim());
        inputstrLine2.setText(p.street2.trim());
        inputsuburb.setText(p.suburb.trim());
        inputstate.setText(p.state.trim());
        inputPostalCode.setText(p.postalCode.trim());
        inputCountry.setText(p.country.trim());
        if (!p.birthday.trim().isEmpty()) {
            inputBirthdayDay.setText(p.birthday.trim().substring(0, 2));
            inputBirthdayMonth.setText((p.birthday).trim().substring(2, 4));
            inputBirthdayYear.setText((p.birthday).trim().substring(4));
        }
        inputNotes.setText(p.notes.trim());
        if (p.gender.toString().trim().equalsIgnoreCase("male")) {
            rdbtnMale.setSelected(true);
        } else if (p.gender.toString().trim().equalsIgnoreCase("female")) {
            rdbtnFemale.setSelected(true);
        }
        System.out.println("details showed");
        //save existed info to compare
        savedFirstName = p.firstName;
        savedMiddleName = p.middleName;
        savedLastName = p.lastName;
        savedPhoneNum = p.phoneNum;
        System.out.println("info is saved to search existing: " + savedFirstName);
    }

    //  search for existed contacted info, if exists, deleted old record
    private void searchExistedInfo(String firstName, String middleName, String lastName, String phoneNum) {
        System.out.println("I am searching" + firstName);
        boolean isDuplicated = false;
        try {
            List<Person> dataLis = contact.load();
            for (Person p : dataLis) {
                if (p.firstName.equalsIgnoreCase(firstName) && p.middleName.equalsIgnoreCase(middleName)
                        && p.lastName.equalsIgnoreCase(lastName) && (p.phoneNum.equalsIgnoreCase(phoneNum))) {
                    isDuplicated = true;
                    System.out.println("The information" + firstName + " is duplicated!");
                    break;
                }
            }
            //delete from txt
            if (isDuplicated) {
                contact.delete(firstName, middleName, lastName, phoneNum);
                System.out.println("Duplicated info" + firstName + " has been deleted from TXT, ready to save");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  clear all the field
//  1. for user clearing
//  2. user faulty input but never used since it may bother user more when all the data is erased
    private void clearFields() {
        inputfirstName.setText("");
        inputmiddleName.setText("");
        inputlastName.setText("");
        inputphoneNum.setText("");
        inputEmail.setText("");
        inputstrLine1.setText("");
        inputstrLine2.setText("");
        inputsuburb.setText("");
        inputstate.setText("");
        inputPostalCode.setText("");
        inputCountry.setText("");
        inputBirthdayDay.setText("");
        inputBirthdayMonth.setText("");
        inputBirthdayYear.setText("");
        inputNotes.setText("");
        group.clearSelection();

    }

    //    check validation for each input
    protected boolean checkAll() {
        checkFirstName();
        checkLastName();
        checkphoneNo();
        checkEmail();
        checkPostalCode();
        checkBDay();
        checkBMonth();
        checkByear();

        //if any birthday info is not input then at least one of them are false
        if (!(isBirthdayY && isBirthdayM && isBirthdayD)) {
            //if all three of them are false then it is not the case
            if (!(isBirthdayY == false && isBirthdayM == false && isBirthdayD == false)) {
                err[10] = "Please enter standard format dd/mm/yyyy!";
                //reset the boolean value
                isBirthdayY = true;
                isBirthdayM = true;
                isBirthdayD = true;
                isLeapYear = true;
                isLong = true;
                isFeb = true;
                //end of reset
                return false;
            }
        }

        //form the error message

        errMessage = "<html>";
        //print all useful error message to user
        for (int i = 0; i < 11; i++) {
            if (err[i] != null) {
                errMessage = errMessage + err[i] + "<br>";
            }
        }
        errMessage = errMessage + "</html>";
        return checkFirstName() && checkLastName()
                && checkphoneNo() && checkEmail() && checkPostalCode()
                && checkBDay() && checkBMonth() && checkByear();
    }

    private boolean checkFirstName() {
        if (inputfirstName.getText().equalsIgnoreCase("")) {
            err[0] = "Please enter first name!";
            return false;
        }
        return true;
    }

    private boolean checkLastName() {
        if (inputlastName.getText().equalsIgnoreCase("")) {
            err[1] = "Please enter last name!";
            return false;
        }
        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    //check if it only contains int
    private boolean checkphoneNo() {
        if (inputphoneNum.getText().isEmpty()) {
            err[3] = "Please enter phone numbers!";
            return false;
        }
        if (!inputphoneNum.getText().equalsIgnoreCase("")) {
            if (!isInteger(inputphoneNum.getText())) {
                err[3] = "Please enter valid phone numbers!";
                return false;
            }
        }
        return true;
    }

    private boolean checkEmail() {
        if (!inputEmail.getText().equalsIgnoreCase("")) {
            //email pattern matching
            String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(regexEmail);
            Matcher matcher = pattern.matcher(inputEmail.getText().trim());

            if (!matcher.matches()) {
                err[4] = "Please enter as 123@abc.com";
                return false;
            }
        }
        return true;
    }

    private boolean checkPostalCode() {
        if (!inputPostalCode.getText().equalsIgnoreCase("")) {
            if (!isInteger(inputPostalCode.getText())) {
                err[9] = "Please enter valid postal code!";
                return false;
            }
        } else {
        }
        return true;//user does not input the postal code
    }

    private boolean checkByear() {
        if (!inputBirthdayYear.getText().equalsIgnoreCase("")) {
            if (isInteger(inputBirthdayYear.getText())) {
                if (inputBirthdayYear.getText().length() == 4) {
                    if (Integer.parseInt(inputBirthdayYear.getText().toString()) > 0) {
                        //decide if the year is a leap yead
                        if ((Integer.parseInt(inputBirthdayYear.getText().toString()) % 400 == 0)
                                || ((Integer.parseInt(inputBirthdayYear.getText().toString()) % 4 == 0)
                                && (Integer.parseInt(inputBirthdayYear.getText().toString()) % 100 != 0))) {
                            isLeapYear = true;
                        } else {
                            isLeapYear = false;
                            err[10] = "Please enter valid birthday number!";
                        }
                        //end of leap year
                        return true;
                    } else {
                        err[10] = "Please enter valid birthday number!";
                        return false;
                    }
                    //user does not input 4 digits
                } else {
                    err[10] = "Please enter standard format dd/mm/yyyy!";
                    return false;
                }
                //user does not input numbers
            } else {
                err[10] = "Please enter valid birthday number!";
                return false;
            }
        }
        isBirthdayY = false;
        return true;//user does not input the birthday
    }

    private boolean checkBMonth() {
        if (!inputBirthdayMonth.getText().equalsIgnoreCase("")) {
            if (isInteger(inputBirthdayMonth.getText())) {
                if (inputBirthdayMonth.getText().length() == 2) {
                    if (Integer.parseInt(inputBirthdayMonth.getText().toString()) < 13 &&
                            Integer.parseInt(inputBirthdayMonth.getText().toString()) > 0) {
                        //decide if the mouth is Feb
                        if (Integer.parseInt(inputBirthdayMonth.getText().toString()) == 2) {
                            isFeb = true;
                            isLong = false;
                            return true;
                            //decide if the mouth has 31 days
                        } else if ((Integer.parseInt(inputBirthdayMonth.getText().toString()) % 2 == 0 &&
                                (Integer.parseInt(inputBirthdayMonth.getText().toString()) > 7)) ||
                                (Integer.parseInt(inputBirthdayMonth.getText().toString()) % 2 != 0 &&
                                        (Integer.parseInt(inputBirthdayMonth.getText().toString()) < 8))) {

                            isLong = true;
                            isFeb = false;
                            return true;
                        } else {
                            isLong = false;
                            isFeb = false;
                            return true;
                        }
                        //end of determination
                    } else {
                        //user input is out of range
                        err[10] = "Please enter valid birthday number!";
                        return false;
                    }
                    //user does not input 2 digits
                } else {
                    err[10] = "Please enter standard format dd/mm/yyyy!";
                    return false;
                }
                //user does not input numbers
            } else {
                err[10] = "Please enter valid birthday number!";
                return false;
            }
        }
        isBirthdayM = false;
        return true;//user does not input the birthday
    }

    private boolean checkBDay() {
        if (!inputBirthdayDay.getText().equalsIgnoreCase("")) {
            if (isInteger(inputBirthdayDay.getText())) {
                if (inputBirthdayDay.getText().length() == 2) {
                    //negative number
                    if (Integer.parseInt(inputBirthdayDay.getText().toString()) < 1) {
                        err[10] = "Please enter valid birthday number!";
                        return false;
                    }
                    if (isLong) {//user should input 31 days for a long month
                        if (Integer.parseInt(inputBirthdayDay.getText().toString()) < 32) {
                            return true;
                        } else {
                            err[10] = "Please enter valid birthday number!";
                            return false;
                        }
                    } else if (isFeb && isLeapYear) {//if user input a feb on leap year, it should be 29 days
                        if (Integer.parseInt(inputBirthdayDay.getText().toString()) < 30) {
                            return true;
                        } else {
                            err[10] = "Please enter valid birthday number!";
                            return false;
                        }
                    } else if (isFeb && (!isLeapYear)) {
                        if (Integer.parseInt(inputBirthdayDay.getText().toString()) < 29) {
                            return true;
                        } else {
                            err[10] = "Please enter valid birthday number!";
                            return false;
                        }
                    } else {
                        if (Integer.parseInt(inputBirthdayDay.getText().toString()) < 31) {
                            return true;
                        } else {
                            err[10] = "Please enter valid birthday number!";
                            return false;
                        }
                    }
                    //user does not input 2 digits
                } else {
                    err[10] = "Please enter standard format dd/mm/yyyy!";
                    return false;
                }
                //user does not input numbers
            } else {
                err[10] = "Please enter valid birthday number!";
                return false;
            }
        }
        isBirthdayD = false;
        return true;//user does not input birthday
    }

}
