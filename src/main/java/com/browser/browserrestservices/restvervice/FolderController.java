package com.browser.browserrestservices.restvervice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class FolderController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/folders")
    public List<Folder> getTodoLists(@RequestParam(value = "name",defaultValue = "") String name) {

        List<Folder> folders = new ArrayList<Folder>();

        File f;

        //if there is parameter API will list selected folder data
        if(name.equals("")){
            f = new File(System.getProperty("user.home"));

        }
        else{
            f = new File(name);
        }
        String parentFolder = f.getParent();
        String absolutePath = f.getAbsolutePath();

        //absolutePath
        folders.add(new Folder(absolutePath,0));
        //parentFolder Path
        folders.add(new Folder(parentFolder,0));


        // For each pathname in the pathnames array
        for (File file : Objects.requireNonNull(f.listFiles())) {


            //do not add hidden files do not calculate folder sizes
            if(file.isDirectory() && file.getName().charAt(0) != '.' ){
                folders.add(new Folder( file.getName() , 0));
            }


            //do not add hidden files
            if(file.isFile() && file.getName().charAt(0) != '.' ){
                folders.add(new Folder( file.getName(),  file.length() + 1 ));
            }
        }

        return folders;
    }
}
