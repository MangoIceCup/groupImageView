package com.example.localviewer.serverRalated;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Zone {
   public ImageAlbum [] imageAlbums;
    public static Zone getInstance(){
        Zone zone = new Zone();
        Path zonePath = Paths.get("/home/edward/uncensored/");
        File zoneFile = zonePath.toFile();
        File[] albumsFile = zoneFile.listFiles();
        ArrayList<ImageAlbum> imageAlbumArrayList = new ArrayList<>();
        for (int i = 0; i < albumsFile.length; i++) {
            if (albumsFile[i].isDirectory() && !albumsFile[i].getName().startsWith(".")) {
                ImageAlbum imageAlbum=new ImageAlbum();
                imageAlbum.id=i+1;
                imageAlbum.name=albumsFile[i].getName();
                File [] imageFilesOfAlbum=albumsFile[i].listFiles();
                Arrays.sort(imageFilesOfAlbum, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                ArrayList<Image> imageArrayList=new ArrayList<>();
                int j=0;
                for(j=0;j<imageFilesOfAlbum.length;j++){
                    //File of an album
                    File _file=imageFilesOfAlbum[j];
                    Image image=new Image();
                    image.url="http://192.168.1.33/bitch/"+imageAlbum.name+"/"+_file.getName();
                    imageArrayList.add(image);
                }
                imageAlbum.countOfImage=j;
                imageAlbum.images=imageArrayList.toArray(new Image[]{});
                imageAlbumArrayList.add(imageAlbum);
            }
        }
        zone.imageAlbums=imageAlbumArrayList.toArray(new ImageAlbum[]{});
        return zone;
    }
}
