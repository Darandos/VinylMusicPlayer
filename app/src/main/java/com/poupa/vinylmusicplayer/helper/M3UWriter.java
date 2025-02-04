package com.poupa.vinylmusicplayer.helper;

import android.content.Context;

import androidx.annotation.NonNull;

import com.poupa.vinylmusicplayer.discog.tagging.MultiValuesTagUtil;
import com.poupa.vinylmusicplayer.model.Playlist;
import com.poupa.vinylmusicplayer.model.Song;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class M3UWriter implements M3UConstants {

    public static void write(@NonNull final Context context, @NonNull final OutputStream stream, @NonNull final Playlist playlist) throws IOException {
        ArrayList<Song> songs = playlist.getSongs(context);
        if (songs.size() > 0) {
            stream.write(HEADER.getBytes(StandardCharsets.UTF_8));

            for (Song song : songs) {
                String line = System.lineSeparator() +
                        ENTRY +
                        song.duration +
                        DURATION_SEPARATOR +
                        MultiValuesTagUtil.infoString(song.artistNames) +
                        " - " +
                        song.title +
                        System.lineSeparator() +
                        song.data;

                stream.write(line.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
