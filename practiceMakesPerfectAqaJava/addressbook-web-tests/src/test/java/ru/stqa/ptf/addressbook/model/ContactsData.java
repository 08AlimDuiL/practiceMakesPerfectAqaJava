package ru.stqa.ptf.addressbook.model;

public class ContactsData {
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String address;
    private final String home;
    private final String mobile;
    private final String work;
    private final String fax;
    private final String email;
    private final String birthDay;
    private final String birthMonth;
    private final String birthYear;
    private final String group;

    public ContactsData(
            String name,
            String middleName,
            String lastName,
            String nickName,
            String company,
            String address,
            String homePhoneNumber,
            String mobilePhoneNumber,
            String work,
            String fax,
            String email,
            String birthDay,
            String birthMonth,

            String birthYear,
            String group) {

        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.address = address;
        this.home = homePhoneNumber;
        this.mobile = mobilePhoneNumber;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhoneNumber() {
        return home;
    }

    public String getMobilePhoneNumber() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGroup() {
        return group;
    }
}
