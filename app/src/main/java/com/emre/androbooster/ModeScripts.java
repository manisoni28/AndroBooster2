package com.emre.androbooster;

/**
 * Created by emre on 24.04.2016.
 */
public class ModeScripts {
    public static String[] GAME_MODE_SCRIPT=new String[]{
            "mount -o remount,rw /system",
            "echo ondemand /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
            "echo 11 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "echo 11 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "echo 11 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
            "echo 11 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "echo 11 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "echo 11 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
            "echo 11 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "echo 11 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "echo 11 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
            "echo 8192 > /sys/block/mmcblk0/bdi/read_ahead_kb",
    };


    public static String[] HIGH_MODE_SCRIPT=new String[]{
            "mount -o remount,rw /system",
            "echo 30 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "echo 30 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "echo 30 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
            "echo 30 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "echo 30 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "echo 30 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
            "echo 30 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "echo 30 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "echo 30 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
            "echo 3072 > /sys/block/mmcblk0/bdi/read_ahead_kb"
            };


    public static String[] DEFAULT=new String[]{
            "mount -o remount,rw /system",
            "echo 90 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_multi_core",
            "echo 90 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold_any_load",
            "echo 90 > /sys/devices/system/cpu/cpufreq/hotplug/up_threshold",
            "echo 90 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold",
            "echo 90 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_any_load",
            "echo 90 > /sys/devices/system/cpu/cpufreq/interactive/up_threshold_multi_core",
            "echo 90 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_any_load",
            "echo 90 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold_multi_core",
            "echo 90 > /sys/devices/system/cpu/cpufreq/ondemand/up_threshold",
            "echo 1024 > /sys/block/mmcblk0/bdi/read_ahead_kb"};

    public static String EXIT = "exit";
}
