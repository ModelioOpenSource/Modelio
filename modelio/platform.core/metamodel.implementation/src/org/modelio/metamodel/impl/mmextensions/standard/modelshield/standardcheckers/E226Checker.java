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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E226:
 * <ul>
 * <li>desc = A PackageImport must be directed towards a Package.</li>
 * <li>what = A package import belonging to ''{1}'' is not directed towards a package.</li>
 * </ul>
 */
@objid ("0091dfa2-e20d-1f69-b3fb-001ec947cd2a")
public class E226Checker extends DepCardinalityChecker {
    @objid ("0051bcb0-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E226";

    @objid ("005c9270-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "ImportedPackage";

    @objid ("0090df26-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=PackageImport, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(PackageImport.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=PackageImport, feature=ImportedPackage
        plan.registerChecker(this, smMetamodel.getMClass(PackageImport.class), TriggerType.Update, DEPNAME);
    }

    @objid ("005c9b94-9e33-1f6c-bf9a-001ec947cd2a")
    public E226Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("7eaedc22-65e8-47ca-a78e-901526468712")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
