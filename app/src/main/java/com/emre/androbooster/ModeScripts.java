package com.emre.androbooster;

/**
 * Created by emre on 24.04.2016.
 * Some edits at 08.05.2017
 */
public class ModeScripts {
    public static String[] GAME_MODE_SCRIPT=new String[]{
            "mount -o remount,rw /sys",
            "chmod 777 /sys/block/mmcblk0/bdi/read_ahead_kb",
			
            "chmod 777 /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
            "chmod 777 /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor",
            "chmod 777 /sys/devices/system/cpu/cpu2/cpufreq/scaling_governor",
            "chmod 777 /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor",
			
            "chmod 777 /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "chmod 777 /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "chmod 777 /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
			
            "chmod 777 /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "chmod 777 /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "chmod 777 /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
			
            "chmod 777 /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "chmod 777 /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "chmod 777 /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
			
            "echo ondemand > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
            "echo ondemand > /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor",
            "echo ondemand > /sys/devices/system/cpu/cpu2/cpufreq/scaling_governor",
            "echo ondemand > /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor",
			
            "echo 20 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "echo 20 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "echo 20 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
			
		    "echo 20 > /sys/devices/system/cpu/cpufreq/hotplug/cpu_up_threshold_multi_core",
		    "echo 20 > /sys/devices/system/cpu/cpufreq/hotplug/cpu_up_threshold_any_load",
		    "echo 20 > /sys/devices/system/cpu/cpufreq/hotplug/cpu_up_threshold",
			
            "echo 20 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "echo 20 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "echo 20 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
			
            "echo 20 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "echo 20 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "echo 20 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
			
            "echo 8192 > /sys/block/mmcblk0/bdi/read_ahead_kb",
    };


    public static String[] HIGH_MODE_SCRIPT=new String[]{
            "mount -o remount,rw /sys",
            "chmod 777 /sys/block/mmcblk0/bdi/read_ahead_kb",
            "chmod 777 /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
            "chmod 777 /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor",
            "chmod 777 /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "chmod 777 /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "chmod 777 /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
            "chmod 777 /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "chmod 777 /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "chmod 777 /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
            "chmod 777 /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "chmod 777 /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "chmod 777 /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
            "echo ondemand > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
            "echo ondemand > /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor",
            "echo 40 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "echo 40 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "echo 40 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
            "echo 40 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "echo 40 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "echo 40 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
            "echo 40 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "echo 40 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "echo 40 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
            "echo 2048 > /sys/block/mmcblk0/bdi/read_ahead_kb",
            };


    public static String[] DEFAULT=new String[]{
            "mount -o remount,rw /sys",
            "echo 90 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "echo 90 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "echo 90 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
            "echo 90 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "echo 90 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "echo 90 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
            "echo 90 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "echo 90 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "echo 90 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
            "echo 512 > /sys/block/mmcblk0/bdi/read_ahead_kb"};

    public static String EXIT = "exit";
}
