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

package org.modelio.app.project.core.update;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * A possible update from the update site.
 */
@objid ("ec5b3ecc-6f4b-4f02-9250-bc751faf0529")
public class UpdateDescriptor {
    @objid ("24909880-f9fe-4be9-bc66-2a81667fa187")
    private String currentVersion;

    @objid ("6b22e3ee-03b9-416f-b4b2-cda3aced5016")
    private String documentationLink;

    @objid ("83d55a85-612d-4c87-8cd8-2b9176b8e54e")
    private String downloadLink;

    @objid ("a7427666-4b0a-4362-be24-01216f2c3de5")
    private String id;

    @objid ("8c1d113d-0ba9-4e47-bfbc-95d35b2b2fef")
    private String label;

    @objid ("cb61c8fe-5d36-4d30-b038-7394ff1e3078")
    private String newVersion;

    /**
     * Constructor initializing all fields.
     * @param id the item's identifier.
     * @param label the item's label.
     * @param currentVersion the current version of the item.
     * @param newVersion the latest version available on the update site for this item.
     * @param documentationLink a documentation web page link.
     * @param downloadLink the link to download the physical item.
     */
    @objid ("bc206c0a-b5fa-4647-868f-f97694094398")
    public UpdateDescriptor(String id, String label, String currentVersion, String newVersion, String documentationLink, String downloadLink) {
        super();
        this.id = id;
        this.label = label;
        this.currentVersion = currentVersion;
        this.newVersion = newVersion;
        this.documentationLink = documentationLink;
        this.downloadLink = downloadLink;
    }

    /**
     * @return the current version of the item.
     */
    @objid ("085ff909-4421-442e-8d46-cfbdc35f9e37")
    public String getCurrentVersion() {
        return this.currentVersion;
    }

    /**
     * @return a documentation web page link.
     */
    @objid ("b7902f65-26c7-476d-819d-336b28f436f4")
    public String getDocumentationLink() {
        return this.documentationLink;
    }

    /**
     * @return the link to download the physical item.
     */
    @objid ("0f42091c-9025-4087-993a-a2413c8bf5e0")
    public String getDownloadLink() {
        return this.downloadLink;
    }

    /**
     * @return the item's identifier.
     */
    @objid ("b141f1ff-d099-4ccd-8a57-47ce0992bbc4")
    public String getId() {
        return this.id;
    }

    /**
     * @return the item's label.
     */
    @objid ("7963e7f0-c9f1-4515-9733-74d992126063")
    public String getLabel() {
        return this.label;
    }

    /**
     * @return the latest version available on the update site for this item.
     */
    @objid ("6ab2e1b8-9840-4447-9f70-f468b129e298")
    public String getNewVersion() {
        return this.newVersion;
    }

    @objid ("e8a63e06-7bb1-4d33-9d18-90dc90e3c973")
    @Override
    public String toString() {
        return this.id + " " + this.currentVersion + "->" + this.newVersion;
    }

}
