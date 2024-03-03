//動作確認をする為に作成。後に削除。

package com.example.debugger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebuggerService {

	private final DebuggerRepository debuggerRepository;
	
	@Autowired
	public DebuggerService(DebuggerRepository debuggerRepository) {
		this.debuggerRepository = debuggerRepository;
	}
	
	public List<Debugger> findAll() {
		return this.debuggerRepository.findAll();
	}
}
