package model;

public class Follower {

	protected String name;
	protected int maximumNumberOfFollowedChannels;
	protected Channel[] followedChannels;
	protected int numberOfFollowedChannels;

	// --------------- CONSTRUCTORS ---------------

	public Follower(String name, int maximumNumberOfFollowedChannels) {
		this.name = name;
		this.maximumNumberOfFollowedChannels = maximumNumberOfFollowedChannels;

		this.followedChannels = new Channel[maximumNumberOfFollowedChannels];
		this.numberOfFollowedChannels = 0;
	}

	// --------------- ACCESSORS ---------------

	protected String getName() {
		return this.name;
	}

	protected int getIndexOfChannel(Channel channel) {
		int indexOfSpecifiedChannel = -1;

		for (int i = 0; i < this.numberOfFollowedChannels && indexOfSpecifiedChannel == -1; i++) {
			if (this.followedChannels[i] == channel) {
				indexOfSpecifiedChannel = i;
			}
		}

		return indexOfSpecifiedChannel;
	}

	public String toString() {
		String followerString = this.name;

		if (this.numberOfFollowedChannels == 0) {
			followerString += " follows no channels";
		} else {
			followerString += " follows [";

			for (int i = 0; i < this.numberOfFollowedChannels; i++) {

				followerString += this.followedChannels[i].getChannelName();
				if (i < this.numberOfFollowedChannels - 1) {
					followerString += ", ";
				}
			}
			followerString += "]";
		}

		return followerString;
	}

	// --------------- MUTATORS ---------------

	void followChannel(Channel channel) {
		this.followedChannels[this.numberOfFollowedChannels] = channel;
		this.numberOfFollowedChannels++;
	}

	void unfollowChannel(Channel channel) {
		int index = -1;

		for (int i = 0; i < this.numberOfFollowedChannels && index == -1; i++) {
			if (this.followedChannels[i] == channel) {
				index = i;
			}
		}

		for (int i = index; i < this.numberOfFollowedChannels - 1; i++) {
			this.followedChannels[i] = followedChannels[i + 1];
		}

		this.numberOfFollowedChannels--;
		this.followedChannels[this.numberOfFollowedChannels] = null;
	}

}
