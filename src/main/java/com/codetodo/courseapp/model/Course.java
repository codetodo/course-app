package com.codetodo.courseapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Course implements Serializable {

	private static final long serialVersionUID = 2054472793137167214L;

	private final Long id;
	private final String title;
	private final int hours;
	private final CourseLevel level;

	private Course(Builder builder) {
		id = builder.id;
		title = builder.title;
		hours = builder.hours;
		level = builder.level;
	}

	public Long getId() {
		return id;
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

	@Override
	public int hashCode() {
		return Objects.hash(this.title, this.level);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Course other = (Course) obj;
		return Objects.equals(this.title, other.title) && Objects.equals(this.level, other.level);
	}

	public static class Builder {

		public Long id;
		public CourseLevel level;
		public int hours;
		public String title;

		public Builder() {

		}

		public Builder setId(Long id) {
			this.id = id;
			return this;
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
		
		static final Map<String, CourseLevel> VALUES = new HashMap<String, CourseLevel>();

		static {
			for (CourseLevel level : CourseLevel.values())
				VALUES.put(level.toString(), level);
		}
		
		public static CourseLevel fromString(String value) {
			return VALUES.get(value);
		}
	}

}
