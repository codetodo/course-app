package com.codetodo.courseapp.model;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 2054472793137167214L;

	private final String title;
	private final int hours;
	private final CourseLevel level;

	private Course(Builder builder) {
		title = builder.title;
		hours = builder.hours;
		level = builder.level;
	}

	public String getTitle() {
		return title;
	}

	public int getHours() {
		return hours;
	}

	public CourseLevel getLevel() {
		return level;
	}

	public static class Builder {

		public CourseLevel level;
		public int hours;
		public String title;

		public Builder() {

		}

		public Builder setLevel(CourseLevel level) {
			this.level = level;
			return this;
		}

		public Builder setHours(int hours) {
			this.hours = hours;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Course build() {
			return new Course(this);
		}

	}

	public enum CourseLevel {
		BASIC, MEDIUM, ADVANCED;
	}

}
