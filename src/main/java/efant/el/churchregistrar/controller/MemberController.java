package efant.el.churchregistrar.controller;

import efant.el.churchregistrar.dto.MemberDTO;
import efant.el.churchregistrar.model.MemberPosition;
import efant.el.churchregistrar.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/")
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO){
        return ResponseEntity.ok(memberService.addMember(memberDTO));
    }
    @PutMapping("/")
    public ResponseEntity<MemberDTO> updateMember(@RequestParam Long memberId, @RequestBody MemberDTO memberDto){
        return ResponseEntity.ok(memberService.updateMember(memberId, memberDto));
    }
    @PutMapping("/change-member-position")
    public ResponseEntity<MemberDTO> changeMemberPosition(@RequestParam Long memberId,@RequestParam MemberPosition position){
        return ResponseEntity.ok(memberService.changeMemberPosition(memberId, position));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteMember(@RequestParam Long memberId){
        if(memberService.deleteMember(memberId)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable(name = "id")Long memberId){
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @GetMapping("/")
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }

}
