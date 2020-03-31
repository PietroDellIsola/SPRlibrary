package com.ap.SPRlibrary.services;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Resource
    private MemberRepository memberRepository;
	
	
	public OutputMsg insertNewMember(Member m) {
		OutputMsg msg = new OutputMsg();
		
		try {
			
			if(memberRepository.findById(m.getFiscal_code()).equals(Optional.empty()))
			{
				/*book not present*/
				
				memberRepository.save(m);
				msg.setMsg("Member inserted");
			}
			else 	
			{
				msg.setMsg("The member is already present");
			}
			
			return msg;
		}
		catch (Exception e) {
			msg.setMsg("There was an error");
			e.printStackTrace();
			
			return msg;
		}
		
	}
	
	public Member getMember(String fiscal_code) {
		return memberRepository.getOne(fiscal_code);
	}

	
}
