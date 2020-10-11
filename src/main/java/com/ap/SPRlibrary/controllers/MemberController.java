package com.ap.SPRlibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.services.MemberService;

@RestController
@RequestMapping("/memberController")
public class MemberController {
	
	@Autowired 
	private MemberService memberService; 
	
	@PostMapping(value = "/insertNewMember")
	public OutputMsg insertNewBook(@RequestBody Member m) {
		return memberService.insertNewMember(m);
	}
	
	@GetMapping(value = "/getMember/{fiscal_code}")
	public Member getMember(@PathVariable String fiscal_code) {
		return memberService.getMember(fiscal_code);
	}
	
	@GetMapping(value = "/getAllMembers")
	public List<Member> getAllMembers(){
		return memberService.getAllMembers();
	}

	@PutMapping(value = "/updateMember/{fiscal_code}")
	public OutputMsg updateVideogioco(@RequestBody Member m, @PathVariable String fiscal_code) 
	{
		return memberService.updateMember(m, fiscal_code);
	}
	
	@DeleteMapping(value = "/deleteMember/{fiscal_code}")
	public OutputMsg deleteMember(@PathVariable String fiscal_code) 
	{
		return memberService.deleteMember(fiscal_code);
	}
}
