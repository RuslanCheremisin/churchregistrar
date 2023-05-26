package efant.el.churchregistrar.dto;

import java.util.List;

public record ChurchDTO(Long churchId, String name, String address, String city, String phoneNumber, List<MemberDTO> memberDTOList) {
}
