/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.project.conf.dialog.urls;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.conf.plugin.AppProjectConf;
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
@objid ("f91f50db-f86f-4dc8-84dd-628569cb0671")
public class PropertiesUrlAdapter {
    @objid ("96ca1cb4-ed8a-441f-8879-b4f4a37625ae")
    private static final String prefix = "info.page.";

    @objid ("ba256cb1-b7bc-4f0e-bcc2-40fd058af543")
    private final GProperties properties;

    @objid ("1b420550-0603-4431-ba17-d4c8ffee7049")
    private final List<UrlEntry> entries;

    /**
     * C'tor.
     * @param gProperties the properties to display
     */
    @objid ("d3df8b55-3e95-4dc8-9f0e-3ebe07c00c2d")
    public PropertiesUrlAdapter(GProperties gProperties) {
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
    @objid ("f191d005-6b74-44d1-b0d3-d8e2d8289ef5")
    public void addUrlEntry(UrlEntry entry) {
        this.entries.add(entry);
        applyChanges();
    }

    /**
     * Remove an URL entry.
     * @param entry the entry to remove
     */
    @objid ("359ae67c-6e98-4378-a814-2229e32d182d")
    public void removeUrlEntry(UrlEntry entry) {
        this.entries.remove(entry);
        applyChanges();
    }

    /**
     * @return the URLS
     */
    @objid ("fb70be9b-7006-4f60-a0e8-1a5f099af56a")
    public List<UrlEntry> getUrls() {
        return this.entries;
    }

    /**
     * The applyChanges() method will update the project properties with the
     * current values from the ProjectUrlAdapter 'entries' table.
     */
    @objid ("b076713d-b350-4124-b485-ed74f24427ea")
    private void applyChanges() {
        AppProjectConf.LOG.debug("applyChanges ");
        
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

    @objid ("0a2ba9ad-f838-4096-a0ac-7ecf63be24dc")
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
