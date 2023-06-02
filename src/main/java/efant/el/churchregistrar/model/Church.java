package efant.el.churchregistrar.model;


import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.dto.MemberDTO;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "churches")
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long churchId;
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "church", cascade = CascadeType.ALL)
    private final List<Member> members = new LinkedList<>();
    @JoinColumn(name = "church_account_id", nullable = false)
    @OneToOne(targetEntity = ChurchAccount.class)
    private ChurchAccount churchAccount;

    public Church() {
    }

    public Church(Long churchId, String name, String city, String address, String phoneNumber, ChurchAccount churchAccount) {
        this.churchId = churchId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.churchAccount = churchAccount;
    }

    public void setChurchId(Long churchId) {
        this.churchId = churchId;
    }

    public long getChurchId() {
        return this.churchId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
                church.getName(),
                church.getAddress(),
                church.getCity(),
                church.getPhoneNumber(),
                membersToMembersDTOList(church.getMembers()));
    }
    private List<MemberDTO> membersToMembersDTOList(List<Member> memberList){
        return memberList.stream().map(Member::memberToDTO).toList();
    }
}
