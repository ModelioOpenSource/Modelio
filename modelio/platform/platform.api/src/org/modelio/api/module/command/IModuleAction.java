/* 
 * Copyright 2013-2020 Modeliosoft
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

import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface defining an Action or Command provided by a module.
 * <p>
 * Usually to be used either in contextual menus or in the toolbar of
 * the property view.
 */
@objid ("00d00158-0001-5db6-0000-000000000000")
public interface IModuleAction {
    @objid ("00d00158-0001-5e39-0000-000000000000")
    String getLabel();

    @objid ("00d00158-0001-5e3b-0000-000000000000")
    IModule getModule();

    @objid ("00d00158-0001-5e3d-0000-000000000000")
    String getName();

    @objid ("00d00158-0001-5e3f-0000-000000000000")
    String getTooltip();

    @objid ("00d00158-0001-5e41-0000-000000000000")
    IModuleCommandHandler getHandler();

    @objid ("00d00158-0001-5e43-0000-000000000000")
    Path getBitmapPath();

    @objid ("01e40254-0000-104d-0000-000000000000")
    boolean isActiveFor(MObject[] selectedElements, boolean readOnlyTool);

    @objid ("01e40254-0000-1055-0000-000000000000")
    boolean accept(MObject[] selectedElements);

    @objid ("6a421697-e247-11dd-abd0-0014222a9f79")
    boolean needReadWriteObject();

    @objid ("d4872759-5b7b-11e0-a93a-002564c97630")
    Path getSlotImagePath(final int slotIndex);

    @objid ("a784bfb3-c8d4-11e0-9f93-002564c97630")
    List<String> getSlots();

    @objid ("c414ddce-83eb-4cc1-a986-327d23e4f7aa")
    void parseSlotPattern(String slotPattern);

    @objid ("bbb97a9a-e383-443c-84e1-c9f9f7d0fa58")
    void parseSlotImagePaths(String slotImagePaths);

}
