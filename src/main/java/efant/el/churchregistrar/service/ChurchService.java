package efant.el.churchregistrar.service;

import efant.el.churchregistrar.dao.ChurchDAO;
import efant.el.churchregistrar.dao.MemberDAO;
import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.model.Church;
import efant.el.churchregistrar.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChurchService {
    private final ChurchDAO churchDAO;
    private final MemberDAO memberDAO;

    private final MemberService memberService;

    public ChurchService(ChurchDAO churchDAO, MemberDAO memberDAO, MemberService memberService) {
        this.churchDAO = churchDAO;
        this.memberDAO = memberDAO;
        this.memberService = memberService;
    }

    public ChurchDTO addChurch(ChurchDTO churchDTO) {
        Church church = dtoToChurch(churchDTO);
        churchDAO.save(church);
        return churchToDTO(church);
    }

    public void assignMemberToChurch(long churchId, long memberId) {
        Member member = memberDAO.findById(memberId).get();
        member.setChurch(churchDAO.findById(churchId).get());
        try{
            memberDAO.save(member);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ChurchDTO getChurchDTOById(long churchId) {
        return churchToDTO(churchDAO.findById(churchId).get());
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

    public Church dtoToChurch(ChurchDTO churchDTO) {
        Church church = new Church();
        church.setName(churchDTO.name());
        church.setAddress(churchDTO.address());
        church.setCity(churchDTO.city());
        church.setPhoneNumber(churchDTO.phoneNumber());
        return church;
    }

    private List<MemberDTO> membersToMembersDTOList(List<Member> memberList){
        return memberList.stream().map(member -> memberService.memberToDTO(member)).toList();
    }
}
