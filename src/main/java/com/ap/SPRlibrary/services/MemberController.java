package com.ap.SPRlibrary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;

@RestController
@RequestMapping("/memberController")
public class MemberController {
	
	@Autowired 
	private MemberService memberService; 
	
//	@RequestMapping(value = "/insertNuovoVideogioco", method = RequestMethod.POST)
	@PostMapping(value = "/insertNewMember")
	public OutputMsg insertNewBook(@RequestBody Member m) {
		return memberService.insertNewMember(m);
	}
	
	@GetMapping(value = "/getMember")
	public Member getMember(@RequestBody Member m) {
		return memberService.getMember(m.getFiscal_code());
	}
	
	/*
	@PatchMapping(value = "/updateVideogioco")
	public OutputMsg updateVideogioco(@RequestBody Videogioco v) 
	{
		return videogiochiService.updateVideogioco(v);
	}*/
}
