/**
 * Created by luchen on 17/03/15.
 */
public class Person {
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

    public Person(String firstName, String middleName, String lastName, String phoneNum, String gender, String email,
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
}
