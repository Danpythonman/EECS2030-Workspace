package model;

public class Subscriber extends Follower {

	private int maximumNumberOfRecommendedVideos;
	private String[] recommendedVideos;
	private int numberOfRecommendedVideos;

	// --------------- CONSTRUCTORS ---------------

	public Subscriber(String name, int maximumNumberOfFollowedChannels, int maximumNumberOfRecommendedVideos) {
		super(name, maximumNumberOfFollowedChannels);

		this.maximumNumberOfRecommendedVideos = maximumNumberOfRecommendedVideos;

		this.recommendedVideos = new String[this.maximumNumberOfRecommendedVideos];
		this.numberOfRecommendedVideos = 0;
	}

	// --------------- ACCESSORS ---------------

	public String toString() {
		String subscriberString = "Subscriber " + super.toString();

		if (this.numberOfRecommendedVideos == 0) {
			subscriberString += " and has no recommended videos.";
		} else {
			subscriberString += " and is recommended <";
	
			for (int i = 0; i < this.numberOfRecommendedVideos; i++) {
	
				subscriberString += this.recommendedVideos[i];
				if (i < this.numberOfRecommendedVideos - 1) {
					subscriberString += ", ";
				}
			}
			subscriberString += ">.";
		}

		return subscriberString;
	}

	// --------------- MUTATORS ---------------

	void recommendVideo(String video) {
		this.recommendedVideos[this.numberOfRecommendedVideos] = video;
		this.numberOfRecommendedVideos++;
	}

	public void watch(String video, int watchTime) {
		Channel watchedChannel = null;
		String[] channelVideos;

		for (int i = 0; i < this.numberOfFollowedChannels && watchedChannel == null; i++) {
			channelVideos = this.followedChannels[i].getVideos();

			for (int j = 0; j < channelVideos.length && watchedChannel == null; j++) {
				if (channelVideos[j] == video) {
					watchedChannel = this.followedChannels[i];
				}
			}
		}

		watchedChannel.updateMonitors(watchTime);
	}

}
