package efant.el.churchregistrar.service;

import efant.el.churchregistrar.dao.ChurchAccountDAO;
import efant.el.churchregistrar.dao.ChurchDAO;
import efant.el.churchregistrar.dao.MemberDAO;
import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.model.Church;
import efant.el.churchregistrar.model.ChurchAccount;
import efant.el.churchregistrar.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChurchService {
    private final ChurchDAO churchDAO;
    private final ChurchAccountDAO churchAccountDAO;
    private final MemberDAO memberDAO;

    public ChurchService(ChurchDAO churchDAO, ChurchAccountDAO churchAccountDAO, MemberDAO memberDAO) {
        this.churchDAO = churchDAO;
        this.churchAccountDAO = churchAccountDAO;
        this.memberDAO = memberDAO;
    }

    public ChurchDTO addChurch(ChurchDTO churchDTO) {
        Church church = dtoToChurch(churchDTO);
        churchDAO.save(church);
        ChurchAccount churchAccount = new ChurchAccount(0L, church);
        churchAccountDAO.save(churchAccount);
        church.setChurchAccount(churchAccount);
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

    public List<ChurchDTO> getAllChurchesDTO(){
        return churchDAO.findAll().stream().map(this::churchToDTO).toList();
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

    public Church dtoToChurch(ChurchDTO churchDTO) {
        return new Church(
                churchDTO.name(),
                churchDTO.address(),
                churchDTO.city(),
                churchDTO.phoneNumber());
    }

    private List<MemberDTO> membersToMembersDTOList(List<Member> memberList){
        return memberList.stream().map(Member::memberToDTO).toList();
    }
}
