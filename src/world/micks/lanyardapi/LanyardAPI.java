package world.micks.lanyardapi;

import world.micks.lanyardapi.internal.Requester;
import world.micks.lanyardapi.internal.interfaces.IDiscordUser;

public class LanyardAPI {
	
	public static IDiscordUser getDiscordUser(Long id) {
		
		Requester request = new Requester(id);
		
		return new IDiscordUser() {
			
			@Override
			public String username() {
				return request.getUsername();
			}
			
			@Override
			public Long id() {
				return request.getId();
			}
			
			@Override
			public boolean hasSpotifyActive() {
				return request.spotifyActive();
			}
			
			@Override
			public String getSpotifySong() {
				return request.spotifyTitle();
			}
			
			@Override
			public String getSpotifyArtist() {
				return request.spotifyArtist();
			}
			
			@Override
			public String getSpotifyAlbumArt() {
				return request.spotifyAlbumArt();
			}
			
			@Override
			public String getSpotifyAlbum() {
				return request.spotifyAlbum();
			}
			
			@Override
			public String formattedDiscriminator() {
				return request.getFormattedDiscriminator();
			}
			
			@Override
			public boolean exist() {
				return request.result().getBoolean("success");
			}
			
			@Override
			public Integer discriminator() {
				return request.getDiscriminator();
			}
			
			@Override
			public String avatar() {
				return request.getAvatar();
			}
		};
		
	}
	
}