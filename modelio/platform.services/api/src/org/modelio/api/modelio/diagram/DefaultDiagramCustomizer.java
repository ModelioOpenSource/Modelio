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

package org.modelio.api.modelio.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.tools.PaletteEntry;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.i18n.I18nSupport;

@objid ("2bc36fee-ce64-4be4-bcb9-b680f9403631")
public class DefaultDiagramCustomizer implements IDiagramCustomizer {
    @mdl.prop
    @objid ("dd520d3e-4254-4590-9c7b-604bc0af7201")
    private boolean keepBasePalette = true;

    @objid ("f9efb3dc-4843-4c1c-8659-047ffd90e26e")
    private Map<String, String> parameters;

    @objid ("77b38e4b-df10-45ba-891b-e66b06f9e2d9")
    private IModule module;

    @mdl.prop
    @objid ("08bc1954-4c23-4f8b-8bfe-6ca147319c89")
    private List<PaletteEntry> tools = new ArrayList<>();

    @objid ("85663442-c66b-4015-aca9-f7adee5f5e78")
    @Override
    public void fillPalette(final PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = this.module.getModuleContext().getModelioServices().getDiagramService();
        
        if (!this.keepBasePalette) {
            final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
            commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
            commonGroup.add(new SelectionToolEntry());
            commonGroup.add(new MarqueeToolEntry());
            commonGroup.add(toolRegistry.getRegisteredTool(IDiagramService.TOOL_POPUPMENU_CREATELINK));
            paletteRoot.add(commonGroup);
        }
        
        Map<String, PaletteDrawer> paletteMap = new HashMap<>();
        
        for (PaletteEntry tool : this.tools) {
            I18nSupport i18nSupport = this.module.getModuleContext().getI18nSupport();
            PaletteDrawer palette = paletteMap.get(i18nSupport.getString(tool.group));
            if (palette == null) {
                palette = new PaletteDrawer(tool.group, null);
                palette.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
                paletteRoot.add(palette);
                paletteMap.put(i18nSupport.getString(tool.group), palette);
            }
            palette.add(toolRegistry.getRegisteredTool(tool.toolId));
        }
    }

    @objid ("ba5f41e8-732a-4f63-9250-77e305e4f80b")
    protected String getParameter(final String key) {
        return this.parameters.get(key);
    }

    @objid ("893cd5bd-e0b9-4a98-9971-c09f09b11714")
    @Override
    public Map<String, String> getParameters() {
        return this.parameters;
    }

    @objid ("6dbbeaa2-fd50-4995-b212-2f60c1962ec4")
    @Override
    public void initialize(IModule aModule, List<PaletteEntry> paletteTools, Map<String, String> hParameters, boolean keepPalette) {
        this.module = aModule;
        this.tools = paletteTools;
        this.parameters = hParameters;
        this.keepBasePalette = keepPalette;
    }

    @objid ("1726e5c8-3207-4c08-a093-29ff91ab19e0")
    @Override
    public boolean keepBasePalette() {
        // Automatically generated method. Please do not modify this code.
        return this.keepBasePalette;
    }

}
