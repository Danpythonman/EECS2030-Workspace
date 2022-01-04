package model;

public class Channel {

	private String name;
	private int maximumNumberOfFollowers;
	private int maximumNumberOfVideos;
	private Follower[] followers;
	private int numberOfFollowers;
	private String[] videos;
	private int numberOfVideos;

	// --------------- CONSTRUCTORS ---------------

	public Channel(String name, int maximumNumberOfFollowers, int maximumNumberOfVideos) {
		this.name = name;
		this.maximumNumberOfFollowers = maximumNumberOfFollowers;
		this.maximumNumberOfVideos = maximumNumberOfVideos;

		this.followers = new Follower[this.maximumNumberOfFollowers];
		this.numberOfFollowers = 0;
		this.videos = new String[this.maximumNumberOfVideos];
		this.numberOfVideos = 0;
	}

	// --------------- ACCESSORS ---------------

	public String getChannelName() {
		return this.name;
	}

	String[] getVideos() {
		String[] nonNullVideos = new String[this.numberOfVideos];

		for (int i = 0; i < this.numberOfVideos; i++) {
			nonNullVideos[i] = this.videos[i];
		}

		return nonNullVideos;
	}

	public String toString() {
		String channelString = this.name;

		if (this.numberOfVideos == 0) {
			channelString += " released no videos";
		} else {
			channelString += " released <";

			for (int i = 0; i < this.numberOfVideos; i++) {

				channelString += this.videos[i];
				if (i < this.numberOfVideos - 1) {
					channelString += ", ";
				}
			}
			channelString += ">";
		}

		if (this.numberOfFollowers == 0) {
			channelString += " and has no followers.";
		} else {
			channelString += " and is followed by [";

			for (int i = 0; i < this.numberOfFollowers; i++) {

				if (this.followers[i] instanceof Subscriber) {
					channelString += "Subscriber ";
				} else if (this.followers[i] instanceof Monitor) {
					channelString += "Monitor ";
				}

				channelString += this.followers[i].getName();
				if (i < this.numberOfFollowers - 1) {
					channelString += ", ";
				}
			}
			channelString += "].";
		}

		return channelString;
	}


	// --------------- MUTATORS ---------------

	public void releaseANewVideo(String videoName) {
		this.videos[this.numberOfVideos] = videoName;
		this.numberOfVideos++;

		for (int i = 0; i < this.numberOfFollowers; i++) {
			if (this.followers[i] instanceof Subscriber) {
				((Subscriber) this.followers[i]).recommendVideo(videoName);
			}
		}
	}

	public void follow(Follower follower) {
		this.followers[this.numberOfFollowers] = follower;
		this.numberOfFollowers++;

		follower.followChannel(this);
	}

	public void unfollow(Follower follower) {
		int index = -1;

		for (int i = 0; i < this.numberOfFollowers && index == -1; i++) {
			if (this.followers[i] == follower) {
				index = i;
			}
		}

		if (index > -1) {
			for (int i = index; i < this.numberOfFollowers - 1; i++) {
				this.followers[i] = followers[i + 1];
			}
	
			this.numberOfFollowers--;
			this.followers[this.numberOfFollowers] = null;
	
			follower.unfollowChannel(this);
		}
	}

	void updateMonitors(int watchTime) {
		for (int i = 0; i < this.numberOfFollowers; i++) {
			if (this.followers[i] instanceof Monitor) {
				((Monitor) this.followers[i]).updateStatistics(this, watchTime);
			}
		}
	}

}
