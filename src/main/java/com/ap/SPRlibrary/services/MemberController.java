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
@RequestMapping("/bookController")
public class MemberController {
	
	@Autowired 
	private MemberService memberService; 
	
	/*@GetMapping(value = "/getOrario")
	public OutputMsg getOrario() {
		return videogiochiService.getOrario();
	}*/
	
//	@RequestMapping(value = "/insertNuovoVideogioco", method = RequestMethod.POST)
	@PostMapping(value = "/insertNewMember")
	public OutputMsg insertNewBook(@RequestBody Member m) {
		return memberService.insertNewMember(m);
	}
	
	/*
	@PatchMapping(value = "/updateVideogioco")
	public OutputMsg updateVideogioco(@RequestBody Videogioco v) 
	{
		return videogiochiService.updateVideogioco(v);
	}*/
}
