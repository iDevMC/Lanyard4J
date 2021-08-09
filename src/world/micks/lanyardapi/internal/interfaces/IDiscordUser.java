package world.micks.lanyardapi.internal.interfaces;

import world.micks.lanyardapi.internal.annotations.NonNullValid;
import world.micks.lanyardapi.internal.annotations.Nullable;

public interface IDiscordUser {
	
	boolean exist();
	@NonNullValid String username();
	@NonNullValid String formattedDiscriminator();
	@NonNullValid Integer discriminator();
	@NonNullValid Long id();
	@NonNullValid String avatar();
	
	boolean hasSpotifyActive();
	@Nullable String getSpotifySong();
	@Nullable String getSpotifyAlbum();
	@Nullable String getSpotifyAlbumArt();
	@Nullable String getSpotifyArtist();
	
}