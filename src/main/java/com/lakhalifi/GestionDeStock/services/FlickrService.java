package com.lakhalifi.GestionDeStock.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface FlickrService { //ou bien on le nomme: photo service

    String savePhoto(InputStream photo, String title) throws FlickrException;

}
