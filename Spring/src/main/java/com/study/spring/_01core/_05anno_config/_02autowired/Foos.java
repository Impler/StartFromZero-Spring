package com.study.spring._01core._05anno_config._02autowired;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Foos {

	@Autowired
	private List<Bar> bars;

	@Override
	public String toString() {
		return "Foos [bars=" + bars + "]";
	}
}
