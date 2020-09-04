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

package org.modelio.metamodel.impl.mmextensions.infrastructure.root;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vcore.model.api.IRepositoryRootGetter;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

@objid ("2171c150-961a-42a9-95e5-67ee4f6d6f2a")
public class InfrastructureRootGetter implements IRepositoryRootGetter {
    @objid ("b5dfd625-acfc-4817-a404-d2beb887219c")
    private final SmMetamodel mm;

    @objid ("198060a7-8e00-4864-ab70-c799bfbb5a37")
    @Override
    public Collection<MObject> getRootElements(IRepository repository) {
        Collection<MObject> roots = repository.findByClass(mm.getMClass(AbstractProject.class));
        return roots;
    }

    @objid ("f55dcba5-49da-472e-a008-42b56d9950c6")
    public InfrastructureRootGetter(SmMetamodel mm) {
        this.mm = mm;
    }

}
