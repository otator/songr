package com.example.songr.repository;

import com.example.songr.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    public static final String QUERY = "Select * from song where album_id =?1 ";
    @Query(value = QUERY, nativeQuery = true)
    public List<Song> getSongs(Long albumId);

    public static final String QUERY2 = "delete * from song where album_id =?1 ";
    @Query(value = QUERY, nativeQuery = true)
    public void deleteSongs(Long albumId);
}
