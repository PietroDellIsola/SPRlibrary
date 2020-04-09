package com.ap.SPRlibrary.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberRepository memberRepository;

	@Override
	public OutputMsg insertNewMember(Member m) {
		OutputMsg msg = new OutputMsg();

		try {

			if (memberRepository.findById(m.getFiscal_code()).equals(Optional.empty())) {
				/* book not present */

				memberRepository.save(m);
				msg.setMsg("Member inserted");
			} else {
				msg.setMsg("The member is already present");
			}

			return msg;
		} catch (Exception e) {
			msg.setMsg("There was an error");
			e.printStackTrace();

			return msg;
		}

	}

	@Override
	public Member getMember(String fiscal_code) {
		return memberRepository.getOne(fiscal_code);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public OutputMsg updateMember(Member newM) {
		/* with this service it is possible update only name, surname and dob. */
		OutputMsg msg = new OutputMsg();
		/*
		 * First, I look up the member, if he is present, the program set the null value
		 * sent with the old value
		 */

		if (!memberRepository.existsById(newM.getFiscal_code()))
			msg.setMsg("Member with fiscal code " + newM.getFiscal_code() + " not found");
		else {

			Member old = memberRepository.getOne(newM.getFiscal_code());

			if (newM.getName() == null)
				newM.setName(old.getName());
			if (newM.getSurname() == null)
				newM.setSurname(old.getSurname());
			if (newM.getDob() == null)
				newM.setDob(old.getDob());

			if (memberRepository.updateMember(newM.getFiscal_code(), newM.getName(), newM.getDob(),
					newM.getFiscal_code()) > 0) /* returns the numbers of updated columns */
				msg.setMsg("Member updated");

		}

		return msg;

	}

	@Override
	public OutputMsg deleteMember(Member m) {
		OutputMsg  msg= new OutputMsg();
		
		if(memberRepository.findById(m.getFiscal_code()).equals(Optional.empty()))
		{
			/*member not present*/
			msg.setMsg("Member not found");
		}
		else 	
		{
			memberRepository.delete(m);
			msg.setMsg("Member deleted");
		}
		
		return msg;
	}

}
