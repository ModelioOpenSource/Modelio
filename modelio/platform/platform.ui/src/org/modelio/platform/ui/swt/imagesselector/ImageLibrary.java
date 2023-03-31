/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.platform.ui.swt.imagesselector;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.modelio.platform.ui.plugin.UI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The ImageLibrary represents the contents of the catalog of the Modelio Server images library.<br/>
 * The class does not deal with SWT images at all, only names and labels are managed.
 */
@objid ("761fa56a-ea86-4084-8059-737fca869d7a")
public class ImageLibrary {
    /**
     * Known categories
     */
    @objid ("9a7fd378-43ab-479d-b44c-fac65a6c8c4d")
    private List<String> categories = new ArrayList<>();

    /**
     * Binds an image file name with its label
     */
    @objid ("3a069c8d-09f1-458a-a4e6-d6da544271ff")
    private HashMap<String, String> labels = new HashMap<>();

    
    @mdl.prop
    @objid ("3959a1fe-695c-4459-abf5-908dee40be4c")
    private String name = "";

    @mdl.propgetter
    public String getName() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.name;
    }

    @mdl.propsetter
    public void setName(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.name = value;
    }

    /**
     * Binds a category with its image file name
     */
    @objid ("53ae47df-60d6-4be6-ada8-1ef91bb117f3")
    private HashMap<Object, List<String>> images = new HashMap<>();

    /**
     * The base URL of the library - up to the directory that contains the images and the catalog.xml file eg: http://constellation.minotaure.softeam.com/library/imagelibrary/standard/
     */
    @objid ("73e6a670-c179-49c6-87c9-d2255178b968")
    private URI repository;

    /**
     * @param repository The base URL of the library - up to the directory that contains the images and the catalog.xml file eg: http://constellation.minotaure.softeam.com/library/imagelibrary/standard/
     */
    @objid ("ee335636-6604-4f7f-99c4-2505abe08ee8")
    public  ImageLibrary(URI repository) {
        this.repository = repository;
        new CatalogParser().loadLibrary(this, repository);
        
    }

    @objid ("ca30889d-b0ad-47c1-ac77-dac692d4d82b")
    private void addImage(String category, String imageFilename, String imageLabel) {
        if (!this.categories.contains(category)) {
            this.categories.add(category);
            this.images.put(category, new ArrayList<String>());
        }
        this.images.get(category).add(imageFilename);
        this.labels.put(imageFilename, imageLabel);
        
    }

    /**
     * @return the categories of this library
     */
    @objid ("23ddb6a8-9a94-4752-96c0-8c400bcbe9df")
    public List<String> getCategories() {
        return this.categories;
    }

    /**
     * @return the images of the category in the library. Images are returned as a list of identifiers (String)
     */
    @objid ("a4dfaf13-dc94-445a-84ab-c9fa99623fb5")
    public List<String> getImages(String category) {
        return this.images.getOrDefault(category, Collections.EMPTY_LIST);
    }

    @objid ("6f9ce7e7-8410-42bd-ba18-8f679d101e66")
    public String getLabel(String imageFile) {
        return this.labels.getOrDefault(imageFile, imageFile);
    }

    /**
     * @return the url of the image in the repository
     */
    @objid ("3dfcb95f-c557-4aa5-90f2-3a0f2ea97cf4")
    public URL getImage(String image) {
        try {
            return new URL(this.repository.toString() + '/' + image);
        } catch (MalformedURLException e) {
            return null;
        }
        
    }

    /**
     * @return the url of the image 64x64 thumbnail in the repository
     */
    @objid ("738334f8-0a3c-4a75-827e-afd9fdc83a7a")
    public URL getImageThumbnail(String image) {
        try {
            return new URL(this.repository.toString() + '/' + image.replaceAll(".png", "_64.png"));
        } catch (MalformedURLException e) {
            return null;
        }
        
    }

    @objid ("1bf4b851-8adc-4c4e-92f3-8ac4ed4ea3fe")
    private static class CatalogParser {
        @objid ("aa71837c-8992-4b3a-93e2-84f459cb8d7e")
        public void loadLibrary(ImageLibrary imageLibrary, URI repository) {
            try {
                URL catalogURL = new URL(repository.toString() + "/catalog.xml");
                try (InputStream xmlStream = catalogURL.openConnection().getInputStream()) {
                    // Create a DocumentBuilderFactory
                    final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    // Create a DocumentBuilder
                    final DocumentBuilder db = dbf.newDocumentBuilder();
                    // Parse
                    // db.setErrorHandler(parserAPIUsage);
                    final Document xmlDoc = db.parse(xmlStream);
                    final Element rootElement = xmlDoc.getDocumentElement();
                    parseLibrary(imageLibrary, rootElement);
                    xmlStream.close();
                } catch (final ParserConfigurationException | SAXException | IOException e) {
                    throw e;
                }
            } catch (Exception e) {
                UI.LOG.error(e);
            }
            
        }

        @objid ("0f3dae6f-5f3f-436f-b293-16da2def4538")
        private void parseLibrary(ImageLibrary imageLibrary, Element rootElement) {
            imageLibrary.setName(rootElement.getAttribute("name"));
            
            // Explore nodes under the root node:
            final NodeList childNodes = rootElement.getElementsByTagName("category");
            final int nNodes = childNodes.getLength();
            for (int i = 0; i < nNodes; i++) {
                Element categoryElement = (Element) childNodes.item(i);
                List<String> images = parseCategory(categoryElement);
            
                for (int j = 0; j < images.size(); j += 2) {
                    imageLibrary.addImage(categoryElement.getAttribute("name"), images.get(j), images.get(j + 1));
                }
            }
            
        }

        /**
         * @return A list of positional 'pairs' [imageFile1, imagelabel2, imageFile2, imageLabel2 ...]
         */
        @objid ("ea8f5c9c-2f44-4a81-a438-a49fd6988f20")
        private List<String> parseCategory(Element categoryElement) {
            List<String> filenames = new ArrayList<>();
            final NodeList childNodes = categoryElement.getElementsByTagName("image");
            final int nNodes = childNodes.getLength();
            for (int i = 0; i < nNodes; i++) {
                Element imageElement = (Element) childNodes.item(i);
                String filename = imageElement.getAttribute("file");
                filenames.add(filename);
                filenames.add(parseLabel(imageElement));
            }
            return filenames;
        }

        @objid ("7f059acc-573b-4e79-b0b6-881ad9f10cd6")
        private String parseLabel(Element imageElement) {
            String label = null;
            String defaultLabel = imageElement.getAttribute("file");
            String targetLang = Locale.getDefault().getLanguage();
            
            final NodeList childNodes = imageElement.getElementsByTagName("label");
            final int nNodes = childNodes.getLength();
            for (int i = 0; i < nNodes; i++) {
                Element labelElement = (Element) childNodes.item(i);
                String lang = labelElement.getAttribute("lang");
                if (Objects.equals(targetLang, lang)) {
                    label = labelElement.getTextContent().trim();
                }
                if (Objects.equals(targetLang, "default")) {
                    defaultLabel = labelElement.getTextContent().trim();
                }
            }
            return label != null ? label : defaultLabel;
        }

    }

}
