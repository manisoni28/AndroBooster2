package com.emre.androbooster;

import android.content.*;

public class CPUBoosting
{
	DefaultFreqs d;
	public CPUBoosting(Context c){
		d = new DefaultFreqs(c);
	}
	CPUFreqTable c = new CPUFreqTable();
	SetCPUFrequencyScaling s = new SetCPUFrequencyScaling();
	public void setUltraGamingMode(){
		s.setFrequencyScaling(c.maxFrequency(),c.maxFrequency());
	}
	public void setHighMode(){
		s.setFrequencyScaling(c.maxFrequencyForHighBoosting(),c.maxFrequencyForHighBoosting());
	}
	public void setDefault(){
		s.setFrequencyScaling(d.getDefaultMaxFreq(),d.getDefaultMinFreq());
	}
}
