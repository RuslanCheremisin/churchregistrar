package efant.el.churchregistrar.dto;

import efant.el.churchregistrar.model.MemberPosition;

import java.time.LocalDate;

public record MemberDTO(Long id,
                        String firstName,
                        String patronymicName,
                        String lastName,
                        String phoneNumber,
                        LocalDate birthDate,
                        Long churchId,
                        MemberPosition memberPosition) {
}
