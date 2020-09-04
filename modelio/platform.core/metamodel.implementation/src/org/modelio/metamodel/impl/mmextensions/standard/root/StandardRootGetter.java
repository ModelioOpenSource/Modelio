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

package org.modelio.metamodel.impl.mmextensions.standard.root;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vcore.model.api.IRepositoryRootGetter;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

@objid ("4eee641e-cb2e-454f-ad09-815d05843952")
public class StandardRootGetter implements IRepositoryRootGetter {
    @objid ("8f9e90df-3546-4f02-908b-b420a03a2237")
    private SmMetamodel mm;

    @objid ("884de329-81db-430f-b881-c83db89ce97b")
    @Override
    public Collection<MObject> getRootElements(IRepository repository) {
        Collection<MObject> roots = repository.findByClass(mm.getMClass(AbstractProject.class));
        return roots;
    }

    @objid ("a1da0b56-5f6c-4bfd-bc99-82088d912a73")
    public StandardRootGetter(SmMetamodel mm) {
        this.mm = mm;
    }

}
