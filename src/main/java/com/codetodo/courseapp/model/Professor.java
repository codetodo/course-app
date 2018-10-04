package com.codetodo.courseapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Professor implements Serializable {

	private static final long serialVersionUID = 4608463887802423595L;

	private final Long id;
	private final String name;
	private final String email;

	public Professor(Builder builder) {
		id = builder.id;
		name = builder.name;
		email = builder.email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.email);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Professor other = (Professor) obj;
		return Objects.equals(this.email, other.email);
	}

	public static class Builder {
		private Long id;
		private String name;
		private String email;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Professor build() {
			return new Professor(this);
		}

	}

}
