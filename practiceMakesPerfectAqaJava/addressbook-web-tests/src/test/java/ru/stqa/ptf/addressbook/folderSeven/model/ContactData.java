package ru.stqa.ptf.addressbook.folderSeven.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id;

    @Column(name = "firstname")
    @Expose
    private String firstName;

    @Column(name = "middlename")
    @Expose
    private String middleName;

    @Column(name = "lastname")
    @Expose
    private String lastName;

    @Column(name = "nickname")
    @Expose
    private String nickName;

    @Column(name = "company")
    @Expose
    private String company;

    @Column(name = "address")
    @Type(type = "text")
    @Expose
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    @Expose
    private String homePhoneNumber;

    @Column(name = "mobile")
    @Type(type = "text")
    @Expose
    private String mobilePhoneNumber;

    @Column(name = "work")
    @Type(type = "text")
    @Expose
    private String workPhoneNumber;

    @Column(name = "fax")
    @Type(type = "text")
    @Expose
    private String fax;

    @Column(name = "email")
    @Type(type = "text")
    @Expose
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    @Expose
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    @Expose
    private String email3;

    @Transient
    //@Column(name = "bday")
    @Expose
    private String birthDay;

    @Transient
    //@Column(name = "bmonth")
    @Expose
    private String birthMonth;

    @Transient
   //@Column(name = "byear")
    @Expose
    private String birthYear;

    @Transient
    @Expose
    private String group;

    @Transient
    @XStreamOmitField
    private String allPhones;

    @Transient
    @XStreamOmitField
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    @XStreamOmitField
    private String photo;

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

        this.photo = photo.getPath();  //Folder 7.3
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
        //Folder 7.3
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
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
}