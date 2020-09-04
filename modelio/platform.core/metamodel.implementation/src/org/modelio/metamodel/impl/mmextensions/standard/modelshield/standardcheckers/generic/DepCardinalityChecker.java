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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001c12b8-7ea0-1f6c-bf9a-001ec947cd2a")
public abstract class DepCardinalityChecker implements IChecker {
    @objid ("006eb8c4-7f46-1f6c-bf9a-001ec947cd2a")
    private final String errorId;

    @objid ("006ed55c-7f46-1f6c-bf9a-001ec947cd2a")
    private final String depName;

    @objid ("74b0d66c-28e7-11e2-a571-001ec947ccaf")
    private MDependency dep;

    @objid ("006f05ea-7f46-1f6c-bf9a-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null)
            return;
        
        if (this.dep == null)
            this.dep = object.getMClass().getDependency(this.depName);
        
        if (this.dep == null || ((SmDependency) this.dep).isDynamic())
            return;
        
        int currentCard = object.mGet(this.dep).size();
        
        if (currentCard < this.dep.getMinCardinality()
                || ((this.dep.getMaxCardinality() > 0) && (currentCard > this.dep.getMaxCardinality()))) {
        
            report.addEntry(createError(object, this.dep, currentCard));
        }
    }

    @objid ("0071f48a-80c8-1f6c-bf9a-001ec947cd2a")
    public DepCardinalityChecker(final String errorId, final String depName) {
        this.errorId = errorId;
        this.depName = depName;
    }

/*
     * Derived classes should redefine this method where needed so that a clear report can be provided to the end user.
     *
     * This 'default' implementation produces a rather technical report about SmDep cardinalities which is most often poorly
     * understandable by end users
     */
    @objid ("d889a123-a103-4ce6-b326-f12443a93c01")
    protected abstract ModelError createError(final MObject object, MDependency dep, int currentCard);

/*
     * Creates a 'default' Model error
     *
     */
    @objid ("c9c424e2-0798-4dba-8a54-bdd303ed54bf")
    protected final ModelError createDefaultError(final MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(dep.getName());
        objects.add(dep.getMinCardinality());
        objects.add(dep.getMaxCardinality());
        objects.add(currentCard);
        return new ModelError(this.errorId, object, objects);
    }

}
