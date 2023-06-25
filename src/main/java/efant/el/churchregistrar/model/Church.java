package efant.el.churchregistrar.model;


import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.util.ValidateUtil;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "churches")
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long churchId;
    @Column(name = "church_name")
    private String churchName;
    @Column(name = "city")
    private String city;
    @Column(name = "address")

    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "church", cascade = CascadeType.ALL)
    private final List<Member> members = new LinkedList<>();
    @JoinColumn(name = "church_account_id")
    @OneToOne(fetch = FetchType.EAGER)
    private ChurchAccount churchAccount;

    public Church() {
    }

    public Church(String churchName,
                  String city,
                  String address,
                  String phoneNumber,
                  ChurchAccount churchAccount) {
        this.churchName = ValidateUtil.stringIsValid(churchName);
        this.city = ValidateUtil.stringIsValid(city);
        this.address = ValidateUtil.stringIsValid(address);
        this.phoneNumber = ValidateUtil.phoneNumberIsValid(phoneNumber);
        this.churchAccount = churchAccount;
    }

    public Church(Long churchId, String churchName, String city, String address, String phoneNumber, ChurchAccount churchAccount) {
        this.churchId = churchId;
        this.churchName = ValidateUtil.stringIsValid(churchName);
        this.city = ValidateUtil.stringIsValid(city);
        this.address = ValidateUtil.stringIsValid(address);
        this.phoneNumber = ValidateUtil.phoneNumberIsValid(phoneNumber);
        this.churchAccount = churchAccount;
    }

    public Church(String churchName, String address, String city, String phoneNumber) {
        this.churchName = ValidateUtil.stringIsValid(churchName);
        this.city = ValidateUtil.stringIsValid(city);
        this.address = ValidateUtil.stringIsValid(address);
        this.phoneNumber = ValidateUtil.phoneNumberIsValid(phoneNumber);
    }

    public Long getChurchId() {
        return churchId;
    }


    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Member> getMembers() {
        return members;
    }

    public ChurchAccount getChurchAccount() {
        return churchAccount;
    }

    public void setChurchAccount(ChurchAccount churchAccount) {
        this.churchAccount = churchAccount;
    }

    private ChurchDTO churchToDTO(Church church) {
        return new ChurchDTO(
                church.getChurchId(),
                church.getChurchName(),
                church.getAddress(),
                church.getCity(),
                church.getPhoneNumber(),
                membersToMembersDTOList(church.getMembers()));
    }

    private List<MemberDTO> membersToMembersDTOList(List<Member> memberList) {
        return memberList.stream().map(Member::memberToDTO).toList();
    }
}
