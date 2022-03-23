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
package org.modelio.app.project.ui.views.urls;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProperties;

/**
 * This class is an adapter between a GProperties (from a project or a project
 * descriptor) and a list of URLs
 * 
 * The url of a GProject/ProjectDescriptor are defined in the project properties
 * using special
 * property keys in the form of "info.page.nnn" where nnn is an integer from 0
 * to the number of defined urls (minus one). The <i>ProjectUrlAdapter</i>
 * adapter guarantees the property keys consistency when adding or removing url
 * to/from the project.
 * 
 * @author phv
 */
@objid ("1914be43-157f-4f39-8295-043384de2568")
public class PropertiesUrlAdapter {
    @objid ("e6c5bd6a-acc8-4277-98eb-9e7d215b48ff")
    private static final String prefix = "info.page.";

    @objid ("b12931f6-db01-4ef6-9e63-adff35901b9c")
    private final GProperties properties;

    @objid ("61c93154-84a2-42ec-a389-1fc38001e149")
    private final List<UrlEntry> entries;

    /**
     * C'tor.
     * @param gProperties the properties to display
     */
    @objid ("d1c0a705-515b-4539-979c-7d1e42b4ef7f")
    public  PropertiesUrlAdapter(GProperties gProperties) {
        this.properties = gProperties;
        
        // Populate the 'entries' cache
        this.entries = new ArrayList<>();
        if (this.properties != null) {
            int index = 0;
            String def = gProperties.getValue(prefix + Integer.toString(index));
            while (def != null) {
                UrlEntry urlentry = parseDefinition(def);
                this.entries.add(urlentry);
                index++;
                def = this.properties.getValue(prefix + Integer.toString(index));
            }
        }
        
    }

    /**
     * Add an URL entry.
     * @param entry the entry to add.
     */
    @objid ("f9a4d13d-d415-4678-b31b-a5a31d7cae41")
    public void addUrlEntry(UrlEntry entry) {
        this.entries.add(entry);
        applyChanges();
        
    }

    /**
     * Remove an URL entry.
     * @param entry the entry to remove
     */
    @objid ("3feb19d0-33ae-4112-bf77-adf7b9095e2c")
    public void removeUrlEntry(UrlEntry entry) {
        this.entries.remove(entry);
        applyChanges();
        
    }

    /**
     * @return the URLS
     */
    @objid ("bc180a10-1164-4ff8-95e0-bd527b470231")
    public List<UrlEntry> getUrls() {
        return this.entries;
    }

    /**
     * The applyChanges() method will update the project properties with the
     * current values from the ProjectUrlAdapter 'entries' table.
     */
    @objid ("d5d096be-b9ec-42e4-b81f-29e69ba4d9fa")
    private void applyChanges() {
        AppProjectUi.LOG.debug("applyChanges ");
        
        if (this.properties == null) {
            return;
        }
        
        // Delete the current properties
        int index = 0;
        String def = this.properties.getValue(prefix + Integer.toString(index));
        while (def != null) {
            this.properties.remove(prefix + Integer.toString(index));
            index++;
            def = this.properties.getValue(prefix + Integer.toString(index));
        }
        
        // Add the new ones
        index = 0;
        for (UrlEntry entry : this.entries) {
            String key = prefix + Integer.toString(index);
            this.properties.setProperty(key, entry.name + "=" + entry.url, DefinitionScope.LOCAL);
            index++;
        }
        
    }

    @objid ("febd755a-7707-448c-9d74-2668bc7b9a46")
    private static UrlEntry parseDefinition(String def) {
        UrlEntry urlEntry = new UrlEntry();
        
        String[] tokens = def.split("=", 2); //def is "name=url"
        
        if (tokens.length == 2) {
            urlEntry.name = tokens[0].trim();
            urlEntry.url = tokens[1].trim();
        }
        return urlEntry;
    }

}
