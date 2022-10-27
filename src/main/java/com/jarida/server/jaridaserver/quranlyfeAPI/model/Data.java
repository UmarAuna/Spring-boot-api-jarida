package com.jarida.server.jaridaserver.quranlyfeAPI.model;

public class Data{

	private String image;

	private String title;
	private String message;
	private String intent;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setIntent(String intent){
		this.intent = intent;
	}

	public String getIntent(){
		return intent;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"image = '" + image + '\'' + 
			",title = '" + title + '\'' + 
			",message = '" + message + '\'' + 
			",intent = '" + intent + '\'' + 
			"}";
		}
}