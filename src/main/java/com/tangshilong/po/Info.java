package com.tangshilong.po;

import java.io.Serializable;

public class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String url;
	private String name;
	private String source;
	private String writer;
	private String level;
	private String date;
	private Integer down;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDown() {
		return down;
	}

	public void setDown(Integer down) {
		this.down = down;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Info [id=" + id + ", url=" + url + ", name=" + name + ", source=" + source + ", writer=" + writer
				+ ", level=" + level + ", date=" + date + ", down=" + down + "]";
	}

	public Info() {
		super();
	}

}