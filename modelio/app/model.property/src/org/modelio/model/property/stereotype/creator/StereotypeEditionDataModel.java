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

package org.modelio.model.property.stereotype.creator;

import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("81efb0e0-c64f-41dd-925d-84f74ce8b57c")
class StereotypeEditionDataModel {
    @objid ("80dbe471-2daa-439a-8d96-b8a78320c716")
    private String stereotypeName = "";

    @objid ("3b2091e5-9f99-4530-9a77-0ac898c2c002")
    private String metaclassName = "";

    @objid ("9a3af15f-c6b8-4113-b901-888555df75d4")
    private String diagramImage = "";

    @objid ("db64c24b-f4df-4d4c-bcb1-076d14f062ac")
    private String explorerIcon = "";

    @objid ("7f526267-829c-4705-8288-7688d63da681")
    private boolean applyStereotype = false;

    @objid ("0c934500-e7b5-4b13-80c5-5801caf2b2e8")
    private String iconName = "";

    @objid ("8cfce3b1-7919-4323-a4f9-922dff5b54fc")
    private String imageName = "";

    @objid ("d2cd6e61-8c49-4041-bd8d-0d16a9a4c942")
    private Stereotype editedStereotype;

    @objid ("f313f9ec-a377-4d70-a02d-47b6fdb401dc")
    private Path projectPath;

    /**
     * Set the stereotype name.
     * @param stereotypeName the stereotype name to set
     */
    @objid ("33efaed3-492f-42f2-905c-865cbf657dc7")
    public void setStereotypeName(String stereotypeName) {
        this.stereotypeName = stereotypeName;
    }

    /**
     * Get the stereotype name.
     * @return the stereotype name
     */
    @objid ("668a1b70-ed00-473b-81b1-0b6d13cb8a26")
    public String getStereotypeName() {
        return this.stereotypeName;
    }

    /**
     * Default constructor.
     * @param metaclassName the metaclass the stereotype should apply to.
     * @param editedStereotype <code>true</code> if this model is used to create a new stereotype, <code>false</code> to edit an existing stereotype.
     * @param projectPath the path of the project to create the stereotype in.
     */
    @objid ("ffef2852-2f3e-4736-9031-ad10221d467c")
    public StereotypeEditionDataModel(String metaclassName, Stereotype editedStereotype, Path projectPath) {
        super();
        this.metaclassName = metaclassName;
        this.editedStereotype = editedStereotype;
        this.projectPath = projectPath;
        if (editedStereotype != null) {
            this.iconName = editedStereotype.getIcon();
            this.imageName = editedStereotype.getImage();
        }
    }

    /**
     * Set the metaclass name.
     * @param metaclassName the metaclassName to set
     */
    @objid ("d3fc0d4f-4f69-4759-975a-40abb6ba8b96")
    public void setMetaclassName(String metaclassName) {
        this.metaclassName = metaclassName;
    }

    /**
     * Get the metaclass name.
     * @return the metaclassName
     */
    @objid ("a6b2927a-4451-40da-94e9-a7eb28e989a2")
    public String getMetaclassName() {
        return this.metaclassName;
    }

    /**
     * @param diagramImage the diagramImage to set
     */
    @objid ("481e1060-5934-454e-b4bf-d9739cc61a90")
    public void setDiagramImage(String diagramImage) {
        this.diagramImage = diagramImage;
    }

    /**
     * @return the diagramImage
     */
    @objid ("42798417-8942-47d8-93d3-eb6d1c78ec52")
    public String getDiagramImage() {
        return this.diagramImage;
    }

    /**
     * @param explorerIcon the explorerIcon to set
     */
    @objid ("b3aa51f8-7114-4f6e-90fe-968921691722")
    public void setExplorerIcon(String explorerIcon) {
        this.explorerIcon = explorerIcon;
    }

    /**
     * @return the explorerIcon
     */
    @objid ("e5854afd-e50b-4af1-810f-1137b168ddb6")
    public String getExplorerIcon() {
        return this.explorerIcon;
    }

    /**
     * @return the applyStereotype
     */
    @objid ("3b565b69-94f7-4e0e-b971-9328b3dc8a2a")
    public boolean isApplyStereotype() {
        return this.applyStereotype;
    }

    /**
     * @param applyStereotype the applyStereotype to set
     */
    @objid ("8cf78a27-2bd9-4365-8a7b-b4e8629d58a9")
    public void setApplyStereotype(boolean applyStereotype) {
        this.applyStereotype = applyStereotype;
    }

    @objid ("c1c18a54-5e84-47bb-8944-5bca6f03a4e3")
    public boolean isCreationMode() {
        return this.editedStereotype == null;
    }

    @objid ("0ee26cd7-24cb-484f-97b5-fedcdf0a13d8")
    public Stereotype getEditedStereotype() {
        return this.editedStereotype;
    }

    @objid ("ecf0e66c-17ff-44f6-bf68-1948ba0adfcf")
    public String getImageName() {
        return this.imageName;
    }

    @objid ("105f0908-938a-43bf-9320-349b8bae5189")
    public void setImageName(String imagePath) {
        if (imagePath.isEmpty()) {
            setDiagramImage("");
        }
        this.imageName = imagePath;
    }

    @objid ("4b832a20-94b2-47f9-81d4-0c8a74c228c8")
    public String getIconName() {
        return this.iconName;
    }

    @objid ("022bc035-6e03-410f-aab4-2101f2333114")
    public void setIconName(String iconPath) {
        if (iconPath.isEmpty()) {
            setExplorerIcon("");
        }
        this.iconName = iconPath;
    }

    @objid ("6993b557-9f7d-426a-91dd-9f114a69586a")
    public Path getDefaultTempIconPath() {
        if (!this.iconName.isEmpty()) {
            return this.projectPath.resolve(".runtime").resolve("modules").resolve("local").resolve("tempIcon"+getIconExtension());
        }
        return null;
    }

    @objid ("cc60609e-50df-4e61-b987-8045ad5c456d")
    public Path getDefaultTempImagePath() {
        if (!this.imageName.isEmpty()) {
            return this.projectPath.resolve(".runtime").resolve("modules").resolve("local").resolve("tempImage"+getImageExtension());
        }
        return null;
    }

    @objid ("e38ec154-381c-455c-b7f9-f9099612e845")
    public String getIconExtension() {
        return this.iconName.substring(this.iconName.lastIndexOf("."));
    }

    @objid ("60876813-048d-44e8-b558-877bed8a4ed8")
    public String getImageExtension() {
        return this.imageName.substring(this.imageName.lastIndexOf("."));
    }

}
