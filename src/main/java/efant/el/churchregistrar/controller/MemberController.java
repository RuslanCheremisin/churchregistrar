package efant.el.churchregistrar.controller;

import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.service.MemberService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity addMember(@RequestBody MemberDTO memberDTO){
        memberService.addMember(memberDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }

}
