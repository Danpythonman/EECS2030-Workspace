package model;

public class Monitor extends Follower {

	private int[] viewCounters;
	private int[] watchTimeCounters;
	private int[] maximumWatchTimes;

	// --------------- CONSTRUCTORS ---------------

	public Monitor(String name, int maximumNumberOfFollowedChannels) {
		super(name, maximumNumberOfFollowedChannels);

		viewCounters = new int[maximumNumberOfFollowedChannels];
		watchTimeCounters = new int[maximumNumberOfFollowedChannels];
		maximumWatchTimes = new int[maximumNumberOfFollowedChannels];
	}

	// --------------- ACCESSORS ---------------

	public String toString() {
		String monitorString = "Monitor " + this.name;

		if (this.numberOfFollowedChannels == 0) {
			monitorString += " follows no channels.";
		} else {

			monitorString += " follows [";

			for (int i = 0; i < this.numberOfFollowedChannels; i++) {

				monitorString += this.followedChannels[i].getChannelName();

				if (this.viewCounters[i] > 0) {
					monitorString += " {#views: " + this.viewCounters[i]
							+ ", max watch time: "+ this.maximumWatchTimes[i]
							+ ", avg watch time: "
							+ String.format("%.2f", ((double) this.watchTimeCounters[i]) / this.viewCounters[i])
							+ "}";
				}

				if (i < this.numberOfFollowedChannels - 1) {
					monitorString += ", ";
				}
			}
			monitorString += "].";
		}

		return monitorString;
	}

	// --------------- MUTATORS ---------------

	void updateStatistics(Channel channel, int watchTime) {
		int index = this.getIndexOfChannel(channel);

		this.viewCounters[index]++;
		this.watchTimeCounters[index] += watchTime;

		if (watchTime > this.maximumWatchTimes[index]) {
			this.maximumWatchTimes[index] = watchTime;
		}
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
			this.viewCounters[i] = viewCounters[i + 1];
			this.watchTimeCounters[i] = watchTimeCounters[i + 1];
			this.maximumWatchTimes[i] = maximumWatchTimes[i + 1];
		}

		this.numberOfFollowedChannels--;
		this.followedChannels[this.numberOfFollowedChannels] = null;
		this.viewCounters[this.numberOfFollowedChannels] = 0;
		this.watchTimeCounters[this.numberOfFollowedChannels] = 0;
		this.maximumWatchTimes[this.numberOfFollowedChannels] = 0;
	}

}
