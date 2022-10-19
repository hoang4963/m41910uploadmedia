package com.codegym.controller;

import com.codegym.model.CheckFileMp3;
import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import com.codegym.service.SongService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {
    @Value("${file-upload}")
    private String fileUpload;

    private final ISongService songService = new SongService();

    @GetMapping("")
    public String index(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        return "/index";
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("songForm", new SongForm());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute SongForm songForm) {
        MultipartFile multipartFile = songForm.getFile();
        String fileName = multipartFile.getOriginalFilename();
        ModelAndView modelAndView;
        if (CheckFileMp3.checkFile(fileName)){
            try {
                FileCopyUtils.copy(songForm.getFile().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Song song = new Song(songForm.getId(), songForm.getName(),
                    songForm.getAuthor(), songForm.getCategory(), fileName);
            songService.save(song);
            modelAndView = new ModelAndView("/create");
            modelAndView.addObject("songForm", songForm);
            modelAndView.addObject("message", "Successfully");
        }else {
            modelAndView = new ModelAndView("/create");
            modelAndView.addObject("songForm", songForm);
            modelAndView.addObject("message", "This is not a song");
        }
        return modelAndView;
    }
}
