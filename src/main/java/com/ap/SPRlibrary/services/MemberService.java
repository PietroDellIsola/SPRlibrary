package com.ap.SPRlibrary.services;

import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;

public interface MemberService {
	public OutputMsg insertNewMember(Member m);
	public Member getMember(String fiscal_code);
}
