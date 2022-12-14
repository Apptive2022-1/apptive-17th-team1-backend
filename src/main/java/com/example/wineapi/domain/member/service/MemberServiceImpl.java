package com.example.wineapi.domain.member.service;

import com.example.wineapi.domain.member.entity.Member;
import com.example.wineapi.domain.member.dto.MemberDTO;
import com.example.wineapi.domain.member.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setEmail(memberDTO.getEmail());
        member.setPass(memberDTO.getPass());
        member.setGender(memberDTO.getGender());
        member.setName(memberDTO.getName());
        member.setAge(memberDTO.getAge());

        Member savedMember = memberDAO.insertMember(member);

        MemberDTO memberResponseDTO = new MemberDTO(savedMember.getEmail(), savedMember.getPass(), savedMember.getName(), savedMember.getGender(), savedMember.getAge());
        return memberResponseDTO;
    }

    @Override
    public MemberDTO getMember(Long id) {
        Member member = memberDAO.selectMember(id);

        MemberDTO memberResponseDTO = new MemberDTO(member.getEmail(), member.getPass(), member.getName(), member.getGender(), member.getAge());

        return memberResponseDTO;
    }

    public Long getId(String email) {
        return memberDAO.selectId(email);
    }

    @Override
    public void deleteMember(Long id) throws Exception {
        memberDAO.deleteMember(id);
    }

    public boolean login(MemberDTO memberDTO) {
        Member member = memberDAO.loginCheck(memberDTO.getEmail(), memberDTO.getPass());
        if(member == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean isDuplicated(String email) {
        if (memberDAO.findByEmail(email) == null){
            return false;
        }
        else {
            return true;
        }
    }

}
