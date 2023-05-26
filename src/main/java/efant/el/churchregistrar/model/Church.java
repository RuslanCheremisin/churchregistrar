package efant.el.churchregistrar.model;


import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.service.MemberService;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "churches")
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "church_id", nullable = false)
    private Long churchId;
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    @OneToMany(targetEntity = Member.class, mappedBy = "church", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Member> members = new LinkedList<>();

    public Church() {
    }

    public Church(Long churchId, String name, String city, String address, String phoneNumber, List<Member> memberList) {
        this.churchId = churchId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.members = memberList;
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
        return memberList.stream().map(member -> member.memberToDTO(member)).toList();
    }
}
