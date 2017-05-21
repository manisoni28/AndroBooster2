package com.emre.androbooster;

import java.io.*;
import java.util.*;

/**
 * Created by ogrenci on 5.05.2017.
 * Alkollü iken yazılan kodlar
 */

public class CPUFreqTable {
    static String PATH_OF_FREQ_TABLE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies";
	static String PATH_OF_MAX_FREQ = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";
	static String PATH_OF_MIN_FREQ = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq";
	
    public int MIN_FREQ;
    public int MAX_FREQ = 0;
	
	public boolean isCompatibleForFreqTableAlgorithm(){
		File a = new File(PATH_OF_FREQ_TABLE);
		return a.exists();
	}
	
	public String pseudoFreqTable() throws Exception {
		MIN_FREQ = (getFreqTable().length)-2;
		return abcde_min() + abcde_max() + abcde_max() + abcde_max();
	}

	public String abcde_max() throws Exception {
		MIN_FREQ = (getFreqTable().length)-2;
		File file = new File(PATH_OF_MAX_FREQ);
		StringBuilder freqTable = null;
		if(file.exists()){
			freqTable = new StringBuilder();

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;

				while ((line = br.readLine()) != null) {
					freqTable.append(line);
					//freqTable.append("\n");
				}

				br.close();
			} catch (Exception e) {

			}
		}else {

		}

		return freqTable.toString();

    }

	public String abcde_min() throws Exception {
		MIN_FREQ = (getFreqTable().length)-2;
		File file = new File(PATH_OF_MIN_FREQ);
		StringBuilder freqTable = null;
		if(file.exists()){
			freqTable = new StringBuilder();

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;

				while ((line = br.readLine()) != null) {
					freqTable.append(line);
					//freqTable.append("\n");
				}

				br.close();
			} catch (Exception e) {

			}
		}else {

		}

		return freqTable.toString();

    }
	
	
    public String[] getFreqTableAsString() throws Exception {
	//MIN_FREQ = (getFreqTable().length)-2;
       File file = new File(PATH_OF_FREQ_TABLE);
	   String real_result = null;
		StringBuilder freqTable = null;
	   if(file.exists()){
		   freqTable = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                freqTable.append(line);
              //freqTable.append("\n");
            }

            br.close();
			
			real_result = freqTable.toString();
        } catch (Exception e) {

        }
		}else {
			real_result = pseudoFreqTable();
		}
			
       return real_result.split(" ");
	 
    }
	
	
	public int[] getFreqTable() throws Exception {
		//MIN_FREQ = (getFreqTable().length)-2;
		int[] abcd = StringArrToIntArr(getFreqTableAsString());
		Arrays.sort(abcd);
		reverse(abcd);
		return abcd;
	}
	public int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			result[i] = Integer.parseInt(s[i]);
		}
		return result;
	}
	public static void reverse(int[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
	}
	
	
	public String maxFrequency() throws Exception {
		MIN_FREQ = (getFreqTable().length)-2;
		return String.valueOf(getFreqTable()[MAX_FREQ]);
	}
	
	public String minFrequency() throws Exception {
		MIN_FREQ = (getFreqTable().length)-2;
		return String.valueOf(getFreqTable()[MIN_FREQ]);
	}
	
	public String maxFrequencyForHighBoosting() throws Exception {
		MIN_FREQ = (getFreqTable().length)-2;
		return String.valueOf(getFreqTable()[MAX_FREQ+3]);
	}
}
