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
	public OutputMsg updateMember(Member newM, String fiscal_code) {

		OutputMsg m = new OutputMsg();
		Optional<Member> oldMember = memberRepository.findById(fiscal_code);
		
		if (newM.getFiscal_code() != null && !newM.getFiscal_code().equals(fiscal_code)) {
			m.setMsg("You can't edit the fiscal code of Members");
			return m;
		}

		if (!oldMember.isPresent()) {
			m.setMsg("Member not found");
		}

		else {
			if (newM.getName() == null)
				newM.setName(oldMember.get().getName());
			if (newM.getSurname() == null)
				newM.setSurname(oldMember.get().getSurname());
			if (newM.getDob() == null)
				newM.setDob(oldMember.get().getDob());
			if (newM.getFiscal_code() == null)
				newM.setFiscal_code(oldMember.get().getFiscal_code());

			if (memberRepository.updateMember(newM.getName(), newM.getSurname(), newM.getDob(),
					newM.getFiscal_code()) > 0) /* returns the numbers of updated columns */
				m.setMsg("Member updated");

			else
				m.setMsg("Member not updated");

		}

		return m;
	}

	@Override
	public OutputMsg deleteMember(String fiscal_code) {
		OutputMsg msg = new OutputMsg();

		Optional<Member> m = memberRepository.findById(fiscal_code);

		if (m.equals(Optional.empty())) {
			/* member not present */
			msg.setMsg("Member not found");
		} else {
			memberRepository.delete(m.get());
			msg.setMsg("Member deleted");
		}

		return msg;
	}

}
