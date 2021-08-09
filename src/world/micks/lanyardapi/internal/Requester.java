package world.micks.lanyardapi.internal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import world.micks.lanyardapi.internal.annotations.NonNullValid;
import world.micks.lanyardapi.internal.annotations.Nullable;

public class Requester {
	
	private final Long id;
	public Long getId() { return id; }
	private JSONObject result;
	
	public Requester(Long id) { 
		this.id = id; 
		this.result = result();
	}
	
	protected String v1 = "https://lanyard.rest/v1/users/";
	protected String avatar = "cdn.discordapp.com/avatars/%id/%hash";
	
	@Nullable
	public JSONObject result() {
		
        try {
        	
        	HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(v1 + id)).build();
            HttpResponse<String> response;
        	
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return new JSONObject(response.body());
			
		} catch (IOException | InterruptedException e) { return null; }
        
	}
	
	private boolean exist() {
		if(result.getBoolean("success")) { return true; }
		return false;
	}
	
	@NonNullValid
	public boolean spotifyActive() {
		if(!exist()) return false;
		JSONObject data = result.getJSONObject("data");
		if(data.has("listening_to_spotify")) { return data.getBoolean("listening_to_spotify"); }
		return false;
	}
	
	@Nullable
	@NonNullValid
	public String spotifyTitle() {
		if(!spotifyActive()) return null;
		JSONObject data = result.getJSONObject("data");
		JSONObject spotify = data.getJSONObject("spotify");
		return spotify.getString("song");
	}
	
	@Nullable
	@NonNullValid
	public String spotifyAlbum() {
		if(!spotifyActive()) return null;
		JSONObject data = result.getJSONObject("data");
		JSONObject spotify = data.getJSONObject("spotify");
		return spotify.getString("album");
	}
	
	@Nullable
	@NonNullValid
	public String spotifyArtist() {
		if(!spotifyActive()) return null;
		JSONObject data = result.getJSONObject("data");
		JSONObject spotify = data.getJSONObject("spotify");
		return spotify.getString("artist");
	}
	
	@Nullable
	@NonNullValid
	public String spotifyAlbumArt() {
		if(!spotifyActive()) return null;
		JSONObject data = result.getJSONObject("data");
		JSONObject spotify = data.getJSONObject("spotify");
		return spotify.getString("album_art_url");
	}
	
	@NonNullValid
	public String getUsername() {
		JSONObject data = result.getJSONObject("data");
		JSONObject discordUser = data.getJSONObject("discord_user");
		return discordUser.getString("username");
	}
	
	@NonNullValid
	public String getFormattedDiscriminator() {
		JSONObject data = result.getJSONObject("data");
		JSONObject discordUser = data.getJSONObject("discord_user");
		return ("#" + discordUser.getString("discriminator"));
	}
	
	@NonNullValid
	public String getUserId() {
		JSONObject data = result.getJSONObject("data");
		JSONObject discordUser = data.getJSONObject("discord_user");
		return discordUser.getString("id");
	}
	
	@NonNullValid
	public int getDiscriminator() {
		JSONObject data = result.getJSONObject("data");
		JSONObject discordUser = data.getJSONObject("discord_user");
		return discordUser.getInt("discriminator");
	}
	
	@NonNullValid
	public String getAvatar() {
		JSONObject data = result.getJSONObject("data");
		JSONObject discordUser = data.getJSONObject("discord_user");
		/* yes, i could use String#format but look.. this works fine too right? exactly. */
		return avatar.replace("%id", getUserId())
				     .replace("%hash", discordUser.getString("avatar")
				             );
	}
	
}