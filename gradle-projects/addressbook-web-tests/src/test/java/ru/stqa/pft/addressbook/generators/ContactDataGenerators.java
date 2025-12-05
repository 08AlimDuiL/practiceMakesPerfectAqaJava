package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.Parameter;

/**
 * Генератор тестовых данных для контактов адресной книги.
 * Создает файл в выбранном формате.
 */
public class ContactDataGenerators {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

//    public static void main(String[] args) throws IOException {
//
//        ContactDataGenerators generator = new ContactDataGenerators();
//        JCommander jCommander = new JCommander(generator);
//        try {
//            jCommander.parse(args);
//        } catch (ParameterException ex) {
//            jCommander.usage();
//            return;
//        }
//        generator.run();
//    }
//
//    private void run() throws IOException {
//        List<ContactData> contact = generateContacts(count);
//
//        if (format.equals("csv")) {
//            saveAsCsv(contact, new File(file));
//        } else if (format.equals("xml")) {
//            saveAsXml(contact, new File(file));
//        } else if (format.equals("json")) {
//            saveAsJson(contact, new File(file));
//        } else {
//            System.out.println("Unrecognized Format " + format);
//        }
//
//    }
//
//    private void saveAsJson(List<ContactData> contact, File file) throws IOException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
//        String json = gson.toJson(contact);
//        try (Writer writer = new FileWriter(file)) {
//            writer.write(json);
//        }
//    }
//
//    private void saveAsXml(List<ContactData> contact, File file) throws IOException {
//        XStream xstream = new XStream();
//        xstream.processAnnotations(ContactData.class);
//        xstream.alias("contact", ContactData.class);
//        String xml = xstream.toXML(contact);
//        Writer writer = new FileWriter(file);
//        writer.write(xml);
//        writer.close();
//    }
//
//    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
//        System.out.println(new File(".").getAbsolutePath());
//        Writer writer = new FileWriter(file);
//        for (ContactData contact : contacts) {
//            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
//                    contact.getFirstName(),
//                    contact.getMiddleName(),
//                    contact.getLastName(),
//                    contact.getNickName(),
//                    contact.getCompany(),
//                    contact.getAddress(),
//                    contact.getHomePhoneNumber(),
//                    contact.getMobilePhoneNumber(),
//                    contact.getWorkPhoneNumber(),
//                    contact.getFax(),
//                    contact.getEmail(),
//                    contact.getEmail2(),
//                    contact.getEmail3(),
//                    contact.getBirthDay(),
//                    contact.getBirthMonth(),
//                    contact.getBirthYear(),
//                    contact.getGroup()
//            ));
//        }
//        writer.close();
//    }
//
//    private static List<ContactData> generateContacts(int count) {
//        String[] names = {"Иван", "Алексей", "Сергей", "Дмитрий", "Михаил", "Андрей", "Александр", "Максим", "Владимир", "Павел"};
//        String[] middleNames = {"Иванович", "Петрович", "Сергеевич", "Алексеевич", "Дмитриевич", "Михайлович", "Андреевич", "Владимирович", "Олегович", "Юрьевич"};
//        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Попов", "Васильев", "Новиков", "Федоров", "Морозов"};
//        String[] nickNames = {"CoolGuy", "SuperUser", "DarkKnight", "SilverFox", "RedDragon", "BlueWolf", "FastRunner", "SmartCat", "LuckyMan", "IronMan"};
//        String[] companies = {"Рога и копыта", "ТехноИнновации", "СтройГарант", "ИТ Решения", "МедСервис", "ТрансЛогистика", "ФинансГрупп", "ЭнергоПром", "АгроХолдинг", "ТелекомСервис"};
//        String[] homePhoneNumbers = {"+7(495)123-45-", "+7(495)234-56-", "+7(495)345-67-", "+7(495)456-78-", "+7(499)111-22-", "+7(499)222-33-", "+7(499)333-44-", "+7(499)444-55-", "+7(495)555-66-", "+7(495)666-77-"};
//        String[] mobilePhoneNumbers = {"+7(901)123-45-", "+7(902)234-56-", "+7(903)345-67-", "+7(904)456-78-", "+7(905)567-89-", "+7(906)678-90-", "+7(907)789-01-", "+7(908)890-12-", "+7(909)901-23-", "+7(910)012-34-"};
//        String[] workPhoneNumbers = {"+7(495)765-43-", "+7(495)876-54-", "+7(495)987-65-", "+7(495)098-76-", "+7(499)111-00-", "+7(499)222-11-", "+7(499)333-22-", "+7(499)444-33-", "+7(495)555-44-", "+7(495)666-55-"};
//        String[] faxNumbers = {"+7(495)111-22-", "+7(495)222-33-", "+7(495)333-44-", "+7(495)444-55-", "+7(499)555-66-", "+7(499)666-77-", "+7(499)777-88-", "+7(499)888-99-", "+7(495)999-00-", "+7(495)000-11-"};
//        String[] birthMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//        String[] emails = {"ivanov@mail.ru", "petrov@gmail.com", "sidorov@yandex.ru", "smirnov@company.com", "kuznetsov@inbox.ru", "popov@hotmail.com", "vasilev@list.ru", "novikov@bk.ru", "fedorov@rambler.ru", "morozov@outlook.com"};
//        String[] emails2 = {"ivanov.work@mail.ru", "petrov.work@gmail.com", "sidorov.work@yandex.ru", "smirnov.work@company.com", "kuznetsov.work@inbox.ru", "popov.work@hotmail.com", "vasilev.work@list.ru", "novikov.work@bk.ru", "fedorov.work@rambler.ru", "morozov.work@outlook.com"};
//        String[] emails3 = {"ivanov.personal@mail.ru", "petrov.personal@gmail.com", "sidorov.personal@yandex.ru", "smirnov.personal@company.com", "kuznetsov.personal@inbox.ru", "popov.personal@hotmail.com", "vasilev.personal@list.ru", "novikov.personal@bk.ru", "fedorov.personal@rambler.ru", "morozov.personal@outlook.com"};
//        String[] addresses = {"ул. Ленина", "пр. Мира", "ул. Советская", "пл. Победы", "ул. Центральная", "бульвар Строителей", "ул. Садовая", "пр. Космонавтов", "ул. Молодежная", "ул. Школьная"};
//        String[] groups = {"test 1", "test 2", "test 3", "test 4", "test 5", "test 6", "test 7", "test 8", "test 9", "test 10"};
//
//        Random random = new Random();
//        List<ContactData> contacts = new ArrayList<ContactData>();
//        for (int i = 0; i < count; i++) {
//            int digit = i % 1000;
//
//            contacts.add(new ContactData()
//                    .withFirstName(names[random.nextInt(names.length)])
//                    .withMiddleName(middleNames[random.nextInt(middleNames.length)])
//                    .withLastName(lastNames[random.nextInt(lastNames.length)])
//                    .withNickName(nickNames[random.nextInt(nickNames.length)] + String.valueOf(random.nextInt(81) + 1920))
//                    .withCompany(companies[random.nextInt(companies.length)])
//                    .withAddress(addresses[random.nextInt(addresses.length)] + " " + random.nextInt(100))
//                    .withHomePhoneNumber(homePhoneNumbers[random.nextInt(homePhoneNumbers.length)] + String.format("%03d", digit))
//                    .withMobilePhoneNumber(mobilePhoneNumbers[random.nextInt(mobilePhoneNumbers.length)] + String.format("%03d", digit))
//                    .withWorkPhoneNumber(workPhoneNumbers[random.nextInt(workPhoneNumbers.length)] + String.format("%03d", digit))
//                    .withFax(faxNumbers[random.nextInt(faxNumbers.length)] + String.format("%03d", digit))
//                    .withEmail(String.format("%s", i) + emails[random.nextInt(emails.length)])
//                    .withEmail2(String.format("%s", i) + emails2[random.nextInt(emails2.length)])
//                    .withEmail3(String.format("%s", i) + emails3[random.nextInt(emails3.length)])
//                    .withBirthDay(String.valueOf(random.nextInt(28) + 1))
//                    .withBirthMonth(birthMonths[random.nextInt(birthMonths.length)])
//                    .withBirthYear(String.valueOf(random.nextInt(81) + 1920))
//                    .withGroup(groups[random.nextInt(groups.length)])
//            );
//        }
//        return contacts;
//    }
}
