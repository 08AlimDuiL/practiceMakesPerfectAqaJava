package ru.stqa.ptf.addressbook.folderOneToSix.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id;

    @Expose
    private String firstName;
    @Expose
    private String middleName;
    @Expose
    private String lastName;
    @Expose
    private String nickName;
    @Expose
    private String company;
    @Expose
    private String address;
    @Expose
    private String homePhoneNumber;
    @Expose
    private String mobilePhoneNumber;
    @Expose
    private String workPhoneNumber;
    @Expose
    private String fax;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String birthDay;
    @Expose
    private String birthMonth;
    @Expose
    private String birthYear;
    @Expose
    private String group;

    @XStreamOmitField
    private String allPhones;
    @XStreamOmitField
    private String allEmails;
    @XStreamOmitField
    private File photo;

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withBirthDay(String birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public ContactData withBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
        return this;
    }

    public ContactData withBirthYear(String birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {

        this.photo = photo;
        return this;
    }

    public String getFirstName() {

        return firstName;
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

        return homePhoneNumber;
    }

    public String getMobilePhoneNumber() {

        return mobilePhoneNumber;
    }

    public String getWorkPhoneNumber() {

        return workPhoneNumber;
    }

    public String getFax() {

        return fax;
    }

    public String getEmail() {

        return email;
    }

    public String getEmail2() {

        return email2;
    }

    public String getEmail3() {

        return email3;
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

    public int getId() {

        return id;
    }

    public String getAllEmails() {

        return allEmails;
    }

    public String getAllPhones() {

        return allPhones;
    }

    public File getPhoto() {

        return photo;
    }

    public ContactData() {
    }

    public ContactData(
            String firstName,
            String middleName,
            String lastName,
            String nickName,
            String company,
            String address,
            String homePhoneNumber,
            String mobilePhoneNumber,
            String workPhoneNumber,
            String fax,
            String email,
            String birthDay,
            String birthMonth,
            String birthYear,
            String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.address = address;
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.workPhoneNumber = workPhoneNumber;
        this.fax = fax;
        this.email = email;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.group = group;
    }

    public ContactData(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = Integer.MAX_VALUE;
        this.middleName = "";
        this.nickName = "";
        this.company = "";
        this.address = "";
        this.homePhoneNumber = "";
        this.mobilePhoneNumber = "";
        this.workPhoneNumber = "";
        this.fax = "";
        this.email = "";
        this.birthDay = "";
        this.birthMonth = "";
        this.birthYear = "";
        this.group = "";
    }

    public ContactData(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = "";
        this.nickName = "";
        this.company = "";
        this.address = "";
        this.homePhoneNumber = "";
        this.mobilePhoneNumber = "";
        this.workPhoneNumber = "";
        this.fax = "";
        this.email = "";
        this.birthDay = "";
        this.birthMonth = "";
        this.birthYear = "";
        this.group = "";
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ContactData that = (ContactData) o;
//        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(name, lastName);
//    }
}