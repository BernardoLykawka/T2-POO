package dados;

import java.util.ArrayList;

public class Acervo {
	private ArrayList<Video> video;

	public Acervo() {
		video = new ArrayList<>();
	}

	public boolean addVideo(dados.Video v) {
		return video.add(v);
	}

	public ArrayList<Video> getVideo() {
		return (ArrayList<Video>) video.clone();
	}

	public void setVideo(ArrayList<Video> video) {
		this.video = video;
	}
}
