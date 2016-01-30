package it.mondogrua.utils;

import it.mondogrua.count.Count;

public interface CountObserver {
	public void update();
	public void setSubject(Count aCount) ;
}
