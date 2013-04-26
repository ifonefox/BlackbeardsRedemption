package com.bbr.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public final class Song {
	public static final File SONG_LIST = new File("data/songlist.txt");
	protected static HashMap<String, Music> songs = new HashMap<String, Music>();

	private static boolean loaded = false;

	private Song() { }
	//
	//	static {
	//		String classJar = Art.class.getResource("/" + Art.class.getName().replace('.', '/') + ".class").toString();
	//		if (classJar.startsWith("jar:")) {
	//			runningInJar = true;
	//		} else {
	//			runningInJar = false;
	//		}
	//	}
	public static void load() { // Not in a static block due to Slick 
		if(loaded) {
			Utility.printWarning(
					"Loading song files after song has already been loaded.");
		}
		
		loadSongs();
		loaded = true;
	}
	private static void loadSongs() {
		SongFileReader fileReader = new SongFileReader(SONG_LIST);
		try {
			fileReader.readFile();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	protected static Music loadSong(String songName) throws SlickException {
		//System.out.println("loading "+ songName);
		//System.out.println(songName);
		
		return new Music(songName);
	}
	public static Music getMusic(String songName) {
		return songs.get(songName);
	}
}