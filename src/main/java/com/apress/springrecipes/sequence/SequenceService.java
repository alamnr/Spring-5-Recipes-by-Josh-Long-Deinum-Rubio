package com.apress.springrecipes.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class SequenceService {
	
	/*
	 * @Autowired private SequenceDao sequenceDao;
	 * 
	 * public void setSequenceDao(SequenceDao sequenceDao) { this.sequenceDao =
	 * sequenceDao; }
	 */
	
	 private final SequenceDao sequenceDao;
	 
	 @Autowired
	 public SequenceService(SequenceDao sequenceDao) {
	      this.sequenceDao=sequenceDao;
	 }
	
	public String generate(String sequenceId) {
		Sequence sequence =  sequenceDao.getSequence(sequenceId);
		int value = sequenceDao.getNextValue(sequenceId);
		return sequence.getPrefix() + value + sequence.getSuffix();
	}

}
