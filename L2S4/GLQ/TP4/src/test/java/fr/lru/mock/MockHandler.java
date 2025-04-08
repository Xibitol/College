package fr.lru.mock;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MockHandler extends Handler{

	private LogRecord lastRecord = null;

	// GETTERS
	public String getLastMessage(){
		return lastRecord == null ? null : lastRecord.getMessage();
	}

	// FUNCTIONS
	@Override
	public void publish(LogRecord logRecord){
		lastRecord = logRecord;
	}

	@Override
	public void flush(){
		// Doesn't have to flush.
	}

	@Override
	public void close() throws SecurityException{
		// Doesn't have to close.
	}
}
