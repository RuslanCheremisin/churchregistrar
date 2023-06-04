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

    public MemberDTO addMember(MemberDTO memberDTO) {
        Member member = dtoToMember(memberDTO);
        memberDAO.save(member);
        return member.memberToDTO();
    }

    public MemberDTO updateMember(Long memberId, MemberDTO memberDTO){
        Member member;
        try{
            member = memberDAO.findById(memberId).get();
        }catch (Exception e){
            throw new NoSuchEntityException("Член церкви с таким id отсутствует в базе");
        }
        member.setFirstName(memberDTO.firstName());
        member.setPatronymicName(memberDTO.patronymicName());
        member.setLastName(memberDTO.lastName());
        member.setPhoneNumber(memberDTO.phoneNumber());
        member.setBirthDate(memberDTO.birthDate());
        member.setChurch(memberDTO.churchId() == null || memberDTO.churchId() == 0 ? null : churchDAO.findById(memberDTO.churchId()).get());
        member.setMemberPosition(member.getMemberPosition());
        memberDAO.save(member);
        return member.memberToDTO();
    }



    private Member dtoToMember(MemberDTO memberDTO) {
        return new Member(
                memberDTO.id(),
                memberDTO.firstName(),
                memberDTO.patronymicName(),
                memberDTO.lastName(),
                memberDTO.phoneNumber(),
                memberDTO.birthDate(),
                memberDTO.churchId() == null || memberDTO.churchId() == 0 ? null : churchDAO.findById(memberDTO.churchId()).get(),
                MemberPosition.CHURCHGOER);
    }
    public List<MemberDTO> getAllMembers() {
        return memberDAO.findAll().stream().map(Member::memberToDTO).toList();
    }

    public boolean deleteMember(Long memberId) {
        try {
            memberDAO.deleteById(memberId);
        }catch (Exception e){
            throw new NoSuchEntityException("Член церкви с таким id отсутствует в базе");
        }
        return true;
    }

    public MemberDTO getMemberById(Long memberId) {
        MemberDTO memberDTO;
        try {
            Member member = memberDAO.findById(memberId).get();
            memberDTO = member.memberToDTO();
        }catch (Exception e){
            throw new NoSuchEntityException("Член церкви с таким id отсутствует в базе");
        }
        return memberDTO;
    }

    public MemberDTO changeMemberPosition(Long memberId, MemberPosition position) {
        Member member;
        try {
            member = memberDAO.findById(memberId).get();
        }catch (Exception e){
            throw new NoSuchEntityException("Член церкви с таким id отсутствует в базе");
        }
        member.setMemberPosition(position);
        memberDAO.save(member);
        return member.memberToDTO();
    }
}
