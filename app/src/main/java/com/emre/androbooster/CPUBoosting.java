package com.emre.androbooster;

public class CPUBoosting
{
	CPUFreqTable c = new CPUFreqTable();
	SetCPUFrequencyScaling s = new SetCPUFrequencyScaling();
	public void setUltraGamingMode(){
		s.setFrequencyScaling(c.maxFrequency(),c.maxFrequency());
	}
	public void setHighMode(){
		s.setFrequencyScaling(c.maxFrequencyForHighBoosting(),c.maxFrequencyForHighBoosting());
	}
	public void setDefault(){
		s.setFrequencyScaling(c.maxFrequencyForHighBoosting(),c.minFrequency());
	}
}
