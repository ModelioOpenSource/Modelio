/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of the {@link IModuleAction} interface.
 */
@objid ("00d00158-0001-5da9-0000-000000000000")
public class DefaultModuleAction implements IModuleAction {
    @objid ("5107e484-0349-11de-bb9a-0014222a9f79")
    private String bitmapPath;

    @objid ("00d00158-0001-5dc5-0000-000000000000")
    private boolean editTheModel;

    @objid ("00d00158-0001-5dbb-0000-000000000000")
    private String label;

    @objid ("00d00158-0001-5db9-0000-000000000000")
    private String name;

    @objid ("00d00158-0001-5dc3-0000-000000000000")
    private boolean needReadWriteObject;

    @objid ("00d00158-0001-5dbd-0000-000000000000")
    private String tooltip;

    @objid ("e079e5d8-eff4-11e0-8ddf-002564c97630")
    private List<String> slotImagePaths = new ArrayList<>();

    @objid ("e07a33fa-eff4-11e0-8ddf-002564c97630")
    private List<String> slots = new ArrayList<>();

    @objid ("00d00158-0001-5e08-0000-000000000000")
    private IModule module;

    @objid ("31bb2105-0ecd-11e2-96c4-002564c97630")
    private IModuleCommandHandler handler;

    /**
     * @param module the module
     * @param name the action name
     * @param label the label
     * @param tooltip the tooltip
     * @param bitmapPath the icon
     * @param slotPattern the sub menu path
     * @param slotImagePaths the slot images path
     * @param needReadWriteObject whether the target must be writeable
     * @param editTheModel whether the command modifies the model
     * @param command the command handler
     */
    @objid ("fa237f2f-5b7b-11e0-a93a-002564c97630")
    public DefaultModuleAction(final IModule module, final String name, final String label, final String tooltip, final String bitmapPath, final String slotPattern, final String slotImagePaths, final boolean needReadWriteObject, final boolean editTheModel, final IModuleCommandHandler command) {
        this.module = module;
        this.name = name;
        this.label = label;
        this.tooltip = tooltip;
        this.bitmapPath = bitmapPath;
        if (!slotPattern.isEmpty()) {
            parseSlotPattern(slotPattern);
            parseSlotImagePaths(slotImagePaths);
        }
        this.needReadWriteObject = needReadWriteObject;
        this.editTheModel = editTheModel;
        this.handler = command;
    }

    @objid ("00d012e4-0000-59b7-0000-000000000000")
    @Override
    public final boolean accept(MObject[] selectedElements) {
        Boolean result = false;
        try {
            result = this.handler.accept(Arrays.asList(selectedElements), this.module);
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    @objid ("00d00158-0001-5ded-0000-000000000000")
    @Override
    public Path getBitmapPath() {
        Path candidateFile = Paths.get(this.bitmapPath);
        
        if (!Files.isRegularFile(candidateFile) || !candidateFile.isAbsolute()) {
            Path moduleDirectory = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();
            candidateFile = moduleDirectory.resolve(this.bitmapPath);
        }
        
        if (Files.isRegularFile(candidateFile)) {
            return candidateFile;
        }
        return null;
    }

    @objid ("00d00158-0001-5dd5-0000-000000000000")
    @Override
    public IModuleCommandHandler getHandler() {
        return this.handler;
    }

    @objid ("00d00158-0001-5dde-0000-000000000000")
    @Override
    public String getLabel() {
        return this.label;
    }

    @objid ("00d00158-0001-5de1-0000-000000000000")
    @Override
    public IModule getModule() {
        return this.module;
    }

    @objid ("00d00158-0001-5de4-0000-000000000000")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("fa261750-5b7b-11e0-a93a-002564c97630")
    @Override
    public Path getSlotImagePath(final int slotIndex) {
        if (slotIndex < this.slotImagePaths.size()) {
            String slotImagePath = this.slotImagePaths.get(slotIndex);
        
            Path candidateFile = Paths.get(slotImagePath);
        
            if (!Files.isRegularFile(candidateFile) || !candidateFile.isAbsolute()) {
                Path moduleDirectory = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();
                candidateFile = moduleDirectory.resolve(slotImagePath);
            }
        
            if (Files.isRegularFile(candidateFile)) {
                return candidateFile;
            }
        }
        return null;
    }

    @objid ("61cd9a18-595c-11dd-b71c-0014222a9f79")
    @Override
    public List<String> getSlots() {
        return this.slots;
    }

    @objid ("00d00158-0001-5de7-0000-000000000000")
    @Override
    public String getTooltip() {
        return this.tooltip;
    }

    @objid ("00d012e4-0000-59bd-0000-000000000000")
    @Override
    public final boolean isActiveFor(MObject[] selectedElements, boolean readOnlyTool) {
        // Test concerning the readOnly status
        if (this.editTheModel && readOnlyTool) {
            return false;
        }
        
        // Test concerning the object itself
        if (this.needReadWriteObject) {
            for (MObject element : selectedElements) {
                if (!element.getStatus().isModifiable()) {
                    return false;
                }
            }
        }
        
        // User test
        try {
            return this.handler.isActiveFor(Arrays.asList(selectedElements), this.module);
        } catch (Exception e) {
            // Ignore exception
            return false;
        }
    }

    @objid ("72d5ec5f-e247-11dd-abd0-0014222a9f79")
    @Override
    public boolean needReadWriteObject() {
        return this.needReadWriteObject;
    }

    @objid ("0885feff-fd17-43a9-a3b1-3524a1b1ebf2")
    private Boolean compareStereotype(Stereotype stereotype, Stereotype type) {
        if (stereotype.equals(type)) {
            return true;
        } else {
            if (stereotype.getParent() != null) {
                return compareStereotype(stereotype.getParent(), type);
            }
        }
        return false;
    }

    @objid ("cfeb825d-7236-4f52-922a-28a381b247a4")
    @Override
    public void parseSlotPattern(String slotPattern) {
        if (!slotPattern.isEmpty()) {
            for (String slot : slotPattern.split("\\|")) {
                this.slots.add(this.module.getModuleContext().getI18nSupport().getString(slot));
            }
        }
    }

    @objid ("080e6eb0-a308-4574-9773-6f03b0e9126c")
    @Override
    public void parseSlotImagePaths(String slotImagePathPatterns) {
        if (!slotImagePathPatterns.isEmpty()) {
            for (String slotImagePath : slotImagePathPatterns.split("\\|")) {
                this.slotImagePaths.add(this.module.getModuleContext().getI18nSupport().getString(slotImagePath));
            }
        }
    }

}
