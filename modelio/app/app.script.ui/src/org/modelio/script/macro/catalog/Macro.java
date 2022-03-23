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
package org.modelio.script.macro.catalog;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.script.plugin.Script;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a runnable macro.
 * <p>
 * A macro has:
 * <ul>
 * <li>a name
 * <li>a description
 * <li>a script file Path
 * <li>a metaclass filter
 * <li>an optional icon.
 * 
 * It may be displayed in the tool bar and/or in the contextual menu.
 * <p>
 * A macro is owned by a {@linkplain Catalog} and its script path is relative to the catalog path.
 */
@objid ("00931804-b64c-106a-bf4f-001ec947cd2a")
public class Macro {
    @objid ("00931c1e-b64c-106a-bf4f-001ec947cd2a")
    private String description = "";

    @objid ("00931ea8-b64c-106a-bf4f-001ec947cd2a")
    private String name = "";

    @objid ("009320ce-b64c-106a-bf4f-001ec947cd2a")
    private boolean showInContextualMenu;

    @objid ("009321b4-b64c-106a-bf4f-001ec947cd2a")
    private boolean showInToolbar;

    @objid ("00932290-b64c-106a-bf4f-001ec947cd2a")
    private Collection<String> metaclasses = new ArrayList<>();

    @objid ("0030f642-b72b-106a-bf4f-001ec947cd2a")
    private Catalog catalog;

    @objid ("a9496271-d5f0-4d73-898e-42df098b7618")
    private Path iconPath;

    @objid ("623e9b51-881c-4ca7-a2cf-938a23a1f58f")
    private Path scriptPath;

    /**
     * Creates a new macro in a catalog.
     * <p>
     * The macro is not added to the catalog, {@link Catalog#addMacro(Macro)} has to be called after.
     * @param catalog the catalog that contains the macro.
     */
    @objid ("009323a8-b64c-106a-bf4f-001ec947cd2a")
    public  Macro(Catalog catalog) {
        this.catalog = catalog;
    }

    /**
     * Add a metaclass on which the macro can be run.
     * @param classname a metaclass name.
     */
    @objid ("0093245c-b64c-106a-bf4f-001ec947cd2a")
    public void addMetaclass(String classname) {
        this.metaclasses.add(classname);
    }

    /**
     * Get the catalog where the macro is stored.
     * @return the macro catalog.
     */
    @objid ("009324f2-b64c-106a-bf4f-001ec947cd2a")
    public Catalog getCatalog() {
        return this.catalog;
    }

    /**
     * get the macro description
     * @return the description
     */
    @objid ("00932588-b64c-106a-bf4f-001ec947cd2a")
    public String getDescription() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.description;
    }

    /**
     * Get the location of the script. The returned Path is an absolute Path.
     * @return The location of the script.
     */
    @objid ("00932614-b64c-106a-bf4f-001ec947cd2a")
    public Path getIconPath() {
        return this.iconPath;
    }

    /**
     * Get the metaclasses on which the macro can be run.
     * @return the metaclasses set.
     */
    @objid ("00932740-b64c-106a-bf4f-001ec947cd2a")
    public Collection<String> getMetaclasses() {
        return this.metaclasses;
    }

    /**
     * Get the macro name.
     * @return the macro name.
     */
    @objid ("009327d6-b64c-106a-bf4f-001ec947cd2a")
    public String getName() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.name;
    }

    /**
     * Get the location of the script. The returned Path is an absolute Path.
     * @return The location of the script.
     */
    @objid ("00932876-b64c-106a-bf4f-001ec947cd2a")
    public Path getScriptPath() {
        return this.scriptPath;
    }

    /**
     * Tells whether the script can be run on the given elements.
     * @param selectedElements a list of elements on which the script will be run
     * @return true if the script is runnable on the selection, false in the other case.
     */
    @objid ("00932998-b64c-106a-bf4f-001ec947cd2a")
    public boolean isRunnableOn(Collection<MObject> selectedElements) {
        // A script without metaclasses can be run anywhere
        if (getMetaclasses().isEmpty()) {
            return true;
        }
        
        // A script with metaclass(es) cannot be run on empty selection
        if (selectedElements == null || selectedElements.isEmpty()) {
            return false;
        }
        
        MObject firstElement = selectedElements.iterator().next();
        MMetamodel metamodel = firstElement.getMClass().getMetamodel();
        
        // Prepare a list of acceptable Java interfaces for the macro allowed metaclasses
        List<Class<? extends MObject>> mcInterfaces = new ArrayList<>(this.metaclasses.size());
        for (String scriptMcName : getMetaclasses()) {
            try {
                final MClass scriptMc = metamodel.getMClass(scriptMcName);
                final Class<? extends MObject> scriptInterface = scriptMc.getJavaInterface();
                mcInterfaces.add(scriptInterface);
            } catch (final IllegalArgumentException e) {
                // Metaclass not found
                Script.LOG.error(e);
            }
        }
        
        // Check each selected element agains't the allowed Java interfaces
        for (final MObject obj : selectedElements) {
            // See one of the allowed metaclasses is matching
            boolean ok = false;
            for (Class<? extends MObject> mcInterface : mcInterfaces) {
                if (mcInterface.isAssignableFrom(obj.getClass())) {
                    ok = true;
                    break;
                }
            }
        
            if (!ok) {
                return false;
            }
        }
        
        // The script passed the test for all selected elements
        return true;
    }

    /**
     * set the macro description.
     * @param string the new description.
     */
    @objid ("00932a24-b64c-106a-bf4f-001ec947cd2a")
    public void setDescription(String string) {
        if (!string.equals(this.description)) {
            this.description = string;
        }
        
    }

    /**
     * Set the absolute macro icon path.
     * @param iconPath The absolute Path to the script icon
     */
    @objid ("00932aba-b64c-106a-bf4f-001ec947cd2a")
    public void setIconPath(Path iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * Set the metaclasses on which the macro can be run.
     * @param newset the metaclass name set.
     */
    @objid ("00932bdc-b64c-106a-bf4f-001ec947cd2a")
    public void setMetaclasses(Collection<String> newset) {
        this.metaclasses = new ArrayList<>(newset);
    }

    /**
     * Set the macro name.
     * @param value the new macro name.
     */
    @objid ("00932c72-b64c-106a-bf4f-001ec947cd2a")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Set the macro absolute path.
     * @param scriptPath The absolute Path to the script
     */
    @objid ("00932cfe-b64c-106a-bf4f-001ec947cd2a")
    public void setScriptPath(Path scriptPath) {
        this.scriptPath = scriptPath;
    }

    /**
     * Set whether the macro is shown in the contextual menu.
     * @param showInContextualMenu true to show the macro in the menu, else false.
     */
    @objid ("00932e20-b64c-106a-bf4f-001ec947cd2a")
    public void setShowInContextualMenu(boolean showInContextualMenu) {
        this.showInContextualMenu = showInContextualMenu;
    }

    /**
     * Set whether the macro is shown in the tool bar.
     * @param showInToolbar true to show the macro in the tool bar, else false.
     */
    @objid ("00932eb6-b64c-106a-bf4f-001ec947cd2a")
    public void setShowInToolbar(boolean showInToolbar) {
        this.showInToolbar = showInToolbar;
    }

    /**
     * Tells whether the macro is shown in the contextual menu.
     * @return the showInContextualMenu
     */
    @objid ("00932f4c-b64c-106a-bf4f-001ec947cd2a")
    public boolean shownInContextualMenu() {
        return this.showInContextualMenu;
    }

    /**
     * Tells whether the macro is shown in the tool bar.
     * @return the showInToolbar
     */
    @objid ("00932fd8-b64c-106a-bf4f-001ec947cd2a")
    public boolean shownInToolbar() {
        return this.showInToolbar;
    }

    /**
     * Move the macro to another catalog. change the script and icon relative paths to point to the same files.
     * @param newCatalog the new catalog.
     */
    @objid ("0093306e-b64c-106a-bf4f-001ec947cd2a")
    public void moveTo(final Catalog newCatalog) {
        final Path oldPath = getScriptPath();
        final Path oldIconPath = getIconPath();
        
        if (this.catalog == newCatalog) {
            return;
        }
        
        if (this.catalog != null) {
            this.catalog.removeMacro(this);
        }
        
        newCatalog.addMacro(this);
        this.catalog = newCatalog;
        
        if (oldPath != null) {
            setScriptPath(oldPath);
        }
        if (oldIconPath != null) {
            setIconPath(oldIconPath);
        }
        
    }

}
