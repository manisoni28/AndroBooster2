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
			try
			{
				s.setFrequencyScaling(c.maxFrequency(), c.maxFrequency());
			}
			catch (Exception e)
			{}
		}
	}
	public void setHighMode(){
		if(c.isCompatibleForFreqTableAlgorithm()){
			try
			{
				s.setFrequencyScaling(c.maxFrequencyForHighBoosting(), c.maxFrequencyForHighBoosting());
			}
			catch (Exception e)
			{}
		}
	}
	public void setDefault(){
		if(c.isCompatibleForFreqTableAlgorithm()){
		s.setFrequencyScaling(d.getDefaultMaxFreq(),d.getDefaultMinFreq());
		}
	}
}
