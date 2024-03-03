//動作確認をする為に作成。後に削除。

package com.example.debugger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebuggerRepository extends JpaRepository<Debugger, Integer> {
	
}