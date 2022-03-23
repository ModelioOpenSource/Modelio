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
package org.modelio.gproject.gproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.plugin.CoreProject;

/**
 * Indicates that the project already contain a fragment with the same identifier or URI.
 */
@objid ("fe631048-4d8c-4c21-8402-26edb092c6dc")
public class FragmentConflictException extends Exception {
    @objid ("744264a6-35a6-4c5b-9814-38b90e2a8bb8")
    private static final long serialVersionUID = 1L;

    @objid ("6a6d10f2-36dd-44cb-9de5-8403e8db6208")
    private IProjectFragment orig;

    @objid ("1b77b032-184e-413e-af4b-bcb153dc7627")
    private IProjectFragment duplicate;

    @objid ("45a33022-473b-4494-a7d3-31414ac6f0b6")
    private GProject project;

    /**
     * @param orig the fragment that was already in the project.
     * @param duplicate the duplicate fragment
     * @param project the involved project.
     */
    @objid ("66cda570-15aa-43f9-bdf8-1ac8ae99f217")
    public  FragmentConflictException(IProjectFragment orig, IProjectFragment duplicate, GProject project) {
        this.orig = orig;
        this.duplicate = duplicate;
        this.project = project;
        
    }

    /**
     * Get the fragment that was already in the project.
     * @return the original fragment.
     */
    @objid ("84a89b56-4958-47ab-ba2f-e86393b66f7e")
    public IProjectFragment getOrig() {
        return this.orig;
    }

    /**
     * @return the duplicate fragment
     */
    @objid ("dbc09b57-c7a8-4adc-a4f8-089af3be7c70")
    public IProjectFragment getDuplicate() {
        return this.duplicate;
    }

    /**
     * @return the involved project.
     */
    @objid ("abb3fe01-2b60-441e-9a07-cb3ef8197814")
    public GProject getProject() {
        return this.project;
    }

    @objid ("89da7f02-f7ab-411d-b912-9d0e1b315ac1")
    @Override
    public String getMessage() {
        return CoreProject.I18N.getMessage("FragmentConflictException", 
                        this.orig.getId(), this.duplicate.getId(), 
                        this.orig.getUri(), this.duplicate.getUri());
        
    }

}
