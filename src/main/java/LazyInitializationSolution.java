import java.util.HashMap;
import java.util.Map;

public class LazyInitializationSolution {
  public static void main(String[] args) {

    User user = (User) SessionImitator.get(User.class, "1");
    System.out.println(user);
    System.out.println(user.getAddress());

  }

  private static class SessionImitator {

    public static Object get(Class clazz, String id) {
      return UserRepository.getUser(id);
    }
  }

  private static class Address {
    private String street;
    private String area;
    private String city;
    private int pincode;

    public Address(String street, String area, String city, int pincode) {
      this.street = street;
      this.area = area;
      this.city = city;
      this.pincode = pincode;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getArea() {
      return area;
    }

    public void setArea(String area) {
      this.area = area;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public int getPincode() {
      return pincode;
    }

    public void setPincode(int pincode) {
      this.pincode = pincode;
    }

    @Override
    public String toString() {
      return "Address{" +
          "street='" + street + '\'' +
          ", area='" + area + '\'' +
          ", city='" + city + '\'' +
          ", pincode=" + pincode +
          '}';
    }
  }

  private static class User {
    private String name;
    private String id;

    private Address address;

    public User(String name, String id) {
      this.name = name;
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public Address getAddress() {
      return address;
    }

    public void setAddress(Address address) {
      this.address = address;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      if (!name.equals(user.name)) return false;
      return id.equals(user.id);
    }

    @Override
    public int hashCode() {
      int result = name.hashCode();
      result = 31 * result + id.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "User{" +
          "name='" + name + '\'' +
          ", id='" + id + '\'' +
          '}';
    }
  }

  private static class ProxyUser extends User {
    private String name;
    private String id;

    private Address address;

    public ProxyUser(String name, String id) {
      super(name, id);
      this.name = name;
      this.id = id;
    }


    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ProxyUser proxyUser = (ProxyUser) o;
      return new org.apache.commons.lang.builder.EqualsBuilder().append(name, proxyUser.name).append(id, proxyUser.id).isEquals();
    }

    @Override
    public int hashCode() {
      return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37).append(name).append(id).toHashCode();
    }

    @Override
    public Address getAddress() {
      if (super.getAddress() == null) {
        //fetch from database;
        System.out.println("Going to fetch address now");
        super.setAddress(UserRepository.getAddress(this));
      }
      return super.getAddress();
    }

    @Override
    public void setAddress(Address address) {
      this.address = address;
    }

    @Override
    public String toString() {
      return "ProxyUser{" +
          "name='" + name + '\'' +
          ", id='" + id + '\'' +
          '}';
    }
  }

  private static class UserRepository {

    private static final Map<String, ProxyUser> userMap = new HashMap<String, ProxyUser>() {
      {
        put("1", new ProxyUser("John Doe", "1"));
        put("2", new ProxyUser("Anna Smith", "2"));
      }
    };

    private static final Map<ProxyUser, Address> userAddressMap = new HashMap<>() {
      {
        put(new ProxyUser("John Doe", "1"), new Address("6", "Janakpuri", "Delhi", 110053));
        put(new ProxyUser("Anna Smith", "2"), new Address("17", "Yamuna Vihar", "Delhi", 110094));

      }
    };

    public static ProxyUser getUser(String id) {
      return userMap.get(id);
    }

    public static Address getAddress(ProxyUser user) {
      return userAddressMap.get(user);
    }

  }
}
