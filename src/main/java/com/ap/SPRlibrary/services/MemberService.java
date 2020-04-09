package com.ap.SPRlibrary.services;

import java.util.List;

import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;

public interface MemberService {
	public OutputMsg insertNewMember(Member m);
	public Member getMember(String fiscal_code);
	public List<Member> getAllMembers();
	public OutputMsg updateMember(Member newM);
	public OutputMsg deleteMember(Member m);
}
