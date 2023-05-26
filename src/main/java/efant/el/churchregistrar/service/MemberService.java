package efant.el.churchregistrar.service;

import efant.el.churchregistrar.dao.ChurchDAO;
import efant.el.churchregistrar.dao.MemberDAO;
import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.model.Member;
import efant.el.churchregistrar.model.MemberPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberDAO memberDAO;
    private final ChurchDAO churchDAO;

    public MemberService(MemberDAO memberDAO, ChurchDAO churchDAO) {
        this.memberDAO = memberDAO;
        this.churchDAO = churchDAO;
    }

    public MemberDTO addMember(MemberDTO memberDTO){
        Member member = dtoToMember(memberDTO);
        memberDAO.save(member);
        return memberToDTO(member);
    }

    public MemberDTO memberToDTO(Member member){
        return new MemberDTO(
                member.getId(),
                member.getFirstName(),
                member.getPatronymicName(),
                member.getLastName(),
                member.getPhoneNumber(),
                member.getBirthDate(),
                member.getChurch().getChurchId());
    }

    private Member dtoToMember(MemberDTO memberDTO){
        Member member = new Member();
        member.setFirstName(memberDTO.firstName());
        member.setPatronymicName(memberDTO.patronymicName());
        member.setLastName(memberDTO.lastName());
        member.setPhoneNumber(memberDTO.phoneNumber());
        member.setBirthDate(memberDTO.birthDate());
        member.setMemberPosition(MemberPosition.CHURCHGOER);
        member.setChurch(churchDAO.findById(memberDTO.churchId()).get());
        return member;
    }


    public List<MemberDTO> getAllMembers() {
        return memberDAO.findAll().stream().map(this::memberToDTO).toList();
    }
}
