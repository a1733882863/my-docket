package com.domain;

import java.util.HashSet;
import java.util.Set;

public class Favorite {
	private int id;
	private int number;
	private User user;
	private Set<FavoriteItem> favoriteItems = new HashSet<FavoriteItem>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<FavoriteItem> getFavoriteItems() {
		return favoriteItems;
	}
	public void setFavoriteItems(Set<FavoriteItem> favoriteItems) {
		this.favoriteItems = favoriteItems;
	}
	
	
}
