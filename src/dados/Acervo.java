package dados;

import java.util.ArrayList;

public class Acervo {
	private ArrayList<Video> video;

	public boolean addVideo(Video v) {
		return video.add(v);
	}

	public ArrayList<Video> getVideo() {
		return video;
	}

	public void setVideo(ArrayList<Video> video) {
		this.video = video;
	}
}
