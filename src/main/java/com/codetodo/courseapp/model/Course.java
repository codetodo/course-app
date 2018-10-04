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
	private final boolean isActive;

	private final Professor professor;

	private Course(Builder builder) {
		id = builder.id;
		title = builder.title;
		hours = builder.hours;
		level = builder.level;
		isActive = builder.isActive;
		professor = builder.professor;
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

	public boolean isActive() {
		return isActive;
	}

	public Professor getProfessor() {
		return professor;
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

		private Long id;
		private CourseLevel level;
		private int hours;
		private String title;
		private boolean isActive;
		private Professor professor;

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

		public Builder setProfessor(Professor professor) {
			this.professor = professor;
			return this;
		}

		public Builder setActive(boolean isActive) {
			this.isActive = isActive;
			return this;
		}

		public Course build() {
			return new Course(this);
		}

	}

	public enum CourseLevel {
		BASIC("1", "Basico"), MEDIUM("2", "Intermedio"), ADVANCED("3", "Avanzado");

		static final Map<String, CourseLevel> BY_NAME_MAPPER = new HashMap<>();
		static final Map<String, CourseLevel> BY_ID_MAPPER = new HashMap<>();

		private String id;
		private String text;

		static {
			for (CourseLevel level : CourseLevel.values()) {
				BY_NAME_MAPPER.put(level.toString(), level);
				BY_ID_MAPPER.put(level.id, level);
			}
		}

		private CourseLevel(String id, String text) {
			this.id = id;
			this.text = text;
		}

		public String getId() {
			return id;
		}

		public String getText() {
			return text;
		}

		public static CourseLevel fromName(String value) {
			return BY_NAME_MAPPER.get(value);
		}

		public static CourseLevel fromId(String value) {
			return BY_ID_MAPPER.get(value);
		}
	}

}
