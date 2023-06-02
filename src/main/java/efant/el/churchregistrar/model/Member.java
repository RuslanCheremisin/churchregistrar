package efant.el.churchregistrar.model;


import efant.el.churchregistrar.dto.MemberDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String firstName;
    private String patronymicName;
    private String lastName;
    private String phoneNumber;

    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "church_id")
    private Church church;
    @Enumerated(EnumType.STRING)
    private MemberPosition memberPosition;
    @OneToMany(mappedBy = "member")
    private final List<Transaction> donationsAndReceivings = new LinkedList<>();

    public Member() {
    }

    public Member(Long memberId,
                  String firstName,
                  String patronymicName,
                  String lastName,
                  String phoneNumber,
                  LocalDate birthDate,
                  Church church,
                  MemberPosition memberPosition) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.patronymicName = patronymicName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.church = church;
        this.memberPosition = memberPosition;
    }

    public void setMemberId(Long id) {
        this.memberId = id;
    }

    public long getMemberId() {
        return memberId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    public MemberPosition getMemberPosition() {
        return memberPosition;
    }

    public void setMemberPosition(MemberPosition memberPosition) {
        this.memberPosition = memberPosition;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public MemberDTO memberToDTO() {
        return new MemberDTO(
                this.getMemberId(),
                this.getFirstName(),
                this.getPatronymicName(),
                this.getLastName(),
                this.getPhoneNumber(),
                this.getBirthDate(),
                this.getChurch().getChurchId(),
                this.getMemberPosition());
    }
}
