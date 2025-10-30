package ru.stqa.ptf.addressbook.folderSeven.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @XStreamOmitField
    private String allPhones;

    @Transient
    @XStreamOmitField
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    @XStreamOmitField
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

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

    public Groups getGroups() {

        return new Groups(groups);
    }

    public ContactData() {
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

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}