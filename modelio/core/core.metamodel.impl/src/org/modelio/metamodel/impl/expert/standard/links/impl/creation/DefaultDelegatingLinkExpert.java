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

package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b2b1fa08-337b-4c7f-befa-96f51ae65537")
public class DefaultDelegatingLinkExpert implements ILinkExpert {
    @objid ("92562c5c-75fb-4374-a925-20966145a406")
    private final ILinkExpert defaultExpert;

    @objid ("8c74e35d-9e00-46f4-824e-e2a4727d66d2")
    public DefaultDelegatingLinkExpert(ILinkExpert defaultExpert) {
        this.defaultExpert = defaultExpert;
    }

    @objid ("c657198d-9422-4aeb-87d9-95595f02dcba")
    @Override
    public boolean canLink(MClass linkMetaclass, MClass from, MClass to) {
        return this.defaultExpert.canLink(linkMetaclass, from, to);
    }

    @objid ("028549ac-99d7-4ad0-addb-696d648b7953")
    @Override
    public boolean canLink(MClass linkMetaclass, MObject from, MObject to) {
        return this.defaultExpert.canLink(linkMetaclass, from, to);
    }

    @objid ("962ba945-8b3e-4a25-bfcc-430078fc182a")
    @Override
    public boolean canSource(MClass linkMetaclass, MClass from) {
        return this.defaultExpert.canSource(linkMetaclass, from);
    }

    @objid ("f1ace80c-60e8-4c90-a31d-4a7077b6f23b")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        return this.defaultExpert.canSource(linkElement, from);
    }

    @objid ("42674151-e0b9-4f82-99a4-bd93aa442814")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass to) {
        return this.defaultExpert.canTarget(linkMetaclass, to);
    }

    @objid ("075779a3-4788-447c-8cdf-e60e417b4584")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        return this.defaultExpert.canTarget(linkElement, to);
    }

}
