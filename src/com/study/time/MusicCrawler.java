package com.study.time;

import java.util.HashSet;

import com.study.bean.song;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/*
 * 爬虫,获取推荐歌曲
 */
public class MusicCrawler extends Thread implements PageProcessor {
	private static MusicCrawler instance = new MusicCrawler();
	private static Spider s;
	private static HashSet<song> songs = new HashSet<song>();

	private MusicCrawler() {
	}

	public static MusicCrawler getInstance() {
		return instance;
	}

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	public static final String URL_LIST = "/gd/gdList\\?&type=\\d+&page=\\d+";
	public static final String URL_SONGL = "http://5sing\\.kugou\\.com/\\w+/dj/\\w+\\.html";
	public static final String URL_SONG = "http://5sing\\.kugou\\.com/\\w+/\\d+\\.html";

	public void process(Page page) {
		if (page.getUrl().regex(URL_LIST).match()) {
			page.addTargetRequests(page.getHtml().xpath("//ul[@class=\"fix\"]")
					.links().regex(URL_SONGL).all());
			// page
			// .addTargetRequests(page.getHtml().xpath(
			// "//span[@class=\"pagecon\"]").links().regex(
			// URL_LIST).all());
			// 文章页
		} else if (page.getUrl().regex(URL_SONGL).match()) {
			page
					.addTargetRequests(page.getHtml().xpath(
							"//span[@class=\"s_title\"]").links().regex(
							URL_SONG).all());
		} else if (page.getUrl().regex(URL_SONG).match()) {
			String songName = getText(page.getHtml().xpath("//h1").toString());
			song song = new song();
			song.setSongName(songName);
			song.setSongUrl(page.getUrl().toString());
			songs.add(song);
		}
	}

	public Site getSite() {
		return site;
	}

	public void getMusic(int page) {
		System.out.println("获取音乐数据");
		songs.clear();
		s = Spider.create(new MusicCrawler()).addUrl(
				"http://5sing.kugou.com/gd/gdList?&type=0&page=" + page)
				.thread(20);
		s.run();
		System.out.println("获取音乐数据完成");
	}

	public static String getText(String ele) {
		String result = ele
				.substring(ele.indexOf(">") + 1, ele.indexOf("<", 2));
		return result;

	}

	public static HashSet<song> getSongs() {
		return songs;
	}

	public static void setSongs(HashSet<song> songs) {
		MusicCrawler.songs = songs;
	}
	
	
}
