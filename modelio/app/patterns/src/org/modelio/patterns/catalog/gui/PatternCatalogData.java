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

package org.modelio.patterns.catalog.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.patterns.api.IPatternRepository;
import org.modelio.patterns.model.RuntimePattern;

@objid ("efa5ce22-9066-4361-ae6d-e518cac9f642")
public class PatternCatalogData {
    @objid ("42354ccf-0965-49e9-b953-07b4328efc98")
    private RuntimePattern selectedPattern;

    @objid ("1bdb1ad0-5c2d-4a6e-b005-7865f1dd244f")
    private IPatternRepository repository;

    @objid ("5f4aab61-49d8-4ea1-979f-58741f06604b")
    public PatternCatalogData(IPatternRepository repository) {
        this.selectedPattern = null;
        
        this.repository = repository;
    }

    @objid ("3cb242a3-e755-4322-bcf2-bc91848cb375")
    public RuntimePattern getSelectedPattern() {
        return this.selectedPattern;
    }

    @objid ("e52e38d7-f8f9-4232-892d-d9ea2f3985cb")
    public void setSelectedPattern(RuntimePattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }

    @objid ("d3462826-acfd-461b-9791-5921647a55ef")
    public IPatternRepository getRepository() {
        return this.repository;
    }

}
