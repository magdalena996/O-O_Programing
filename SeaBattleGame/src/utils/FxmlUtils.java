package utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

import static controllers.OptionsController.soundsEffectsStatus;

public class FxmlUtils {

    public static MediaPlayer musicPlayer;
    public MediaPlayer soundsPlayer;
    public MediaPlayer alertPlayer;

    public static void musicLoader() {
        //Media media = new Media(new File("src/utils/sounds/Ocean_Waves.mp3").toURI().toString());
        Media media = new Media(new File("src/utils/sounds/BeachWaves.mp3").toURI().toString());
        musicPlayer = new MediaPlayer(media);
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
        musicPlayer.setVolume(0.3);
        musicPlayer.play();
    }

    public void soundsEffectsLoader(String nameOfSound) {
        if (nameOfSound.equals("splash")) {
            Media media = new Media(new File("src/utils/sounds/Video_Game_Splash.mp3").toURI().toString());
            soundsPlayer = new MediaPlayer(media);
            soundsPlayer.play();
            soundsPlayer.setVolume(0.5);
        } else if (nameOfSound.equals("destroy")) {
            Media media = new Media(new File("src/utils/sounds/Grenade_Explosion.mp3").toURI().toString());
            soundsPlayer = new MediaPlayer(media);
            soundsPlayer.play();
            soundsPlayer.setVolume(0.5);
        } else if (nameOfSound.equals("applause")) {
            Media media = new Media(new File("src/utils/sounds/Audience_Applause.mp3").toURI().toString());
            soundsPlayer = new MediaPlayer(media);
            soundsPlayer.play();
            soundsPlayer.setVolume(0.5);
        } else if (nameOfSound.equals("boo")) {
            Media media = new Media(new File("src/utils/sounds/CrowdBoo.mp3").toURI().toString());
            soundsPlayer = new MediaPlayer(media);
            soundsPlayer.play();
            soundsPlayer.setVolume(0.5);
        }
    }

    public void alertsSoundsLoader() {
        Media media = new Media(new File("src/utils/sounds/Computer_Error.mp3").toURI().toString());
        alertPlayer = new MediaPlayer(media);
        alertPlayer.play();
    }

    public void getSoundsEffects(String effectName){
        if (soundsEffectsStatus) soundsEffectsLoader(effectName);
    }

}
