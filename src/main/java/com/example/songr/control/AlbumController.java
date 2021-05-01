package com.example.songr.control;

import com.example.songr.model.Album;
import com.example.songr.repository.AlbumRepository;
import com.example.songr.repository.SongRepository;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @GetMapping("/album")
    public String getAlbums(Model model){
        model.addAttribute("albums", albumRepository.findAll());
        return "album.html";
    }

    @GetMapping("/album-details/{id}")
    public String getAlbumsDetails(Model model, @PathVariable(value = "id") Long id){
        try {
            model.addAttribute("albumDetails", albumRepository.findById(id).get());
            model.addAttribute("songs", songRepository.getSongs(id));
            return "album-details.html";
        }catch (Exception ex){
            return "error.html";
        }
    }

    @GetMapping("/add-album")
    public String getAlbumForm(){
        return "add-album.html";
    }

    @PostMapping("/album")
    public RedirectView addAlbum(@RequestParam(value = "title") String title,
                                 @RequestParam(value = "artist") String artist,
                                 @RequestParam(value = "length") int length,
                                 @RequestParam(value = "songCount") int songCount,
                                 @RequestParam(value = "imageUrl") String imageUrl){
        Album album = new Album(title, artist, songCount, length, imageUrl);
        try {
            albumRepository.save(album);
            return new RedirectView("/album");
        }catch (Exception ex){
            return new RedirectView("/error");
        }
    }

    @OnDelete(action = OnDeleteAction.CASCADE)
    @PostMapping("/delete-album/{id}")
    public RedirectView deleteAlbum(@PathVariable(value = "id") Long id){
        try {
            albumRepository.deleteById(id);
            return new RedirectView("/album");
        }catch (Exception ex){
            return new RedirectView("/error");
        }
    }

    @PostMapping("/edit-album/{id}")
    public RedirectView editAlbum(String title, String artist, Integer songCount, Integer length, String imageUrl,
                                  @PathVariable(value = "id") Long id){
        try {
            Album album = albumRepository.findById(id).get();
            album.setTitle(title);
            album.setArtist(artist);
            album.setSongCount(songCount);
            album.setLength(length);
            album.setImageUrl(imageUrl);
            albumRepository.save(album);

            return new RedirectView("/album-details/" + id);
        }catch (Exception ex){
            return new RedirectView("/error");
        }
    }

}
