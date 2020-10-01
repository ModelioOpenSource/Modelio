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

package org.modelio.api.modelio.diagram.tools;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;

/**
 * Basic definition of a command for the diagram palette.<br>
 * It must provide a label, a tooltip and a bitmap.
 */
@objid ("01f41e44-0000-0101-0000-000000000000")
public interface IDiagramTool {
    @objid ("d02a8112-e629-47b2-a01b-25d76c89667a")
    void decorate(String label, String tooltip, ImageDescriptor image);

    @objid ("4a925702-da5c-42f4-84b0-0a7d905f0067")
    void initialize(List<ElementScope> sourceScopes, List<ElementScope> targetScopes, Map<String, String> parameters, IModule module);

    /**
     * Get the command's bitmap.
     * 
     * @return the bitmap ImageDescriptor.
     */
    @objid ("01f41e44-0000-018c-0000-000000000000")
    ImageDescriptor getBitmap();

    /**
     * Get the name of the command.
     * 
     * @return the name of the command.
     */
    @objid ("01f41e44-0000-0186-0000-000000000000")
    String getLabel();

    @objid ("e848cc4d-aa6c-48ca-bccf-4b9d2fd30e8d")
    Map<String, String> getParameters();

    @objid ("d4fd734d-76b6-4086-89b7-fd8100d58895")
    List<ElementScope> getSourceScopes();

    @objid ("5cf01cdf-b0e8-4948-8f81-325fbae8a554")
    List<ElementScope> getTargetScopes();

    /**
     * Gets the tooltip of this command.
     * 
     * @return the text that will be displayed in the command's tooltip.
     */
    @objid ("01f41e44-0000-0189-0000-000000000000")
    String getTooltip();

    @objid ("fb32166c-6c4b-4348-9f97-f68567a46151")
    IModule getModule();

}
