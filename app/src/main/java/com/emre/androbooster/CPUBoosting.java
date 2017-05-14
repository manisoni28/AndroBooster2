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
		if(c.isCompatibleForFreqTableAlgorithm()){
		s.setFrequencyScaling(c.maxFrequency(),c.maxFrequency());
		}
	}
	public void setHighMode(){
		if(c.isCompatibleForFreqTableAlgorithm()){
		s.setFrequencyScaling(c.maxFrequencyForHighBoosting(),c.maxFrequencyForHighBoosting());
		}
	}
	public void setDefault(){
		if(c.isCompatibleForFreqTableAlgorithm()){
		s.setFrequencyScaling(d.getDefaultMaxFreq(),d.getDefaultMinFreq());
		}
	}
}
