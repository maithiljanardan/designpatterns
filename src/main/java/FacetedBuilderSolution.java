/**
 * Here in the example we will delegate the address building and job building to sub builders
 * This breaks Depending Inversion principle because the super class is dependent on sub classes
 * also breaks open and close principle as the super class will need to be changed if any new sub builder is introduced
 */
public class FacetedBuilderSolution {


  public static void main(String[] args) {

    Person.PersonBuilder personBuilder = new Person.PersonBuilder();
    Person person = personBuilder.lives()
        .at("123 Lucknow Road").withPostcode("110001").in("Delhi")
        .works().at("TCS").asA("Software Developer").earning(100000).build();
    System.out.println(person);
  }

  private static class Person {
    private String streetAddress;
    private String postcode;
    private String city;
    // employment
    private String companyName;
    private String position;
    private int annualIncome;

    @Override
    public String toString() {
      return "Person{" +
          "streetAddress='" + streetAddress + '\'' +
          ", postcode='" + postcode + '\'' +
          ", city='" + city + '\'' +
          ", companyName='" + companyName + '\'' +
          ", position='" + position + '\'' +
          ", annualIncome=" + annualIncome +
          '}';
    }

    private static class PersonBuilder {
      protected Person person = new Person();

      public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
      }

      public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
      }

      protected Person build() {
        return person;
      }
    }

    private static class PersonAddressBuilder extends PersonBuilder {

      public PersonAddressBuilder(Person person) {
        this.person = person;
      }

      public PersonAddressBuilder at(String street) {
        person.streetAddress = street;
        return this;
      }

      public PersonAddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
      }

      public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
      }
    }

    private static class PersonJobBuilder extends PersonBuilder {

      public PersonJobBuilder(Person person) {
        this.person = person;
      }

      public PersonJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
      }

      public PersonJobBuilder asA(String position) {
        person.position = position;
        return this;
      }

      public PersonJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
      }
    }
  }


}
