package com.example.songr.control;

import com.example.songr.model.Album;
import com.example.songr.model.Song;
import com.example.songr.repository.AlbumRepository;
import com.example.songr.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;

@Controller
public class SongController {

    @Autowired
    SongRepository songRepository;

    @Autowired
    AlbumRepository albumRepository;
    @GetMapping("/songs")
    public String getSongs(Model model){
        try {
            model.addAttribute("songs", songRepository.findAll());
            return "song.html";
        }catch (Exception ex){
            return "error.html";
        }
    }

    @GetMapping("/song-details/{id}")
    public String getSongDetails(Model model, @PathVariable(value = "id") Long id){
        try {
            model.addAttribute("songDetails", songRepository.findById(id).get());
            return "song-details.html";
        }catch (Exception ex){
            return "error.html";
        }
    }

    @PostMapping("/delete-song/{id}")
    public RedirectView deleteSong(@PathVariable(name = "id") Long id){
        try {
            songRepository.deleteById(id);
            return new RedirectView("/songs");
        }catch (Exception ex){
            return new RedirectView("/error");
        }
    }

    @PostMapping("/edit-song/{id}")
    public RedirectView editSong(String title, Integer length, Integer trackNumber,
                                 @PathVariable(name="id") Long id){
        try {
            Song song = songRepository.findById(id).get();
            song.setTitle(title);
            song.setLength(length);
            song.setTrackNumber(trackNumber);
            songRepository.save(song);
            return new RedirectView("/song-details/" + id);
        }catch (Exception ex){
            return new RedirectView("/error");
        }
    }

    @GetMapping("/add-song")
    public String getSongForm(){
        return "add-song.html";
    }

    @PostMapping("/song")
    public RedirectView addSong(String title, Integer length, Integer trackNumber, Long albumId){
        try {
            Album album = albumRepository.findById(albumId).get();
            Song song = new Song(title, length, trackNumber, album);
            songRepository.save(song);
            return new RedirectView("/songs");
        }catch (Exception ex){
            return new RedirectView("/error");
        }
    }
}
