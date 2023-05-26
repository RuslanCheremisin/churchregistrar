package efant.el.churchregistrar.model;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstName;
    private String patronymicName;
    private String lastName;
    private String phoneNumber;

    private LocalDate birthDate;

    @ManyToOne(targetEntity = Church.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "church_id")
    private Church church;
    @Enumerated(EnumType.STRING)
    private MemberPosition memberPosition;

    public Member() {
    }

    public Member(Long id,
                  String firstName,
                  String patronymicName,
                  String lastName,
                  String phoneNumber,
                  LocalDate birthDate,
                  Church church,
                  MemberPosition memberPosition) {
        this.id = id;
        this.firstName = firstName;
        this.patronymicName = patronymicName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.church = church;
        this.memberPosition = memberPosition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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
}