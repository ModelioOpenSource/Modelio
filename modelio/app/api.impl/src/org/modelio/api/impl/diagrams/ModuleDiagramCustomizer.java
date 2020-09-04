/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.impl.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.diagram.IDGDynamicDecorator;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.plugin.IDiagramConfigurer;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.plugin.ToolRegistry;
import org.modelio.diagram.elements.common.abstractdiagram.IDynamicStyler;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Configures a diagram coming from a module.
 * <p>
 * This implementation is based on an {@link IDiagramCustomizer} (the base diagram,
 * associated with the diagram's metaclass) combined with an {@link IDiagramCustomizer} (for specific
 * palette configuration and dynamic styling).
 * </p>
 */
@objid ("ff3b6178-c500-4243-b283-150bd874b0a0")
class ModuleDiagramCustomizer implements IDiagramConfigurer {
    @objid ("b50de30d-d539-464d-9ee7-9bb58f4510ad")
    private IDiagramConfigurer baseConfigurer;

    @objid ("63e3145a-5297-4229-9bcf-0f173959430f")
    private IDiagramCustomizer customizer;

    @objid ("b39d7a70-7474-440e-8c31-a121ee0e9e21")
    public ModuleDiagramCustomizer(final MClass baseDiagramClass, final IDiagramCustomizer customizer, IDiagramConfigurerRegistry configurerRegistry) {
        this.customizer = customizer;
        this.baseConfigurer = configurerRegistry.getConfigurer(baseDiagramClass.getName());
    }

    @objid ("3dae4810-e99a-4e6c-836b-b5a9f4f20669")
    @Override
    public String getContributionURI() {
        return this.baseConfigurer.getContributionURI();
    }

    @objid ("ec839c9a-3ee8-46af-8bd6-f2513863b93a")
    @Override
    public PaletteRoot initPalette(final AbstractDiagramEditor diagram, final ToolRegistry toolRegistry) {
        PaletteRoot paletteRoot = null;
        if (this.customizer.keepBasePalette()) {
            paletteRoot = this.baseConfigurer.initPalette(diagram, toolRegistry);
        } else {
            paletteRoot = new PaletteRoot();
        }
        
        this.customizer.fillPalette(paletteRoot);
        return paletteRoot;
    }

/* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
    @objid ("699e44b7-8722-4cac-a125-55b741e7576f")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.baseConfigurer == null) ? 0 : this.baseConfigurer.hashCode());
        result = prime * result + ((this.customizer == null) ? 0 : this.customizer.hashCode());
        return result;
    }

/* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
    @objid ("87b9adf4-1e1a-44d5-a8da-32c65e68fb66")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleDiagramCustomizer other = (ModuleDiagramCustomizer) obj;
        if (this.baseConfigurer == null) {
            if (other.baseConfigurer != null)
                return false;
        } else if (!this.baseConfigurer.equals(other.baseConfigurer))
            return false;
        if (this.customizer == null) {
            if (other.customizer != null)
                return false;
        } else if (!this.customizer.equals(other.customizer))
            return false;
        return true;
    }

    @objid ("949b6af1-f220-4984-9049-6aedc7799a5d")
    @Override
    public IDynamicStyler getDynamicStyler() {
        IDGDynamicDecorator dgDecorator = this.customizer.getDynamicDecorator();
        return dgDecorator != null ? new DGDynamicStyler(dgDecorator) : null;
    }

}
